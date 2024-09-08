import java.io.*;
import java.util.*;

public class Main {
    static int n, m, answer;
    static int[][] pos;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++) {
                visited[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

        pos = new int[m][2];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            pos[i][0] = Integer.parseInt(st.nextToken()) - 1;
            pos[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        answer = 0;
        visited[pos[0][0]][pos[0][1]] = true;
        dfs(pos[0][0], pos[0][1], 1);

        System.out.println(answer);
        br.close();
    }

    public static void dfs(int r, int c, int idx) {
        if(r == pos[m - 1][0] && c == pos[m - 1][1]) {
            answer++;
            return;
        }

        if(r == pos[idx][0] && c == pos[idx][1]) idx++;

        for(int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(isPropagation(nr, nc, idx)) {
                visited[nr][nc] = true;
                dfs(nr, nc, idx);
                visited[nr][nc] = false;
            }
        }
    }

    public static boolean isPropagation(int nr, int nc, int idx) {
        if(nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc]) return false;
        for(int i = 0; i < m; i++) {
            if(i == idx) continue;
            if(nr == pos[i][0] && nc == pos[i][1]) return false;
        }
        return true;
    }
}