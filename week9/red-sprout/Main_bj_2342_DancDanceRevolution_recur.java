import java.io.*;
import java.util.*;

public class Main_bj_2342_DancDanceRevolution_recur {
	static int N, MAX = 400_001;
	static int[][][] dp;
	static List<Integer> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		list = new ArrayList<>();		

		int idx = 0;
		while((idx = Integer.parseInt(st.nextToken())) != 0) list.add(idx);

		N = list.size();
		dp = new int[N][5][5];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < 5; k++) {
					dp[i][j][k] = MAX;
				}
			}
		}
		
		System.out.println(dfs(0, 0, 0));
		br.close();
	}
	
	public static int dfs(int cur, int left, int right) {
		if(cur == N) return 0;
	
		if(dp[cur][left][right] != MAX) return dp[cur][left][right];
		
		int idx = list.get(cur);
		int a = dfs(cur + 1, idx, right) + getForce(left, idx);
		int b = dfs(cur + 1, left, idx) + getForce(right, idx);
		
		return dp[cur][left][right] = Math.min(a, b);
	}
	
	public static int getForce(int a, int b) {
		if(a == 0) return 2;
		if(a == b) return 1;
		if(Math.abs(a - b) == 2) return 4;
		return 3;
	}
}
