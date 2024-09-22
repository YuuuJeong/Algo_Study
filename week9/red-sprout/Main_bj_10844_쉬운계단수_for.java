import java.io.*;

public class Main_bj_10844_쉬운계단수_for {
	static int N, dp[][];
	static final int MAX = 1_000_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N][10];
		for(int i = 1; i < 10; i++) {
			dp[0][i] = 1;
		}
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < 10; j++) {
				if(j == 0) dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MAX;
				else if(j == 9) dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MAX;
				else {
					dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MAX;
					dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MAX;
				}
			}
		}
		int answer = 0;
		for(int i = 0; i < 10; i++) {
			answer = (answer + dp[N - 1][i]) % MAX;
		}
		System.out.println(answer);
		br.close();
	}
}
