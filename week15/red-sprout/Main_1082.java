import java.io.*;
import java.util.*;

public class Main_1082 {
    static int N, M;
    static int[] P;
    static Map<Integer, String> memo;
    static String dfs(int money, String str) {
        if (memo.containsKey(money)) return memo.get(money);
        String result = "";
        for (int i = 0; i < N; i++) {
            if (money + P[i] > M) continue;
            String newStr = dfs(money + P[i], str + i) + i;
            result = max(result, newStr);
        }
        memo.put(money, result);
        return result;
    }
    static String max(String str1, String str2) {
        int l1 = len(str1), l2 = len(str2);
        if (l1 > l2) return str1;
        if (l1 < l2) return str2;
        int s1 = str1.length() - l1;
        int s2 = str2.length() - l2;
        for (int i = 0; i < l1; i++) {
            char c1 = str1.charAt(s1 + i);
            char c2 = str2.charAt(s2 + i);
            if (c1 > c2) return str1;
            if (c1 < c2) return str2;
        }
        return str1;
    }
    static int len(String str) {
        int result = str.length();
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) != '0') break;
            result--;
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        P = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        memo = new HashMap<>();
        String answer = dfs(0, "");
        System.out.println(answer.substring(answer.length() - len(answer)));
        br.close();
    }
}
