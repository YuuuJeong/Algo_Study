import java.io.*;
import java.util.*;

public class BOJ_6497 {

    static class Point {
        int to, from;
        long value;

        public Point(int to, int from, long value) {
            this.to = to;
            this.from = from;
            this.value = value;
        }
    }

    static long result;
    static int[] parents;
    static PriorityQueue<Point> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) break;
            result = 0;
            pq = new PriorityQueue<>((o1, o2) -> (int) o1.value - (int) o2.value);

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                result += value;
                pq.add(new Point(to, from, value));
            }

            parents = new int[m];
            for (int i = 0; i < m; i++) {
                parents[i] = i;
            }

            solve();
            sb.append(result).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static void solve() {

        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (find(p.to) == find(p.from)) continue;
            result -= p.value;
            union(p.to, p.from);
        }
    }

    static int find(int x) {
        if (parents[x] == x)
            return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b)
            return;

        if (a > b)
            parents[a] = b;
        else
            parents[b] = a;
    }

}
