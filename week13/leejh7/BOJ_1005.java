import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1005 {

    static int N, K, W;
    static List<Integer>[] map;

    static int[] degree;
    static int[] time;
    static int[] res;

    static void topologySort() {
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                q.offer(i);
                res[i] = time[i];
            }
        }

        while (!q.isEmpty()) {
            int idx = q.poll();

            if (idx == W) {
                break;
            }

            for (int next : map[idx]) {
                if (--degree[next] == 0) {
                    q.offer(next);
                }
                res[next] = Math.max(res[next], res[idx] + time[next]);
            }
        }

        System.out.println(res[W]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                map[i] = new ArrayList<>();
            }

            degree = new int[N + 1];
            time = new int[N + 1];
            res = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                map[a].add(b);
                degree[b]++;
            }

            W = Integer.parseInt(br.readLine());

            topologySort();
        }

    }
}
