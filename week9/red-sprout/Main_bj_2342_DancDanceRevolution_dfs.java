import java.io.*;
import java.util.*;

public class Main_bj_2342_DancDanceRevolution_dfs {
	static int N, answer, MAX = 400_001;
	static List<Integer> list;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		list = new ArrayList<>();		

		int idx = 0;
		while((idx = Integer.parseInt(st.nextToken())) != 0) list.add(idx);

		N = list.size();
		answer = Integer.MAX_VALUE;
		dfs(0, 0, 0, 0);
		System.out.println(answer);
		br.close();
	}
	
	public static void dfs(int cur, int left, int right, int force) {
		if(cur == N) {
			answer = Math.min(answer, force);
			return;
		}
	
		int idx = list.get(cur);
		dfs(cur + 1, idx, right, force + getForce(left, idx));
		dfs(cur + 1, left, idx, force + getForce(right, idx));
	}
	
	public static int getForce(int a, int b) {
		if(a == 0) return 2;
		if(a == b) return 1;
		if(Math.abs(a - b) == 2) return 4;
		return 3;
	}
}
