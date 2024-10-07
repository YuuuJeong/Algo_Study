import java.io.*;

public class BOJ_10844 {
    static long dp[][];
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1][10];

        init();
        findStairNum();
        System.out.print(getCount());
    }

    //1의 자리 계단 수 갯수
    private static void init() {
        for (int i = 0; i <= 9; i++) dp[1][i] = 1;
    }

    //2의 자리 이상부터 계단 수 구하기
    private static void findStairNum() {
        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][1];
            for (int j = 1; j <= 9; j++) {
                if (j == 9) dp[i][9] = dp[i - 1][8] % 1000000000;
                else dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
            }
        }
    }

    //N의 자리 계단 수의 합계 구하기
    static long getCount() {
        long sum = 0;
        for (int i = 1; i <= 9; i++) sum = (sum + dp[N][i]) % 1000000000;
        return sum;
    }
}
