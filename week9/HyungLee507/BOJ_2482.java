package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2482 {

    static long MOD = 1000000003L;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int K = Integer.parseInt(bf.readLine());
        // 이럴 경우는 무조건 인접하게 가져가기 때문.
        if (K > N / 2) {
            System.out.println(0);
            return;
        }

        dp = new long[N + 1][K + 1];

        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
        }
        // 앞에서부터 순서대로 i개의 색상 중에서 j개의 색을 선택할 때의 경우의 수 (이건 원형인 것을 고려하지 않음)
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= K; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
            }
        }
        // 이제 원형인걸 고려.(찻번째와 마지막은 연결되어 있으니...)
        // 첫 번째 색을 선택하고 K-1개의 색을 나머지에서 선택-> N-3 인 이유는 첫번째 마지막 두개를 지워야 되기 때문.
        long case1 = dp[N - 3][K - 1];
        // 첫 번째 색을 선택하지 않고 K개의 색을 나머지에서 선택
        long case2 = dp[N - 1][K];

        long result = (case1 + case2) % MOD;
        System.out.println(result);
    }
}
