import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 자동차 테스트 문제
public class Softeer6247_Comb {
	static int n;
	static int[] fuelEff;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 자동차의 개수
		int q = Integer.parseInt(st.nextToken()); // 테스트할 연비 값 개수

		fuelEff = new int[n]; // n개의 자동차 연비를 저장할 배열
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			fuelEff[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(fuelEff);

		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());

			int ans = fuelCombinations(fuelEff, query);

			sb.append(ans).append("\n");
		}

		System.out.println(sb);
	}

	// 특정 연비 값이 중앙값으로 나오는 조합의 수
	static int fuelCombinations(int[] fuelEff, int target) {
		int count = 0;

		for (int i = 0; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {
				for (int k = j + 1; k < n; k++) {
					// 3개의 연비 값을 배열에 저장&정렬
					int[] triplet = { fuelEff[i], fuelEff[j], fuelEff[k] };
					Arrays.sort(triplet);

					int median = triplet[1]; // 1번 인덱스 값이 중앙값

					if (median == target) {
						count++;
					}
				}
			}
		}
		return count;
	}
}
