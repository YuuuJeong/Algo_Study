import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 염기서열 커버 문제
public class Softeer6249 {
	static String[] sequences;
	static int N, M, MIN_CNT;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 좋은 염기서열의 개수
		M = Integer.parseInt(st.nextToken()); // 좋은 염기서열의 길이

		sequences = new String[N];
		for (int n = 0; n < N; n++) {
			sequences[n] = br.readLine();
		}

		MIN_CNT = N; // 최악의 경우 입력받은 모든 염기서열 각각이 하나의 초염기서열
		findSequences(sequences);
		System.out.println(MIN_CNT);
	}

	static void findSequences(String[] sequences) {
		boolean[] isCovered = new boolean[N];

		dfs(sequences, isCovered, 0, 0);
		return;
	}

	static void dfs(String[] sequences, boolean[] isCovered, int start, int cnt) {
		if (cnt >= MIN_CNT)
			return;

		boolean coveredAll = true;
		for (int i = 0; i < N; i++) {
			if (!isCovered[i]) {
				coveredAll = false;
				break;
			}
		}

		if (coveredAll) {
			MIN_CNT = cnt > MIN_CNT ? MIN_CNT : cnt;
			return;
		}

		for (int i = start; i < N; i++) {
			if (!isCovered[i]) {
				boolean[] tmp = Arrays.copyOf(isCovered, N);
				for (int j = 0; j < N; j++) {
					if (canCover(sequences[i], sequences[j]))
						isCovered[j] = true;
				}
				dfs(sequences, isCovered, i + 1, cnt + 1);
			}
		}
	}

	static boolean canCover(String seq1, String seq2) {
		for (int i = 0; i < M; i++) {
			if (seq1.charAt(i) != '.' && seq2.charAt(i) != '.' && seq1.charAt(i) != seq2.charAt(i))
				return false;
		}
		return true;
	}
}
