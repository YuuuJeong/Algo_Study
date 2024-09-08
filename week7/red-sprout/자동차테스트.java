import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] rate = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++) {
            rate[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(rate);
        for(int i = 0; i < q; i++) {
            int m = Integer.parseInt(br.readLine());
            int idx = Arrays.binarySearch(rate, m);
            
            if(idx < 0) sb.append(0);
            else sb.append(idx * (n - 1 - idx));
            sb.append("\n");
        }

        System.out.print(sb.toString());
        br.close();
    }
}