import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 자동차 테스트 문제
public class Softeer6247 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 자동차의 개수
		int q = Integer.parseInt(st.nextToken()); // 테스트할 연비 값 개수

		int[] fuelEff = new int[n]; // n개의 자동차 연비를 저장할 배열
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			fuelEff[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(fuelEff); // 연비 배열 정렬

		// 각 연비 값의 위치(인덱스)를 저장할 해시맵
		Map<Integer, Integer> testDict = new HashMap<>();
		for (int i = 0; i < n; i++) {
			testDict.put(fuelEff[i], i); // 연비 값을 키로, 해당 연비의 인덱스를 값으로 저장
		}

		// q개의 테스트 입력 처리
		// 입력받은 질의 값이 3개의 자동차 연비를 뽑았을 때 중앙값이 되는 경우의 수 찾기
		for (int i = 0; i < q; i++) {
			int td = Integer.parseInt(br.readLine()); // 테스트할 연비 값(td) 입력

			if (testDict.containsKey(td)) { // 해시맵에 해당 연비 값이 있는지 확인
				int temp = testDict.get(td); // 해당 연비 값의 인덱스를 가져옴
				sb.append((temp) * (n - temp - 1)).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}

		System.out.println(sb);
	}
}
