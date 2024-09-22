import java.io.*;
import java.util.*;

public class BOJ_2169 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int[][] rlmap = new int[N][M];
        int[][] lrmap = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                rlmap[i][j] = lrmap[i][j] = map[i][j] = Integer.MIN_VALUE / 2;
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int j = 0; j < M - 1; j++) rlmap[0][j + 1] = lrmap[0][j + 1] = map[0][j + 1] += map[0][j];


        for (int i = 1; i < N; i++) {

            for (int j = 0; j < M; j++)
                if (j == 0) lrmap[i][j] = map[i - 1][j] + map[i][j];
                else lrmap[i][j] = Math.max(lrmap[i][j - 1], map[i - 1][j]) + map[i][j];

            for (int j = M - 1; j >= 0; j--)
                if (j == M - 1) rlmap[i][j] = map[i - 1][j] + map[i][j];
                else rlmap[i][j] = Math.max(rlmap[i][j + 1], map[i - 1][j]) + map[i][j];

            for (int j = 0; j < M; j++)
                lrmap[i][j] = rlmap[i][j] = map[i][j] = Math.max(lrmap[i][j], rlmap[i][j]);
        }
        System.out.println(map[N - 1][M - 1]);
    }
}
