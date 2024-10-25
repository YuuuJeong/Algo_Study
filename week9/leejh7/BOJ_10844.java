import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_10844 {

    static long[][] dp = new long[101][10];
    static int N;
    static final int MOD = 1000000000;

    static void solution() {

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j + 1];
                } else if (j == 9) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
                dp[i][j] %= MOD;
            }
        }

        long result = 0;
        for (int j = 0; j <= 9; j++) {
            result = (result + dp[N][j]) % MOD;
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp[1][0] = 0;
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        solution();
    }
}
