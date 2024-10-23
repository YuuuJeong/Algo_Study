import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][][] map;
    static boolean[][][] visited;
    private static int N = 8;
    private static int[] dx = { -1, 1, 0, 0, 0, -1, -1, 1, 1, };
    private static int[] dy = { 0, 0, 1, -1, 0, -1, 1, -1, 1 };
    private static int desX = 0;
    private static int desY = 7;

    static class Location {
        int x, y, time;

        public Location(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[64][8][8];
        visited = new boolean[64][8][8];
        // init
        for (int i = 0; i < N; i++) {
            String temp = bf.readLine();
            for (int j = 0; j < N; j++) {
                if (temp.charAt(j) == '#') {
                    map[0][i][j] = 1;
                }
            }
        }
        // 시간대별 맵
        for (int t = 0; t < 8; t++) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    map[t + 1][i + 1][j] = map[t][i][j];
                }
            }
        }
        if (canMove()) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static boolean canMove() {

        Location temp = new Location(7, 0, 0);
        Queue<Location> queue = new ArrayDeque<>();
        queue.offer(temp);
        while (!queue.isEmpty()) {
            Location now = queue.poll();
            if (now.x == desX && now.y == desY) {
                return true;
            }
            for (int i = 0; i < 9; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                int nowTime = now.time;
                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && !visited[nowTime + 1][nextX][nextY]
                        && map[nowTime][nextX][nextY] == 0 && map[nowTime + 1][nextX][nextY] == 0) {
                    visited[nowTime + 1][nextX][nextY] = true;
//					visited[nowTime][nextX][nextY] = true;
                    queue.offer(new Location(nextX, nextY, nowTime + 1));
                }
            }
        }

        return false;
    }
}
