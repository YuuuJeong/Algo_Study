import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1520 {

    static int M, N;
    static int[][] board;
    static int[][] dp;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static int dfs(int r, int c) {
        if (r == M - 1 && c == N - 1)
            return 1;

        if (dp[r][c] != -1)
            return dp[r][c];

        dp[r][c] = 0;
        for (int i = 0; i < 4; i++) {
            int rr = r + dy[i];
            int cc = c + dx[i];
            if (rr < 0 || rr >= M || cc < 0 || cc >= N)
                continue;
            if (board[rr][cc] < board[r][c]) {
                dp[r][c] += dfs(rr, cc);
            }
        }
        return dp[r][c];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }
}
