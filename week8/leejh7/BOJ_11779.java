import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11779 {

    static int N, M, start, end;
    static int[][] graph;
    static int[] cost;
    static int[] path;

    static StringBuilder sb = new StringBuilder();

    static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        pq.offer(new int[]{start, 0});
        cost[start] = 0;

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int from = p[0];
            int dist = p[1];

            if(cost[from] < dist) continue;
            for(int i=1; i<=N; i++) {
                if(graph[from][i] == -1) continue;
                int nextDist = dist + graph[from][i];
                if(nextDist < cost[i]) {
                    cost[i] = nextDist;
                    pq.offer(new int[]{i, nextDist});
                    path[i] = from;
                }
            }
        }

        sb.append(cost[end]).append("\n");
    }

    static void getPath() {
        int cnt = 1, cur = end;
        List<Integer> res = new ArrayList<>();
        res.add(end);

        while (cur != start) {
            int next = path[cur];
            res.add(next);
            cur = next;
            cnt += 1;
        }

        sb.append(cnt).append("\n");
        for(int i=res.size() - 1; i>=0; i--) {
            sb.append(String.format("%d ", res.get(i)));
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];
        cost = new int[N + 1];
        path = new int[N + 1];
        Arrays.fill(cost, 100_000 * 1000 + 1);

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                graph[i][j] = -1;
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if(graph[start][to] == -1) {
                graph[start][to] = dist;
            } else {
                graph[start][to] = Math.min(dist, graph[start][to]);
            }
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra();
        getPath();
        System.out.println(sb.toString());
    }
}
