import java.io.*;
import java.util.*;
 
class Solution {
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우 0123
    static int[] dc = {0, 0, -1, 1};
    static int[][] block = {
            {0, 1, 2, 3},
            {1, 3, 0, 2},
            {3, 0, 1, 2},
            {2, 0, 3, 1},
            {1, 2, 3, 0},
            {1, 0, 3, 2}
    };
    static Map<Integer, List<int[]>> wormhole; 
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine().trim());
         
        for(int test = 1; test <= T; test++) {
            sb.append("#").append(test).append(" ");
            int N = Integer.parseInt(br.readLine().trim());
             
            map = new int[N + 2][N + 2];
            for(int i = 0; i < N + 2; i++) Arrays.fill(map[i], 5);
            wormhole = new HashMap<>();
             
            for(int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine().trim(), " ");
                for(int j = 1; j <= N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] >= 6) {
                        List<int[]> list = wormhole.getOrDefault(map[i][j], new ArrayList<>());
                        list.add(new int[] {i, j});
                        wormhole.put(map[i][j], list);
                    }
                }
            }
             
            int answer = 0;
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(map[i][j] == 0) {
                        map[i][j] = -1;
                        for(int d = 0; d < 4; d++) answer = Math.max(answer, move(i, j, d));
                        map[i][j] = 0;
                    }
                }
            }
             
            sb.append(answer).append("\n");
        }
 
        System.out.print(sb.toString());
        br.close();
    }
     
    public static int move(int row, int col, int d) {
        int score = 0;
        int now = 0;
        while(now != -1) {
            if(1 <= now && now <= 5) {
                score += 1;
                d = block[now][d];
            } else if(now >= 6) {
                List<int[]> holes = wormhole.get(now);
                for(int[] h : holes) {
                    if(h[0] != row || h[1] != col) {
                        row = h[0];
                        col = h[1];
                        break;
                    }
                }
            }
            row += dr[d];
            col += dc[d];
            now = map[row][col];
        }
        return score;
    }
}
