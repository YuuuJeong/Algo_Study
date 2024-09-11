import java.io.*;
import java.util.*;

public class BOJ14567 {

    static void tpSort(int[] inDegree, List<Integer>[] graph, int N, int M) {
        int[] result = new int[N];
        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<N; i++) {
            if(inDegree[i] == 0) {
                q.offer(i);
                result[i] = 1;
            }
        }

        for (int i = 0; i < N; i++) {
            if (q.isEmpty()) return;

            int u = q.poll();

            for(int v : graph[u]) {
                inDegree[v] -= 1;
                if (inDegree[v] == 0) {
                    q.offer(v);
                    result[v] = result[u] + 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(result[i]);
            sb.append(" ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] inDegree = new int[N];
        List<Integer>[] graph = new List[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) -1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            inDegree[b] += 1;
            graph[a].add(b);
        }

        tpSort(inDegree, graph, N, M);
    }
}
