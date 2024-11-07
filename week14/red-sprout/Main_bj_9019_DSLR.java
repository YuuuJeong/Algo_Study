import java.io.*;
import java.util.*;

public class Main_bj_9019_DSLR {
	static String solution(int A, int B) {
		String[] register = new String[10000];
		Queue<Integer> q = new ArrayDeque<>();
		register[A] = "";
		q.offer(A);
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == B) break;
			int D = (cur * 2) % 10000;
			int S = (cur + 9999) % 10000;
			int L = (cur / 1000) + (cur % 1000) * 10;
			int R = (cur / 10) + (cur % 10) * 1000;
			if(register[D] == null) {
				register[D] = register[cur] + "D";
				q.offer(D);
			}
			if(register[S] == null) {
				register[S] = register[cur] + "S";
				q.offer(S);
			}
			if(register[L] == null) {
				register[L] = register[cur] + "L";
				q.offer(L);
			}
			if(register[R] == null) {
				register[R] = register[cur] + "R";
				q.offer(R);
			}
		}
		return register[B];
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			sb.append(solution(A, B)).append('\n');
		}
		System.out.println(sb.toString().trim());
		br.close();
	}
}
