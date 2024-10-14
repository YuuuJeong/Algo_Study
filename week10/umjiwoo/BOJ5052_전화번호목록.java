

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ5052_전화번호목록 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine()); // 전화번호의 수
			String[] phoneNumbers = new String[n];

			// 전화번호 입력 받기
			for (int i = 0; i < n; i++) {
				phoneNumbers[i] = br.readLine();
			}

			// 전화번호 목록 정렬
			Arrays.sort(phoneNumbers);

			boolean isConsistent = true;

			// 인접한 전화번호들끼리 접두어 여부 확인
			for (int i = 0; i < n - 1; i++) {
				if (phoneNumbers[i + 1].startsWith(phoneNumbers[i])) {
					isConsistent = false;
					break;
				}
			}

			if (isConsistent) {
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			}
		}
		System.out.println(sb);
	}
}
