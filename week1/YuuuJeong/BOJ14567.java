import java.io.*;
import java.util.*;

public class BOJ14567 {

    public static List<List<Integer>> graph = new ArrayList<List<Integer>>();
    public static int N, M;
    public static int[] indegree;
    public static int[] count;

    public static void tpSort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i = 0; i < graph.get(now).size(); i++) {
                int newIndex = graph.get(now).get(i);
                indegree[newIndex] -= 1;
                if (indegree[newIndex] == 0) {
                    q.offer(newIndex);
                    count[newIndex] = count[now] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indegree = new int[N + 1];
        count = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Integer>());
            count[i] = 1;
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
            indegree[B] += 1;
        }

        tpSort();

        for (int i = 1; i <= N; i++) {
            sb.append(count[i]).append(" ");
        }

        System.out.println(sb.toString());
    }
}
