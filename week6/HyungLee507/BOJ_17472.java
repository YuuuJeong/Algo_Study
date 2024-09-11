import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int islandNumber = 1;
    static int[] parents;

    static ArrayList<ArrayList<int[]>> islandElements = new ArrayList<>();
    static ArrayList<int[]> elements;
    static int[] direction1 = {-1, 1, 0, 0};
    static int[] direction2 = {0, 0, 1, -1};
    static PriorityQueue<Edge> edges = new PriorityQueue<>();

    static class Edge implements Comparable<Edge> {
        int island1;
        int island2;
        int cost;

        public Edge(int island1, int island2, int cost) {
            this.island1 = island1;
            this.island2 = island2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());

        map = new int[row][column];
        visited = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < column; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j);
                    islandNumber++;
                    islandElements.add(elements);
                }
            }
        }
        parents = IntStream.range(0, islandNumber + 1).toArray();
        for (int i = 0; i < islandElements.size(); i++) {
            ArrayList<int[]> currentIslandComponents = islandElements.get(i);
            for (int j = 0; j < currentIslandComponents.size(); j++) {
                int x = currentIslandComponents.get(j)[0];
                int y = currentIslandComponents.get(j)[1];
                int now = map[x][y];
                for (int k = 0; k < 4; k++) {
                    int dir1 = direction1[k];
                    int dir2 = direction2[k];
                    int bridgeLength = 0;
                    while (x + dir1 >= 0 && x + dir1 < map.length && y + dir2 >= 0 && y + dir2 < map[x].length) {
                        if (map[x + dir1][y + dir2] == now) {
                            break;
                        } else if (map[x + dir1][y + dir2] != 0) {
                            if (bridgeLength > 1) {
                                edges.add(new Edge(now, map[x + dir1][y + dir2], bridgeLength));
                            }
                            break;
                        } else {
                            bridgeLength++;
                        }
                        if (dir1 < 0) {
                            dir1--;
                        } else if (dir2 < 0) {
                            dir2--;
                        } else if (dir1 > 0) {
                            dir1++;
                        } else if (dir2 > 0) {
                            dir2++;
                        }

                    }
                }

            }

        }
        int result = 0;
        int useEdge = 0;
        while (!edges.isEmpty()) {
            Edge poll = edges.poll();
            if (find(poll.island1) != find(poll.island2)) {
                union(poll.island1, poll.island2);
                result += poll.cost;
                useEdge++;
            }
        }
        if (useEdge == islandNumber - 2) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }


    }

    static void union(int a, int b) {
        int number1 = find(a);
        int number2 = find(b);

        if (number1 != number2) {
            parents[number2] = number1;
        }

    }

    static int find(int a) {
        if (parents[a] == a) {
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    static void bfs(int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        elements = new ArrayList<>();
        queue.add(new int[]{i, j});
        elements.add(new int[]{i, j});
        visited[i][j] = true;
        map[i][j] = islandNumber;
        while (!queue.isEmpty()) {
            int[] currentIndex = queue.poll();
            int x = currentIndex[0];
            int y = currentIndex[1];
            for (int k = 0; k < 4; k++) {
                int dir1 = direction1[k];
                int dir2 = direction2[k];
                while (dir1 + x >= 0 && dir1 + x < map.length && dir2 + y >= 0 && dir2 + y < map[x].length) {
                    if (map[dir1 + x][dir2 + y] != 0 && !visited[dir1 + x][dir2 + y]) {
                        elements.add(new int[]{dir1 + x, dir2 + y});
                        map[dir1 + x][dir2 + y] = islandNumber;
                        visited[dir1 + x][dir2 + y] = true;
                        queue.add(new int[]{dir1 + x, dir2 + y});
                    } else {
                        break;
                    }
                    if (dir1 < 0) {
                        dir1--;
                    } else if (dir2 < 0) {
                        dir2--;
                    } else if (dir1 > 0) {
                        dir1++;
                    } else if (dir2 > 0) {
                        dir2++;
                    }
                }
            }
        }
    }
}