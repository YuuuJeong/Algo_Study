import java.io.*;
import java.util.*;

public class Main {
    static int[] dirX = {0,0,1,-1};
    static int[] dirY = {1,-1,0,0};
    static int[][] map;
    static int n, m;
    static int answer;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        visited = new boolean[n+1][n+1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(bf.readLine());
            for(int j=1; j<=n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        List<int[]> points = new ArrayList<>();
        for(int i=0; i<m; i++){
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points.add(new int[]{x,y});
        }
        int[] start = points.get(0);
        answer = 0;
        visited[start[0]][start[1]] = true;
        dfs(start[0], start[1], points, 1);
        System.out.println(answer);
    }
    private static void dfs(int x, int y, List<int[]> points, int depth){
        int nextDepth = depth;
        if(points.get(depth)[0] == x && points.get(depth)[1] == y){
            if(depth == (m-1)){
                answer++;
                return;
            }else{
                nextDepth++;
            }
        }


        for(int i=0; i<4; i++){
            int nextX = dirX[i] + x;
            int nextY = dirY[i] + y;
            if(nextX >=1 && nextX <=n && nextY>=1 && nextY<=n &&
                    !visited[nextX][nextY] && map[nextX][nextY]==0){
                visited[nextX][nextY] = true;
                dfs(nextX, nextY, points,nextDepth);
                visited[nextX][nextY] = false;
            }
        }


    }
}