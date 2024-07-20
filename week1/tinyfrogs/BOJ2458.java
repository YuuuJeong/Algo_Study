package tinyfrogs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2458 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int result = 0;

        st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] visit = new int[N + 1][N + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            visit[x][y] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    if (visit[j][i] == 1 && visit[i][k] == 1) visit[j][k] = 1;
                }
            }
        }
        int[] counts = new int[N + 1];
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                if (visit[i][j] == 1 || visit[j][i] == 1) counts[i] += 1;

        for (int count : counts)
            if (count == N - 1) result++;
        sb.append(result).append("\n");
        System.out.println(sb);
        br.close();

    }

}
