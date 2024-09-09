import java.io.*;
import java.util.*;

public class BOJ_4991 {

    static BufferedReader br;
    static int[][] distance, dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int w, h, result;
    static String[][] map;
    static List<Point> dust;
    static boolean[] checked;
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            result = Integer.MAX_VALUE;
            if (w == 0 && h == 0) break;
            inputMap();

            if(!findDistance()) {
                sb.append(-1).append("\n");
                continue;
            }

            checked = new boolean[dust.size()];
            perm(0, 0, 0);

            sb.append(result).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static void inputMap() throws Exception {
        map = new String[h][w];
        dust = new ArrayList<>();

        for (int i = 0; i < h; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < w; j++) {
                map[i][j] = split[j];
                //로봇은 무조건 0번으로 저장한다 -> 실행해보니 상관없을듯
                if (map[i][j].equals("o")) dust.add(0, new Point(i, j));
                if(map[i][j].equals("*")) dust.add(new Point(i, j));
            }
        }
    }

    //먼지마다(로봇 포함) 거리를 다 구한다
    static boolean findDistance(){
        distance = new int[dust.size()][dust.size()];

        for(int i =0 ; i < dust.size(); i++){
            for(int j = i+1; j < dust.size(); j++){
                int move = bfs(dust.get(i), dust.get(j));
                if(move == -1) return false;
                distance[i][j] = distance[j][i] = move;
            }
        }

        return true;
    }

    //처음에서 시작하여 depth를 증가시키며 가장 최소가 되는 거리 구하기
    static int bfs(Point startP, Point endP){
        ArrayDeque<Point> q = new ArrayDeque<>();
        int depth = 1;
        boolean[][] visited = new boolean[h][w];
        visited[startP.x][startP.y] = true;
        q.offer(new Point(startP.x, startP.y));

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0 ;i < size ;i++){
                Point curP = q.poll();
                for(int d = 0; d < 4; d++){
                    int ni = curP.x + dir[d][0];
                    int nj = curP.y + dir[d][1];

                    if(ni == endP.x && nj == endP.y) return depth;

                    if(!isIngrid(ni, nj) || visited[ni][nj] || map[ni][nj].equals("x")) continue;
                    visited[ni][nj] = true;
                    q.offer(new Point(ni, nj));
                }
            }
            depth++;
        }
        //찾지 못하면 -1 반환
        return -1;
    }

    static boolean isIngrid(int i, int j){
        if(i >= 0 && i < h && j >= 0 && j < w) return true;
        return false;
    }

    static void perm(int cnt, int start, int length){
        if(length >= result) return;
        //개수가 넘어가면 실행
        if(cnt == dust.size()-1){
            result = Math.min(length, result);
            return;
        }
        
        //0번부터 size만큼 순서 정하기
        for(int i = 1 ; i < dust.size(); i++){
            if(checked[i]) continue;

            checked[i] = true;
            perm(cnt+1, i, length + distance[start][i]);
            checked[i] = false;
        }
    }


}
