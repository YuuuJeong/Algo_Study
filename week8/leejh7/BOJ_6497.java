import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_6497 {

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int dist;

        Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }

    static int N, M;
    static List<Edge> edges;
    static int[] parents;

    static int find(int x) {
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return;
        if(a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    static void makeParents() {
        parents = new int[N];
        for(int i=0; i<N; i++) {
            parents[i] = i;
        }
    }

    static int kruscal(int max) {
        int res = 0;

        int cnt = 0;
        for (Edge edge : edges) {
            if(find(edge.from) == find(edge.to)) continue;

            union(edge.from, edge.to);
            cnt += 1;
            res += edge.dist;
            if(cnt == N - 1) break;
        }

        return max - res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            makeParents();
            edges = new ArrayList<>();

            int max = 0;
            for(int i=0; i<M; i++) {
                int x, y, z;
                st = new StringTokenizer(br.readLine());

                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                z = Integer.parseInt(st.nextToken());
                max += z;
                edges.add(new Edge(x, y, z));
            }

            Collections.sort(edges);

            sb.append(kruscal(max)).append("\n");
        }
        System.out.println(sb.toString());
    }
}
