import java.io.*;

public class Main_bj_2482_색상환_dfs {
	static int N, K, answer;
	static final int MAX = 1_000_000_003;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		answer = 0;
		dfs(N - 3, K - 1);
		dfs(N - 1, K);
		System.out.println(answer);
		br.close();
	}
	
	public static void dfs(int cur, int cnt) {
		if(cnt < 0) return;
		if(cur <= 0) {
			if(cnt == 0) answer = (answer + 1) % MAX;
			return;
		}
		dfs(cur - 2, cnt - 1);
		dfs(cur - 1, cnt);
	}
}
