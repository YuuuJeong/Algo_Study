import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 순서대로 방문하기 문제
public class Softeer6246 {
	static int N, M, CNT;
	static int[][] map, mVisit;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[n][j] = Integer.parseInt(st.nextToken());
			}
		}

		mVisit = new int[M][2]; // 입력의 순서=방문해야 하는 순서
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 2; i++) {
				mVisit[m][i] = Integer.parseInt(st.nextToken()) - 1;
			}
		}

		visited = new boolean[N][N];
		CNT = 0;
		dfs(mVisit[0][0], mVisit[0][1], 0);

		System.out.println(CNT);
	}

	static void dfs(int mvR, int mvC, int order) {
		if ((mvR == mVisit[order][0]) && (mvC == mVisit[order][1])) {
			if (order == M - 1) { // 마지막 방문지에 도착한 경우
				CNT++;
				return;
			}
			order++;
		}
		visited[mvR][mvC] = true;

		for (int d = 0; d < 4; d++) {
			int nr = mvR + dr[d];
			int nc = mvC + dc[d];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N) // map을 벗어나거나
				continue;
			if (visited[nr][nc]) // 이미 방문한 곳이거나
				continue;
			if (map[nr][nc] == 1) // 벽인 경우
				continue; // continue

			dfs(nr, nc, order);
		}
		visited[mvR][mvC] = false;
	}
}
