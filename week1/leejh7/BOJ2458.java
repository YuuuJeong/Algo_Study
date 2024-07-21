import java.util.*;
import java.io.*;

public class BOJ2458 {

    static int cnt = 0;

    static void dfs(boolean[] visited, int node, List<Integer>[] graph) {
        if (graph[node].isEmpty()) {
            return;
        }

        for (int v : graph[node]) {
            if (visited[v]) continue;
            visited[v] = true;
            cnt += 1;
            dfs(visited, v, graph);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] income = new List[N];
        List<Integer>[] outcome = new List[N];

        for(int i=0; i<N; i++) {
            income[i] = new ArrayList<>();
            outcome[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            income[b].add(a);
            outcome[a].add(b);
        }

        int answer = 0;
        for(int i=0; i<N; i++) {
            boolean[] visited = new boolean[N];
            visited[i] = true;
            cnt = 0;
            dfs(visited, i, income);
            int iCnt = cnt;
            cnt = 0;
            dfs(visited, i, outcome);
            int oCnt = cnt;
            if (iCnt + oCnt == N - 1) {
                answer += 1;
            }
        }

        System.out.println(answer);
    }
}
