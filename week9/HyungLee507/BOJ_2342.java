package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2342 {
    static int[][] map, dp;
    static int N, M;
    private static int[] dirX = {1, -1, 0, 0};
    private static int[] dirY = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        dfs(0, 0);
        System.out.println(dfs(0, 0));
    }

    public static int dfs(int x, int y) {
        // 도착지점까지 도달했을 경우
        if (x == N - 1 && y == M - 1) {
            return 1;
        }

        if (dp[x][y] == -1) {
            dp[x][y] = 0;
            for (int i = 0; i < 4; i++) {
                int nextX = x + dirX[i];
                int nextY = y + dirY[i];
                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && map[x][y] > map[nextX][nextY]) {
                    dp[x][y] += dfs(nextX, nextY);
                }
            }
        }
        return dp[x][y];
    }
}
