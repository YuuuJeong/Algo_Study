import java.io.*;

public class Main_bj_2482_색상환_for {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int MAX = 1_000_000_003;
		int[][] dp = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++){
        dp[i][0] = 1;
        dp[i][1] = i;
    }
		for(int i = 3; i <= N; i++){
        for(int j = 2; j <= (i + 1) / 2; j++){
            dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MAX;
        }
    }
		System.out.println((dp[N - 3][K - 1] + dp[N - 1][K]) % MAX);
		br.close();
	}
}
