import java.io.*;
import java.util.*;

public class  Main_bj_4991_로봇청소기 {
    static int minDistance = Integer.MAX_VALUE;
    static int W, H;
    static int startR, startC;
    static List<int[]> dirtySquares;
    static int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static char[][] grid;
    static int[][] dist;
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        while (true) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if (W == 0 && H == 0) break;
            
            dirtySquares = new ArrayList<>();
            grid = new char[H][W];
            minDistance = Integer.MAX_VALUE;
            
            for (int r = 0; r < H; r++) {
                String s = br.readLine();
                for (int c = 0; c < W; c++) {
                    grid[r][c] = s.charAt(c);
                    if (grid[r][c] == 'o') {
                        startR = r;
                        startC = c;
                    } else if (grid[r][c] == '*') {
                        dirtySquares.add(new int[]{r, c});
                    }
                }
            }
            
            int n = dirtySquares.size();
            dist = new int[n + 1][n + 1]; // 출발지 + 더러운 칸들
            boolean impossible = false;
            
            for (int i = -1; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int distance;
                    if (i == -1) distance = bfs(startR, startC, dirtySquares.get(j));
                    else distance = bfs(dirtySquares.get(i)[0], dirtySquares.get(i)[1], dirtySquares.get(j));
                    
                    if (distance == -1) {
                        impossible = true;
                        break;
                    }
                    
                    dist[i + 1][j + 1] = dist[j + 1][i + 1] = distance;
                }
                if (impossible) break;
            }
            
            if (impossible) {
                System.out.println(-1);
                continue;
            }
            
            visited = new boolean[n];
            permute(0, -1, 0, 0);
            System.out.println(minDistance == Integer.MAX_VALUE ? -1 : minDistance);
        }
        br.close();
    }
    
    private static void permute(int cnt, int prev, int currentDist, int visitedCount) {
        if (visitedCount == dirtySquares.size()) {
            minDistance = Math.min(minDistance, currentDist);
            return;
        }
        
        for (int i = 0; i < dirtySquares.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                int nextDist = currentDist + dist[prev + 1][i + 1];
                if (nextDist < minDistance) {
                    permute(cnt + 1, i, nextDist, visitedCount + 1);
                }
                visited[i] = false;
            }
        }
    }

    private static int bfs(int sr, int sc, int[] dest) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[H][W];
        
        q.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], d = cur[2];
            
            if (r == dest[0] && c == dest[1]) return d;
            
            for (int[] dir : direction) {
                int nr = r + dir[0], nc = c + dir[1];
                if (isInGrid(nr, nc) && !visited[nr][nc] && grid[nr][nc] != 'x') {
                    q.offer(new int[]{nr, nc, d + 1});
                    visited[nr][nc] = true;
                }
            }
        }
        return -1;
    }
    
    private static boolean isInGrid(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W;
    }
}
