import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class CodeTree_KnightDuel {
    static int[][] map;
    static int[] dirX = { -1, 0, 1, 0 };
    static int[] dirY = { 0, 1, 0, -1 };
    static int N, knightCount, order;
    static LinkedList<Knight> knights;

    static class Knight {
        int r;
        int c;
        int w;
        int h;
        int life;
        int preLife;

        public Knight(int r, int c, int w, int h, int life) {
            this.r = r;
            this.c = c;
            this.w = w;
            this.h = h;
            this.life = life;
            this.preLife = life;
        }

        private void move(int moveDir, boolean isFirst) {
            this.r += dirX[moveDir];
            this.c += dirY[moveDir];
            if (!isFirst) {
                this.life -= getCount();
            }
        }

        // 함정의 개수를 세는 메서드
        private int getCount() {
            int count = 0;
            for (int i = this.r; i < this.r + this.h; i++) {
                for (int j = this.c; j < this.c + this.w; j++) {
                    if (map[i][j] == 1) {
                        count++;
                    }
                }
            }
            return count;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("knight location is ").append(r).append(" ").append(c).append(" ").append(h).append(' ')
                    .append(w);
            return sb.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        knightCount = Integer.parseInt(st.nextToken());
        order = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        knights = new LinkedList<>();
        // map init
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // knight init
        for (int i = 0; i < knightCount; i++) {
            st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int life = Integer.parseInt(st.nextToken());
            knights.add(new Knight(r, c, w, h, life));
        }

        for (int i = 0; i < order; i++) {
            st = new StringTokenizer(bf.readLine());
            int knightIndex = Integer.parseInt(st.nextToken());
            int moveDir = Integer.parseInt(st.nextToken());
            // 죽은 기사에 인덱스로도 명령을 내릴 수 있음. -> 해당 경우 그냥 아무 것도 안함.
            if (knights.get(knightIndex - 1).life <= 0)
                continue;
            // 움직일 수 있는지 체크
            if (canMove(knightIndex, moveDir)) {
                // 움직일 수 있다면 움직이기
                move(knightIndex, moveDir, true);
            }
        }
        int answer = 0;
        for (Knight knight : knights) {
            if (knight.life > 0) {
                answer += (knight.preLife - knight.life);
            }

        }
        System.out.println(answer);
    }

    private static boolean canMove(int knightIndex, int moveDir) {
        Knight knight = knights.get(knightIndex - 1);
        if (reachWall(knightIndex, moveDir)) {
            return false;
        }
        if (reachBoundary(knightIndex, moveDir)) {
            return false;
        }
        for (int i = 0; i < knights.size(); i++) {
            if (knightIndex - 1 == i) {
                continue;
            }
            if (isNextKnight(knight, i + 1, moveDir)) {
                if (!canMove(i + 1, moveDir)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isNextKnight(Knight knight, int nextIndex, int moveDir) {
        Knight next = knights.get(nextIndex - 1);
        if (next.life < 0)
            return false;
        int r = next.r;
        int c = next.c;
        int h = next.h;
        int w = next.w;
        for (int i = knight.r + dirX[moveDir]; i < knight.r + knight.h + dirX[moveDir]; i++) {
            for (int j = knight.c + dirY[moveDir]; j < knight.c + knight.w + dirY[moveDir]; j++) {
                if (i >= r && i < (r + h) && j >= c && j < (c + w)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean reachBoundary(int knightIndex, int moveDir) {
        Knight knight = knights.get(knightIndex - 1);
        for (int i = knight.r + dirX[moveDir]; i < knight.r + knight.h + dirX[moveDir]; i++) {
            for (int j = knight.c + dirY[moveDir]; j < knight.c + knight.w + dirY[moveDir]; j++) {
                if (i < 1 || i > N || j < 1 || j > N) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean reachWall(int knightIndex, int moveDir) {
        Knight knight = knights.get(knightIndex - 1);

        for (int i = knight.r + dirX[moveDir]; i < knight.r + knight.h + dirX[moveDir]; i++) {
            for (int j = knight.c + dirY[moveDir]; j < knight.c + knight.w + dirY[moveDir]; j++) {
                if (i >= 1 && i <= N && j >= 1 && j <= N && map[i][j] == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void move(int knightIndex, int moveDir, boolean isFirst) {
        List<Knight> moveList = new ArrayList<>();
        moveList.add(knights.get(knightIndex - 1));
        boolean[] used = new boolean[knights.size() + 1];
        used[knightIndex] = true;
        ArrayDeque<Knight> queue = new ArrayDeque<>();
        queue.add(knights.get(knightIndex - 1));
        while (!queue.isEmpty()) {
            Knight poll = queue.poll();
            for (int i = 0; i < knights.size(); i++) {
                if (!used[i + 1] && isNextKnight(poll, i + 1, moveDir)) {
                    used[i + 1] = true;
                    Knight knight = knights.get(i);

                    queue.add(knights.get(i));
                    moveList.add(knight);
                }
            }
        }

        for (Knight tempKnight : moveList) {
            tempKnight.move(moveDir, isFirst);
            isFirst = false;
        }
    }

}