import java.io.*;

public class Main_bj_2342_DancDanceRevolution_for {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] order = br.readLine().split(" ");
		int size = order.length;
		int MAX = 400_001;
		int[][][] dp = new int[5][5][size];
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < size; k++) {
					dp[i][j][k] = MAX;
				}
			}
		}
		
		dp[0][0][0] = 0;
		for(int k = 1; k < size; k++) {
			int next = Integer.parseInt(order[k - 1]);
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 5; j++) {
					dp[i][next][k] = Math.min(dp[i][next][k], dp[i][j][k - 1] + getForce(j, next));
					dp[next][j][k] = Math.min(dp[next][j][k], dp[i][j][k - 1] + getForce(i, next));
				}
			}
		}
		
		int ans = MAX;
		int foot = Integer.parseInt(order[size - 2]);
		for(int i = 0; i < 5; i++) {
			ans = Math.min(ans, dp[i][foot][size - 1]);
			ans = Math.min(ans, dp[foot][i][size - 1]);
		}

		System.out.println(ans);
		br.close();
	}
	
	public static int getForce(int a, int b) {
		if(a == 0) return 2;
		if(a == b) return 1;
		if(Math.abs(a - b) == 2) return 4;
		return 3;
	}
}
