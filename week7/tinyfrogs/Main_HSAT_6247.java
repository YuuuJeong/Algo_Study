import java.io.*;
import java.util.*;

public class Main_HSAT_6247 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        Map<Integer, Integer> midCount = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n ; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        for(int i = 0; i < n; i++) midCount.put(arr[i], i * (n - 1 - i));

        for(int i = 0 ; i < q; i++){
            int key = Integer.parseInt(br.readLine());
            sb.append(midCount.getOrDefault(key, 0)).append("\n");
        }

        System.out.print(sb);
    }
}
