import java.io.*;
import java.util.*;

public class BOJ1043 {

    static int find(int x, int[] par) {
        if(par[x] < 0) {
            return x;
        }
        par[x] = find(par[x], par);
        return par[x];
    }

    static void merge(int a, int b, int[] par) {
        a = find(a, par);
        b = find(b, par);

        if (a == b) {
            return;
        }

        if(par[a] < par[b]) {
            par[a] += par[b];
            par[b] = a;
        } else {
            par[b] += par[a];
            par[a] = b;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] par = new int[N + 1];
        Arrays.fill(par, -1);

        st = new StringTokenizer(br.readLine());
        int[] tp = new int[Integer.parseInt(st.nextToken())];
        for(int i=0; i<tp.length; i++) {
            tp[i] = Integer.parseInt(st.nextToken());
        }

        int[][] parties = new int[M][];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            parties[i] = new int[Integer.parseInt(st.nextToken())];
            parties[i][0] = Integer.parseInt(st.nextToken());
            for(int j=1; j< parties[i].length; j++) {
                parties[i][j] = Integer.parseInt(st.nextToken());
                merge(parties[i][j-1], parties[i][j], par);
            }
        }

        int answer = M;
        for(int i=0; i<M; i++) {
            boolean flag = false;
            for(int j=0; j<parties[i].length; j++) {
                for(int k=0; k<tp.length; k++) {
                    if (find(parties[i][j], par) == find(tp[k], par)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) break;
            }
            if (flag) answer -= 1;
        }
        System.out.println(answer);
    }

}
