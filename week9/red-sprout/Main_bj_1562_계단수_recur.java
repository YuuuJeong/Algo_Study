import java.io.*;
import java.util.*;

public class Main_bj_1562_계단수_recur {
	static int N, dp[][][];
	static final int MAX = 1_000_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N][10][1 << 10];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 10; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		System.out.println(dfs(0, 0, 0));
		br.close();
	}
	
	public static int dfs(int cnt, int num, int visited) {
		if(cnt == N) return visited == (1 << 10) - 1 ? 1 : 0;
		
		if(dp[cnt][num][visited] != -1) return dp[cnt][num][visited];
		
		dp[cnt][num][visited] = 0;
		if(cnt == 0) {
			for(int i = 1; i <= 9; i++) dp[cnt][num][visited] = (dp[cnt][num][visited] + dfs(cnt + 1, i, visited | (1 << i))) % MAX;
			return dp[cnt][num][visited];
		}
		
		if(num == 0) dp[cnt][num][visited] = (dp[cnt][num][visited] + dfs(cnt + 1, num + 1, visited | (1 << (num + 1)))) % MAX;
		else if(num == 9) dp[cnt][num][visited] = (dp[cnt][num][visited] + dfs(cnt + 1, num - 1, visited | (1 << (num - 1)))) % MAX;
		else {
			dp[cnt][num][visited] = (dp[cnt][num][visited] + dfs(cnt + 1, num + 1, visited | (1 << (num + 1)))) % MAX;
			dp[cnt][num][visited] = (dp[cnt][num][visited] + dfs(cnt + 1, num - 1, visited | (1 << (num - 1)))) % MAX;
		}
		
		return dp[cnt][num][visited];
	}
}
