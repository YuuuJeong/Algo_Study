import java.io.*;
import java.util.*;

public class BOJ1516 {

    static class Node {
        int v;
        int time;

        public Node(int v, int time) {
            this.v = v;
            this.time = time;
        }
    }

    static void tpSort(List<Integer>[] graph, int[] inDegree, int[] times, int N) {
        int[] result = new int[N];

        Queue<Node> q = new LinkedList<>();

        for(int i=0; i<N; i++) {
            if(inDegree[i] == 0) {
                q.offer(new Node(i, times[i]));
            }
        }

        while (!q.isEmpty()) {
            Node n = q.poll();

            result[n.v] += times[n.v];

            for (int v : graph[n.v]) {
                inDegree[v] -= 1;
                result[v] = Math.max(result[v], result[n.v]);

                if (inDegree[v] == 0) {
                    q.offer(new Node(v, result[v]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(result[i]);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[N];
        int[] inDegree = new int[N];
        int[] times = new int[N];

        for(int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            int v;
            while ((v = Integer.parseInt(st.nextToken())) != -1) {
                graph[v - 1].add(i);
                inDegree[i] += 1;
            }
        }

        tpSort(graph, inDegree, times, N);
    }
}
