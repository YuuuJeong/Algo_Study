import java.io.*;
import java.util.*;

public class BOJ_17472 {
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static final String SEPARATOR = " ";
    private static final int MAX = 987_654_321;

    private static int[] parents;
    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stz = new StringTokenizer(br.readLine(), SEPARATOR);

        n = Integer.parseInt(stz.nextToken());
        m = Integer.parseInt(stz.nextToken());

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            stz = new StringTokenizer(br.readLine(), SEPARATOR);
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stz.nextToken());
            }
        }

        // 1. 각 섬을 넘버링 한다.
        boolean[][] visited = new boolean[n][m];
        int lands = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    transLandNumber(map, visited, i, j, lands);
                    lands++;
                }
            }
        }

        // 다른 섬을 연결하는 다리
        int[][] allBridgeSize = new int[lands][lands];
        for (int i = 1; i < lands; i++) {
            Arrays.fill(allBridgeSize[i], MAX);
        }

        // 2. 각 섬의 외각(가장자리) 부터 시작하여 
        // 다른 섬으로 갈 수 있는 모든 다리의 정보를 구한다.
        makeAllBrideSize(map, allBridgeSize);

        parents = new int[lands];
        for (int i = 1; i < lands; i++) {
            parents[i] = i;
        }

        // 이차원 배열에 대각선을 기준으로 위, 아래가 같은 값이 저장되어있음
        // 때문에, 대각선 위쪽만의 정보를 저장함
        List<Bridge> bridges = getBridges(lands, allBridgeSize);

        // 3. 다리의 크기를 기준으로 오름차순 정렬한다.
        Collections.sort(bridges);

        // 4. 가장 작은 다리부터 선택하여 union-find로 각 섬을 연결한다.
        int answer = minimumLandConnect(bridges);

        // 5. 모든 섬이 연결되지 않으면 -1을 반환한다.
        answer = isAllConnected(lands, answer);

        bw.write(String.valueOf(answer));

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean inRange(int x, int y) {
        return (x >= 0 && y >= 0 && x < n && y < m);
    }

    private static void transLandNumber(int[][] map, boolean[][] visited, int i, int j, int land) {
        Queue<int[]> qu = new LinkedList<>();
        qu.add(new int[]{i, j});
        visited[i][j] = true;
        map[i][j] = land;

        while (!qu.isEmpty()) {
            int[] cn = qu.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cn[0] + dx[d];
                int ny = cn[1] + dy[d];

                if (!inRange(nx, ny) || map[nx][ny] == 0 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                map[nx][ny] = land;
                qu.add(new int[]{nx, ny});
            }

        }
    }

    private static void makeAllBrideSize(int[][] map, int[][] allBridgeSize) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) { // 땅일경우
                    int startLand = map[i][j];

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        // 바다가 아니면 스킵
                        if (!inRange(nx, ny) || map[nx][ny] != 0) continue;

                        int tx = nx;
                        int ty = ny;
                        int size = 0;

                        // 범위를 벗어날때 까지 같은 방향으로 계속 이동하면서 다리의 크기를 구함
                        while (inRange(tx, ty)) {
                            int next = map[tx][ty];
                            // 다른 섬을 연결할 수 있는 다리를 만들었다면 최소 갱신
                            if (next > 0 && next != startLand && size > 1) {
                                allBridgeSize[startLand][next] =
                                        Math.min(allBridgeSize[startLand][next], size);
                                allBridgeSize[next][startLand] =
                                        Math.min(allBridgeSize[next][startLand], size);
                                break;
                            } else if (startLand > 0 && next > 0 && startLand != next && size < 2) {
                                // 다른 땅에 도착했지만, 다리의 크기가 만족되지 않을 경우
                                break;
                            } else if (map[i][j] == map[tx][ty]) {
                                // 바다를 거쳐 다시 같은 땅인 경우
                                break;
                            }

                            tx += dx[d];
                            ty += dy[d];
                            size++;
                        }

                    }
                }
            }
        }
    }

    private static List<Bridge> getBridges(int lands, int[][] allBridgeSize) {
        List<Bridge> bridges = new ArrayList<>();
        for (int i = 1; i < lands; i++) {
            for (int j = i + 1; j < lands; j++) {
                if (allBridgeSize[i][j] != MAX) {
                    bridges.add(new Bridge(i, j, allBridgeSize[i][j]));
                }
            }
        }
        return bridges;
    }

    private static boolean union(int land1, int land2) {
        int root1 = find(land1);
        int root2 = find(land2);

        if (root1 == root2) return false;

        if (root1 > root2) {
            parents[root1] = root2;
        } else {
            parents[root2] = root1;
        }

        return true;
    }

    private static int find(int land) {
        if (land == parents[land]) {
            return land;
        }
        return parents[land] = find(parents[land]);
    }

    private static int minimumLandConnect(List<Bridge> bridges) {
        int answer = 0;
        for (Bridge bridge : bridges) {
            if (union(bridge.start, bridge.end)) {
                answer += bridge.size;
            }
        }
        return answer;
    }

    private static int isAllConnected(int lands, int answer) {
        int a = find(1);
        for (int i = 2; i < lands; i++) {
            if (a != find(i)) {
                answer = -1;
                break;
            }
        }
        return answer;
    }

    private static class Bridge implements Comparable<Bridge> {
        int start, end, size;

        public Bridge(int start, int end, int size) {
            this.start = start;
            this.end = end;
            this.size = size;
        }

        @Override
        public int compareTo(Bridge o) {
            return this.size - o.size;
        }
    }
}
