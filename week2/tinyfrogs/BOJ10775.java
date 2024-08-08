package tinyfrogs;

import java.io.*;

public class BOJ10775 {
    static int[] gate;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        gate = new int[G + 1];
        int result = 0;

        for (int i = 0; i <= G; i++) {
            gate[i] = i;
        }
        for (int i = 0; i < P; i++) {
            int airplane = Integer.parseInt(br.readLine());
            //System.out.println(Arrays.toString(gate));
            int gateCount = find(airplane);
            if (gateCount == 0)
                break;
            result++;
            union(gateCount, gateCount - 1);
            //System.out.println(Arrays.toString(gate));
        }

        sb.append(result);
        System.out.print(sb);

        br.close();
    }

    static int find(int x) {
        if (gate[x] == x)
            return x;
        gate[x] = find(gate[x]);
        return gate[x];
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y)
            gate[x] = y;

    }

}
