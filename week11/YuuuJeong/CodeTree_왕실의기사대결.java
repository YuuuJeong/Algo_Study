import java.io.*;
import java.util.*;

public class CodeTree_왕실의기사대결 {

    static class Knight {
        int r, c, w, h, k, damaged;
        boolean isDead;

        public Knight(int r, int c, int h, int w, int k) {
            this.r = r;
            this.c = c;
            this.w = w;
            this.h = h;
            this.k = k;
            this.damaged = 0;
            this.isDead = false;
        }
    }

    static Knight[] knights;
    static int[][] grid;
    static int N, L, Q;
    static int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int T = 1;
        for (int t = 1; t <= T; t++) {
            initializeGame();
            solution();
        }
    }

    private static void initializeGame() throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        grid = new int[L + 1][L + 1];

        for (int r = 1; r <= L; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= L; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken()); // 0 빈칸, 1 함정, 2 벽
            }
        }

        knights = new Knight[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            knights[i] = new Knight(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }
    }

    public static void solution() throws Exception {
        int cnt = 0;

        while (cnt < Q) {
            executeMove();
            cnt++;
        }

        int ans = calculateTotalDamage();
        System.out.println(ans);
    }

    private static void executeMove() throws IOException {
        st = new StringTokenizer(br.readLine());
        int knightId = Integer.parseInt(st.nextToken());
        int targetDir = Integer.parseInt(st.nextToken());

        if (!knights[knightId].isDead) {
            knightMove(knightId, targetDir, true, new ArrayList<>(), new HashSet<>());
        }
    }

    private static int calculateTotalDamage() {
        int totalDamage = 0;
        for (Knight knight : knights) {
            if (knight != null && !knight.isDead) {
                totalDamage += knight.damaged;
            }
        }
        return totalDamage;
    }

    public static boolean knightMove(int id, int dir, boolean isFirst, List<int[]> needToPush, HashSet<Integer> targetIds) {
        int nextStartR = knights[id].r + directions[dir][0];
        int nextStartC = knights[id].c + directions[dir][1];

        targetIds.add(id);
        int damaged = checkMovementArea(id, nextStartR, nextStartC, dir, needToPush, targetIds);

        if (damaged == -1) return false; // 이동 불가 시 -1 반환으로 처리

        needToPush.add(new int[]{id, damaged});
        if (isFirst) applyMovement(needToPush, dir, id);

        return true;
    }

    private static int checkMovementArea(int id, int nextR, int nextC, int dir, List<int[]> needToPush, HashSet<Integer> targetIds) {
        int totalDamage = 0;
        for (int r = nextR; r < nextR + knights[id].h; r++) {
            for (int c = nextC; c < nextC + knights[id].w; c++) {
                if (!isInGrid(r, c) || grid[r][c] == 2) {
                    return -1;
                } else if (grid[r][c] == 1) {
                    totalDamage++;
                }
                if (isKnightOverlap(id, r, c, dir, needToPush, targetIds)) {
                    return -1;
                }
            }
        }
        return totalDamage;
    }

    private static boolean isKnightOverlap(int id, int r, int c, int dir, List<int[]> needToPush, HashSet<Integer> targetIds) {
        for (int i = 1; i <= N; i++) {
            if (i == id || knights[i].isDead || targetIds.contains(i)) continue;
            if (isOverlapWithOtherKnight(knights[i], r, c) && !knightMove(i, dir, false, needToPush, targetIds)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isOverlapWithOtherKnight(Knight knight, int r, int c) {
        return r >= knight.r && r < knight.r + knight.h && c >= knight.c && c < knight.c + knight.w;
    }

    private static void applyMovement(List<int[]> needToPush, int dir, int mainId) {
        for (int[] target : needToPush) {
            int i = target[0];
            int dmg = target[1];
            knights[i].r += directions[dir][0];
            knights[i].c += directions[dir][1];
            if (mainId != i) {
                knights[i].damaged += dmg;
                if (knights[i].damaged >= knights[i].k) {
                    knights[i].isDead = true;
                }
            }
        }
    }

    public static boolean isInGrid(int r, int c) {
        return r > 0 && r <= L && c > 0 && c <= L;
    }
}
