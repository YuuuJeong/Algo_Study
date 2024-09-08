import java.io.*;
import java.util.*;

public class Main_HSAT_6246 {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static List<Point> points;
    static int n, m, result;
    static int[][] map, dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        points = new ArrayList<>();
        result = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            points.add(new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
        }

        map[points.get(0).x][points.get(0).y] = 1;
        dfs(points.get(0), 0);

        System.out.print(result);
    }

    static void dfs(Point point, int index) {
        //현재 순서의 지점을 마주치면
        if (points.get(index).x == point.x && points.get(index).y == point.y) {
            //마지막 지점까지 갔으면 증가시키고 return
            if (points.size() - 1 == index) {
                result++;
                return;
            }
            index++;
        }

        //사방탐색
        for (int d = 0; d < 4; d++) {
            int nx = point.x + dir[d][0];
            int ny = point.y + dir[d][1];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] != 1) {
                map[nx][ny] = 1;
                dfs(new Point(nx, ny), index);
                map[nx][ny] = 0;
            }
        }
    }
}
