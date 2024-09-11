package leejh7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Soft_순서대로방문하기 {

    static int N, M, result;
    static int[][] board;
    static int[][] orders;
    static boolean[][] visited;

    static int[] dy = { -1, 0, 1, 0 };
    static int[] dx = { 0, 1, 0, -1 };

    static void dfs(int r, int c, int idx) {
        if (idx == orders.length) {
            result += 1;
            return;
        }

        for (int d = 0; d < 4; d++) {
            int rr = r + dy[d];
            int cc = c + dx[d];

            if (rr < 0 || rr >= N || cc < 0 || cc >= N)
                continue;
            if (visited[rr][cc])
                continue;
            if (board[rr][cc] == 1)
                continue;

            visited[rr][cc] = true;
            if (rr == orders[idx][0] && cc == orders[idx][1]) {
                dfs(rr, cc, idx + 1);
            } else {
                dfs(rr, cc, idx);
            }
            visited[rr][cc] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = 0;

        board = new int[N][N];
        orders = new int[M][2];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            orders[i][0] = Integer.parseInt(st.nextToken()) - 1;
            orders[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        visited[orders[0][0]][orders[0][1]] = true;
        dfs(orders[0][0], orders[0][1], 1);
        System.out.println(result);
    }

}

