import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ1039_교환 {
	static int K;
	static int maxValue;
	static HashSet<String>[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String N = st.nextToken();
		K = Integer.parseInt(st.nextToken());

		// maxValue를 N으로 초기화
		maxValue = -1;

		if (N.length() == 1 || (N.length() == 2 && N.charAt(1) == '0')) {
			System.out.println(-1);
			return;
		}

		visited = new HashSet[K + 1];

		for (int i = 0; i <= K; i++) {
			visited[i] = new HashSet<>();
		}

		visited[0].add(N);

		// N자리에서 2개 swap, 이를 재귀로 K번 반복한 후 maxValue 갱신
		dfs(N, 0);

		System.out.println(maxValue);
	}

	static void dfs(String num, int swapCnt) {
		if (swapCnt == K) {
			maxValue = Math.max(maxValue, Integer.parseInt(num));
			return;
		}

		int n = num.length();

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				// 두 자리 교환
				String swapped = swap(num, i, j);

				if (swapped.charAt(0) != '0' && !visited[swapCnt + 1].contains(swapped)) {
					visited[swapCnt + 1].add(swapped);
					dfs(swapped, swapCnt + 1);
				}
			}
		}
	}

	static String swap(String num, int i, int j) {
		char[] charArray = num.toCharArray();
		char temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return new String(charArray);
	}
}
