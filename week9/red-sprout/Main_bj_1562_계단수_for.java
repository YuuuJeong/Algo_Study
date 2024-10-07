import java.io.*;

public class Main_bj_1562_계단수_for {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int MAX = 1_000_000_000;
		int[][][] dp = new int[N + 1][1 << 10][10];
		for(int i = 1; i <= 9; i++) {
			dp[1][1 << i][i] = 1;
		}
		
		for(int i = 2; i <= N; i++) {
			for(int j = 0; j < (1 << 10); j++) {
				dp[i][j | (1 << 0)][0] =  (dp[i][j | (1 << 0)][0] + dp[i - 1][j][1]) % MAX;
			}
			for(int j = 1; j <= 8; j++) {
				for(int k = 0; k < (1 << 10); k++) {
					dp[i][k | (1 << j)][j] = (dp[i][k | (1 << j)][j] + dp[i - 1][k][j - 1]) % MAX;
					dp[i][k | (1 << j)][j] = (dp[i][k | (1 << j)][j] + dp[i - 1][k][j + 1]) % MAX;
				}
			}
			for(int j = 0; j < (1 << 10); j++) {
				dp[i][j | (1 << 9)][9] =  (dp[i][j | (1 << 9)][9] + dp[i - 1][j][8]) % MAX;
			}
		}
		
		int sum = 0;
		for(int i = 0; i <= 9; i++) {
			sum = (sum + dp[N][(1 << 10) - 1][i]) % MAX;
		}
		
		System.out.println(sum);
		br.close();
	}
}
