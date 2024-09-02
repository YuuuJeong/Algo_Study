import java.io.*;
import java.util.*;
 
class Solution {
    static int max, min;
    static int[] operators, numbers;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
         
        for(int test = 1; test <= T; test++) {
            sb.append("#").append(test).append(" ");
             
            int N = Integer.parseInt(br.readLine());
            operators = new int[4];
            numbers = new int[N];
             
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < 4; i++) operators[i] = Integer.parseInt(st.nextToken());
             
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < N; i++) numbers[i] = Integer.parseInt(st.nextToken());
             
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            calc(1, numbers[0]);
            sb.append(max - min).append("\n");
        }
         
        System.out.print(sb.toString());
        br.close();
    }
     
    public static void calc(int cnt, int result) {
        if(cnt == numbers.length) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }
         
        if(operators[0] > 0) {
            operators[0]--;
            calc(cnt + 1, result + numbers[cnt]);
            operators[0]++;
        }
         
        if(operators[1] > 0) {
            operators[1]--;
            calc(cnt + 1, result - numbers[cnt]);
            operators[1]++;
        }
         
        if(operators[2] > 0) {
            operators[2]--;
            calc(cnt + 1, result * numbers[cnt]);
            operators[2]++;
        }
         
        if(operators[3] > 0) {
            operators[3]--;
            calc(cnt + 1, result / numbers[cnt]);
            operators[3]++;
        }
    }
}
