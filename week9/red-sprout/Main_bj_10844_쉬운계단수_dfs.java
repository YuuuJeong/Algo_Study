import java.io.*;

public class Main_bj_10844_쉬운계단수_dfs {
	static int N, answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		answer = 0;
		dfs(0, 0);
		System.out.println(answer);
		br.close();
	}
	
	public static void dfs(int cnt, int num) {
		if(cnt == N) {
			answer = answer < 999_999_999 ? answer + 1 : 0;
			return;
		}
		
		if(cnt == 0) {
			for(int i = 1; i <= 9; i++) dfs(cnt + 1, i);
			return;
		}
		
		if(num == 0) dfs(cnt + 1, num + 1);
		else if(num == 9) dfs(cnt + 1, num - 1);
		else {
			dfs(cnt + 1, num + 1);
			dfs(cnt + 1, num - 1);
		}
	}
}
