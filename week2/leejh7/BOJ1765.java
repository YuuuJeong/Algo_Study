import java.util.*;
import java.io.*;

public class BOJ1765 {

    static int find(int x, int[] par) {
        if(par[x] < 0) return x;
        par[x] = find(par[x], par);
        return par[x];
    }

    static void merge(int a, int b, int[] par) {
        a = find(a, par);
        b = find(b, par);

        if (a == b) return;

        if(par[a] < par[b]) {
            par[a] += par[b];
            par[b] = a;
        } else {
            par[b] += par[a];
            par[a] = b;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] par = new int[n + 1];
        Arrays.fill(par, -1);

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[n+1];
        for(int i=1; i<n+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd.equals("F")) {
                merge(a, b, par);
            } else {
                graph[a].add(b);
                graph[b].add(a);
            }
        }

        for(int i=1; i<n+1; i++) {
            for (int oIdx : graph[i]) {
                for(int fIdx : graph[oIdx]) {
                    if (i == fIdx) continue;
                    merge(i, fIdx, par);
                }
            }
        }

        int answer = 0;
        for(int i=1; i<n+1; i++) {
            if(par[i] < 0) {
                answer += 1;
            }
        }
        System.out.println(answer);
    }
}
