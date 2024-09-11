import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int dirtyRoom = 1;
    static final int wall = -1;
    static final int[] dirX = {1, -1, 0, 0};
    static final int[] dirY = {0, 0, 1, -1};
    static int[][] map;
    static boolean[][][] visited;
    static int W, H, answer, totalDirtyRoom;
    static List<Point> dirtyPoints;

    private static class Robot {
        int x;
        int y;
        int count;
        int cleanMask; // 더러운 방을 청소한 상태를 비트마스크로 표현

        public Robot(int x, int y, int count, int cleanMask) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.cleanMask = cleanMask;
        }
    }

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if (W == 0 && H == 0) {
                break;
            }
            map = new int[H][W];
            dirtyPoints = new ArrayList<>();
            int startX = 0;
            int startY = 0;
            answer = Integer.MAX_VALUE;
            totalDirtyRoom = 0;

            for (int i = 0; i < H; i++) {
                String temp = bf.readLine();
                for (int j = 0; j < W; j++) {
                    char c = temp.charAt(j);
                    if (c == 'o') {
                        startX = i;
                        startY = j;
                    } else if (c == 'x') {
                        map[i][j] = wall;
                    } else if (c == '*') {
                        map[i][j] = dirtyRoom;
                        dirtyPoints.add(new Point(i, j));
                        totalDirtyRoom++;
                    }
                }
            }

            visited = new boolean[1 << totalDirtyRoom][H][W];
            bfs(startX, startY);
            sb.append(answer == Integer.MAX_VALUE ? -1 : answer).append('\n');
        }
        System.out.println(sb);
        bf.close();
    }

    private static void bfs(int startX, int startY) {
        LinkedList<Robot> queue = new LinkedList<>();
        queue.offer(new Robot(startX, startY, 0, 0));
        visited[0][startX][startY] = true;

        while (!queue.isEmpty()) {
            Robot poll = queue.poll();
            // 모든 더러운 방을 청소한 경우

            //이제, 이 값 (1 << totalDirtyRoom) - 1은 모든 더러운 방이 청소된 상태를 나타내는 비트마스크가 됩니다.
            // 즉, 모든 방이 청소되었다면 cleanMask는 111 (이진수)이고, 이는 7과 동일합니다.
            // ==> gpt 답변 : 즉 모든 수가 111 이란 뜻은 3개의 더러운 방이 있다고 생각하면 모든 방이 청소됨을 1로 표현되기 때문.
            if (poll.cleanMask == (1 << totalDirtyRoom) - 1) {
                answer = Math.min(answer, poll.count);
                return;
            }

            int curX = poll.x;
            int curY = poll.y;

            for (int i = 0; i < 4; i++) {
                int nextX = curX + dirX[i];
                int nextY = curY + dirY[i];

                if (nextX >= 0 && nextX < H && nextY >= 0 && nextY < W && map[nextX][nextY] != wall) {
                    int nextMask = poll.cleanMask;
                    if (map[nextX][nextY] == dirtyRoom) {
                        for (int k = 0; k < totalDirtyRoom; k++) {
                            Point p = dirtyPoints.get(k);
                            if (p.x == nextX && p.y == nextY) {
                                nextMask |= (1 << k); // 현재 더러운 방을 청소한 상태로 마스크 업데이트
                                break;
                            }
                        }
                    }

                    if (!visited[nextMask][nextX][nextY]) {
                        visited[nextMask][nextX][nextY] = true;
                        queue.offer(new Robot(nextX, nextY, poll.count + 1, nextMask));
                    }
                }
            }
        }
    }
}
