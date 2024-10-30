import java.io.*;
import java.util.*;

public class Main_bj_2169_로봇조종하기_for {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N][M], dpL = new int[N][M], dpR = new int[N][M];
		
		dp[0][0] = map[0][0];
		for(int i = 1; i < M; i++) dp[0][i] = map[0][i] + dp[0][i - 1];
		
		for(int i = 1; i < N; i++) {
			dpL[i][0] = dp[i - 1][0] + map[i][0];
			for(int j = 1; j < M; j++) dpL[i][j] = Math.max(dp[i - 1][j] + map[i][j], dpL[i][j - 1] + map[i][j]);
			
			dpR[i][M - 1] = dp[i - 1][M - 1] + map[i][M - 1];
			for(int j = M - 2; j >= 0; j--) dpR[i][j] = Math.max(dp[i - 1][j] + map[i][j], dpR[i][j + 1] + map[i][j]);
			
			for(int j = 0; j < M; j++) dp[i][j] = Math.max(dpL[i][j], dpR[i][j]);
		}
		
		System.out.println(dp[N - 1][M - 1]);
		br.close();
	}
}
