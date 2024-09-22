import java.io.*;
import java.util.*;

public class Main_bj_10844_쉬운계단수_recur {
	static int N, dp[][];
	static final int MAX = 1_000_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N][10];
		for(int i = 0; i < N; i++) Arrays.fill(dp[i], -1);
		System.out.println(dfs(0, 0));
		br.close();
	}
	
	public static int dfs(int cnt, int num) {
		if(cnt == N) return 1;
		
		if(dp[cnt][num] != -1) return dp[cnt][num];
		
		dp[cnt][num] = 0;
		if(cnt == 0) {
			for(int i = 1; i <= 9; i++) dp[cnt][num] = (dp[cnt][num] + dfs(cnt + 1, i)) % MAX;
			return dp[cnt][num];
		}
		
		if(num == 0) dp[cnt][num] = (dp[cnt][num] + dfs(cnt + 1, num + 1)) % MAX;
		else if(num == 9) dp[cnt][num] = (dp[cnt][num] + dfs(cnt + 1, num - 1)) % MAX;
		else {
			dp[cnt][num] = (dp[cnt][num] + dfs(cnt + 1, num + 1)) % MAX;
			dp[cnt][num] = (dp[cnt][num] + dfs(cnt + 1, num - 1)) % MAX;
		}
		
		return dp[cnt][num];
	}
}
