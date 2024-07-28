import java.util.*;
import java.io.*;

public class BOJ10775 {

    static int find(int x, int[] par) {
        if(par[x] == x) {
            return x;
        }
        par[x] = find(par[x], par);
        return par[x];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int G = Integer.parseInt(st.nextToken());

        int[] par = new int[G + 1];
        for(int i=0; i<G + 1; i++) {
            par[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int P = Integer.parseInt(st.nextToken());

        int answer = 0;
        for(int i=0; i<P; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int x = find(g, par);

            if(x == 0) {
                break;
            }
            answer += 1;
            par[x] = find(x-1, par);
        }
        System.out.println(answer);
    }
}
