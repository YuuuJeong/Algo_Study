import java.util.*;
import java.io.*;

public class BOJ_1520 {

    static int M, N, arr[][], dp[][], dir[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        System.out.print(dfs(0, 0));
    }

    static int dfs(int i, int j) {
        //도착지점 도달
        if (i == M - 1 && j == N - 1) return 1;

        //방문을 안했으면 경로 탐색, 했으면 바로 반환
        if (dp[i][j] == -1) {
            dp[i][j] = 0;
            for (int d = 0; d < 4; d++) {
                int di = i + dir[d][0];
                int dj = j + dir[d][1];

                if (di >= 0 && di < M && dj >= 0 && dj < N && arr[i][j] > arr[di][dj])
                    dp[i][j] += dfs(di, dj);
            }
        }

        return dp[i][j];
    }
}