import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class BOJ_16954 {

    static char[][] map;
    static boolean[][] visited;

    //제자리, 우측부터 시계방향으로
    static int[][] dir = {
            {0, 0}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}
    };

    static class Human {
        int x, y;

        public Human(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[8][8];
        for (int i = 0; i < 8; i++) {
            map[i] = br.readLine().toCharArray();
        }

//        for(char[] c : map)
//            System.out.println(Arrays.toString(c));

        visited = new boolean[8][8];
        bfs();
    }


    static void bfs() {
        ArrayDeque<Human> queue = new ArrayDeque<>();
        queue.add(new Human(7, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();
            visited = new boolean[8][8];
            for (int i = 0; i < size; i++) {
                Human current = queue.poll();

                //벽이 존재하면 탐색불가
                if (map[current.x][current.y] == '#') continue;

                //벽이 없어지거나 목적지에 도달하면 통과
                if ((current.x == 0 && current.y == 7) || !existWall()) {
                    System.out.println(1);
                    return;
                }

                //팔방탐색 + 제자리
                for (int d = 0; d < 9; d++) {
                    int nx = current.x + dir[d][0];
                    int ny = current.y + dir[d][1];

                    if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8 && !visited[nx][ny] && map[nx][ny] != '#') {
                        queue.add(new Human(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
            //벽 움직이기
            moveWall();
        }

        System.out.println(0);
    }

    static void moveWall() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == '#') {
                    map[i][j] = '.';
                    if (i != 7) map[i + 1][j] = '#';
                }
            }
        }
    }

    static boolean existWall() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == '#')
                    return true;
            }
        }
        return false;
    }
}
