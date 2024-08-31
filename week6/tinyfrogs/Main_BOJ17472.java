
import java.io.*;
import java.util.*;

public class Main_BOJ17472 {

    static class Node implements Comparable<Node> {
        int to, from, length;

        public Node(int to, int from, int length) {
            this.to = to;
            this.from = from;
            this.length = length;
        }

        @Override
        public int compareTo(Node o) {
            return this.length - o.length;
        }
    }

    static int N, M, islandNum;
    static int[] parents;
    static int[][] map;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] checked;
    static Queue<int[]> q;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    //섬을 어떻게 구별할 것인가 -> 넘버링하기 1부터
    //
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        checked = new boolean[N][M];

        //강 -1, 판단안되는 섬 = 0
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken()) - 1;
        }

        //섬인곳 1, 2, 3씩 넘버링하기
        islandNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    checkedMap(i, j, islandNum);
                    islandNum++;
                }
            }
        }

        //다리를 만들어서 pq에 넣기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != -1)
                    createBridge(i, j, map[i][j]);
            }
        }

//        for (int[] m : map)
//            System.out.println(Arrays.toString(m));
//        while(!pq.isEmpty()) {
//            Node node = pq.poll();
//            System.out.println("from["+node.from + "] - to[" + node.to + "] length = " + node.length);
//        }

        // 1~ islandNum까지 parents 설정
        parents = new int[islandNum];
        for (int i = 1; i < islandNum; i++)
            parents[i] = i;

        //크루스칼 사용
        int result = kruskal();

        System.out.println(result);

        br.close();
    }

    //섬인곳 1, 2, 3씩 넘버링하기
    static void checkedMap(int i, int j, int islandNum) {
        q = new ArrayDeque<>();
        q.add(new int[]{i, j});
        map[i][j] = islandNum;
        checked[i][j] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];

            for (int d = 0; d < 4; d++) {
                int nrow = row + dir[d][0];
                int ncol = col + dir[d][1];

                if (nrow >= 0 && nrow < N && ncol >= 0 && ncol < M && !checked[nrow][ncol]) {
                    if (map[nrow][ncol] == 0) {
                        map[nrow][ncol] = islandNum;
                        checked[nrow][ncol] = true;
                        q.add(new int[]{nrow, ncol});
                    }
                }
            }
        }
    }

    //다리를 이을 수 있으면 이은다
    static void createBridge(int i, int j, int islandNum) {
        checked = new boolean[N][M];
        q = new LinkedList<>();
        for (int d = 0; d < 4; d++) {
            q.add(new int[]{i, j, 0});
            checked[i][j] = true;

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int move = cur[2];

                int nrow = cur[0] + dir[d][0];
                int ncol = cur[1] + dir[d][1];

                if (nrow >= 0 && nrow < N && ncol >= 0 && ncol < M && !checked[nrow][ncol] && map[nrow][ncol] != islandNum) {
                    //섬이면 pq에 정보를 넣는다
                    if (map[nrow][ncol] != -1) {
                        int from = islandNum;
                        int to = map[nrow][ncol];
                        if (move > 1) {
                            pq.add(new Node(to, from, move));
                            break;
                        }
                    }
                    //강이면 계속해서 이은다
                    else {
                        checked[nrow][ncol] = true;
                        q.add(new int[]{nrow, ncol, move + 1});
                    }

                }
            }
            q.clear();

        }
    }

    static int kruskal() {
        int sum = 0;
        int size = pq.size();
        for (int i = 0; i < size; i++) {
            Node node = pq.poll();
            int x = node.from;
            int y = node.to;

            if (find(x) != find(y)) {
                sum += node.length;
                union(x, y);
            }
        }

        //다 이어져 있다면 모든 parents의 부모 노드는 첫번째이다.
        int parent = parents[1];
        for (int i = 2; i < islandNum; i++)
            if (parent != find(parents[i]))
                return -1;

        return sum;
    }

    static int find(int x) {
        if (parents[x] == x)
            return x;

        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y)
            parents[y] = x;
        else
            parents[x] = y;
    }
}
