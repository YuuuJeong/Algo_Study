import java.io.*;
import java.util.*;

public class BOJ_11779 {

    static int n, m, start, end;
    static int[][] g;
    static long[] cost;
    static int[] preCity;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        g = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                g[i][j] = -1;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            if (g[to][from] == -1) g[to][from] = value;
            else g[to][from] = Math.min(g[to][from], value);
        }

        st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        preCity = new int[n + 1];
        cost = new long[n + 1];
        v = new boolean[n + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;

        dijkstra();

        System.out.println(cost[end]);
        findPreCity();
    }

    static void dijkstra() {
        for (int i = 1; i <= n; i++) {
            int minVertex = -1;
            long min = Integer.MAX_VALUE;

            for (int j = 1; j <= n; j++) {
                if (!v[j] && min > cost[j]) {
                    minVertex = j;
                    min = cost[j];
                }
            }
            // 도착지점이 무조건 존재하기 때문에 필요가 없다.
            // if(minVertex == -1) break;
            v[minVertex] = true;
            if (minVertex == end) break;
            for (int j = 1; j <= n; j++) {
                if (!v[j] && g[minVertex][j] != -1 && cost[j] > min + g[minVertex][j]) {
                    cost[j] = min + g[minVertex][j];
                    preCity[j] = minVertex;
                }
            }
        }
    }

    static void findPreCity() {
        ArrayDeque<Integer> s = new ArrayDeque<>();
        while (end != 0) {
            s.push(end);
            end = preCity[end];
        }
        System.out.println(s.size());
        while (!s.isEmpty())
            System.out.print(s.pop() + " ");
    }

}
