

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17472 {
	static int N, M;
    static int[][] map;
    static int[][] dist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static int islandCount = 0;

    static class Edge implements Comparable<Edge> {
        int start, end, cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static List<Edge> edges = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬에 번호 매기기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    islandCount++;
                    bfs(i, j, islandCount);
                }
            }
        }

        // 섬 간 최소 거리 계산
        dist = new int[islandCount + 1][islandCount + 1];
        for (int i = 1; i <= islandCount; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    connectBridges(i, j);
                }
            }
        }

        // MST 생성
        parent = new int[islandCount + 1];
        for (int i = 1; i <= islandCount; i++) {
            parent[i] = i;
        }

        Collections.sort(edges);

        int result = 0;
        int count = 0;

        for (Edge edge : edges) {
            if (union(edge.start, edge.end)) {
                result += edge.cost;
                count++;
                if (count == islandCount - 1) break;
            }
        }

        if (count == islandCount - 1) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    // BFS를 이용해 섬에 번호를 부여
    static void bfs(int x, int y, int num) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        map[x][y] = num;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    map[nx][ny] = num;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    // 섬 간의 다리 연결
    static void connectBridges(int x, int y) {
        int fromIsland = map[x][y];

        for (int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            int length = 0;

            while (true) {
                nx += dx[i];
                ny += dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
                if (map[nx][ny] == fromIsland) break;
                if (map[nx][ny] > 0) {
                    int toIsland = map[nx][ny];
                    if (length >= 2) {
                        if (dist[fromIsland][toIsland] > length) {
                            dist[fromIsland][toIsland] = length;
                            dist[toIsland][fromIsland] = length;
                            edges.add(new Edge(fromIsland, toIsland, length));
                        }
                    }
                    break;
                }
                length++;
            }
        }
    }

    // Union-Find 알고리즘
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootY] = rootX;
            return true;
        }
        return false;
    }
}