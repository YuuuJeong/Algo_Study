import java.io.*;

public class Main_bj_1562_계단수_dfs {
	static int N, answer;
	static final int MAX = 1_000_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		answer = 0;
		dfs(0, 0, 0);
		System.out.println(answer);
		br.close();
	}
	
	public static void dfs(int cnt, int num, int visited) {
		if(cnt == N) {
			if(visited == (1 << 10) - 1) answer = (answer + 1) % MAX;
			return;
		}
		
		if(cnt == 0) {
			for(int i = 1; i <= 9; i++) dfs(cnt + 1, i, visited | (1 << i));
			return;
		}
		
		if(num == 0) dfs(cnt + 1, num + 1, visited | (1 << (num + 1)));
		else if(num == 9) dfs(cnt + 1, num - 1, visited | (1 << (num - 1)));
		else {
			dfs(cnt + 1, num + 1, visited | (1 << (num + 1)));
			dfs(cnt + 1, num - 1, visited | (1 << (num - 1)));
		}
	}
}
