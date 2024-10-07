import java.io.*;
import java.util.*;

public class Main_bj_2482_색상환_recur {
	static int N, K;
	static int[][] dp;
	static final int MAX = 1_000_000_003;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		dp = new int[N + 1][K + 1];
		for(int i = 0; i <= N; i++) Arrays.fill(dp[i], -1);
		dfs(N, K);
		System.out.println((dp[N - 3][K - 1] + dp[N - 1][K]) % MAX);
		br.close();
	}
	
	public static int dfs(int cur, int cnt) {
		if(cnt < 0) return 0;
		if(cur <= 0) return cnt == 0 ? 1 : 0;
		if(dp[cur][cnt] != -1) return dp[cur][cnt];
		dp[cur][cnt] = 0;
		dp[cur][cnt] = (dp[cur][cnt] + dfs(cur - 2, cnt - 1)) % MAX;
		dp[cur][cnt] = (dp[cur][cnt] + dfs(cur - 1, cnt)) % MAX;
		return dp[cur][cnt];
	}
}
