import java.io.*;
import java.util.*;

public class Main_bj_1039_교환 {
	static int K, L;
	static int[][] dp;
	static int dfs(int cur, String N) {
		int num = Integer.parseInt(N);
		if(cur == K) return num;
		if(dp[cur][num] != 0) return dp[cur][num];
		dp[cur][num] = -1;
		for(int i = 0; i < L - 1; i++) {
			for(int j = i + 1; j < L; j++) {
				if(i == 0 && N.charAt(j) == '0') continue; // 맨 앞에는 0이 올 수 없음
				String tmpN = swap(N, i, j);
				dp[cur][num] = Math.max(dp[cur][num], dfs(cur + 1, tmpN));
			}
		}
		return dp[cur][num];
	}
	static String swap(String N, int i, int j) {
		char[] tmpArr = N.toCharArray();
		char c = tmpArr[i];
		tmpArr[i] = tmpArr[j];
		tmpArr[j] = c;
		return String.valueOf(tmpArr);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		String N = st.nextToken();
		K = Integer.parseInt(st.nextToken());
		L = N.length();
		dp = new int[K + 1][1000001];
		System.out.println(dfs(0, N));
		br.close();
	}
}
