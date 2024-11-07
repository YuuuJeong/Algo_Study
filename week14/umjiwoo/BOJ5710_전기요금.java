import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5710_전기요금 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if (A == 0 && B == 0)
				break;

			int totalUsage = calcUsage(A); // 이웃+상근 요금 -> 사용량 계산

			int minUsage = findMinimumDifference(totalUsage, B); // 사용량과 |이웃-상근| 정보를 이용해 상근이가 내야하는 요금 추정
			int res = calculateBill(minUsage);
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}

	static int calcUsage(int charge) {
		int usage = 0;

		if (charge <= 200) {
			usage = charge / 2;
		} else if (charge <= 29900) {
			usage = 100 + (charge - 200) / 3;
		} else if (charge <= 4979900) {
			usage = 10000 + (charge - 29900) / 5;
		} else {
			usage = 1000000 + (charge - 4979900) / 7;
		}

		return usage;
	}

	static int calculateBill(int usage) {
		int charge = 0;

		if (usage <= 100) {
			charge = usage * 2;
		} else if (usage <= 10000) {
			charge = 200 + (usage - 100) * 3;
		} else if (usage <= 1000000) {
			charge = 200 + 29700 + (usage - 10000) * 5;
		} else {
			charge = 200 + 29700 + 4950000 + (usage - 1000000) * 7;
		}

		return charge;
	}

	static int findMinimumDifference(int totalUsage, int B) {
		int left = -1, right = totalUsage; // 찾고자 하는 상근이의 사용량은 0이 될 있으므로 left=-1부터 시작

		while (left + 1 < right) { // while문 안에서 조건을 통해 left or right에 mid 대입, 이때 mid는 left<mid<right
			// => while문의 종료 조건은 TTT`FF... 또는 FFF`TT... 처럼 T->F(F->T)가 되는 변곡점(?)
			// => 따라서 while문 탈출 조건은 left+1==right 인 지점이 됨
			int mid = (left + right) / 2; // 상근이의 사용량을 나타내는 mid를 줄이고 늘려가며 diff=B를 만족하는 값 찾기

			int billA = calculateBill(mid);
			int billB = calculateBill(totalUsage - mid);

			int diff = Math.abs(billA - billB);

			// ver 1.템플릿 적용 x
//			if (diff < B) { // 상근이와 이웃의 요금 차가 알고있는 값보다 작음
//				right = mid; // 차이를 늘려야 하므로 right를 낮춰
//			} else if (diff > B) { // 상근이와 이웃의 요금 차가 알고있는 값보다 큼
//				left = mid; // 차이를 줄여야 하므로 left(상근이의 사용량)을 증가시켜 두 사람의 사용량 차를 줄임
//			} else {
//				return calculateBill(mid);
//			}

			// ver 2.템플릿 적용 o
			// 결국 문제에서 찾고자 하는 값은 상근이의 최소 사용량 ; 0~(이웃+상근) 사용량 범위에서 FFF...FTTT..T 가 되는 lower
			// bound를 찾고싶은 것
			// 따라서 lower bound 탐색을 위해 right를 갱신하는 쪽의 조건문에 문제에서 요구하는 조건을 만족함을 포함시킴
			if (diff <= B) { // 상근이와 이웃의 요금 차가 알고있는 값보다 작음
				right = mid; // 차이를 늘려야 하므로 상근이의 사용량을 낮춤
			} else {
				left = mid;
			}
		}
		return right;
//		return 0;
	}
}
