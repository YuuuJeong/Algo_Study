import java.io.*;
import java.util.*;

public class BOJ1043 {
	static int N, M;
	static int[] parent;
	static boolean[] known;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		known = new boolean[N + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		int cnt = Integer.parseInt(st.nextToken());
		for(int i = 0; i < cnt; i++) known[Integer.parseInt(st.nextToken())] = true;
		for(int i = 0; i <= N; i++) parent[i] = i;
		
		List<Integer>[] parties = new List[M];
		for(int p = 0; p < M; p++) {
			parties[p] = new ArrayList<>();
			st = new StringTokenizer(br.readLine(), " ");
			cnt = Integer.parseInt(st.nextToken());
			for(int i = 0; i < cnt; i++) parties[p].add(Integer.parseInt(st.nextToken()));
			for(int i = 0; i < cnt - 1; i++) {
				for(int j = i + 1; j < cnt; j++) {
					unionParent(parties[p].get(i), parties[p].get(j));
				}
			}
		}
		
		int result = 0;
		for(int p = 0; p < M; p++) {
			boolean isCount = true;
			for(int idx : parties[p]) {
				if(known[getParent(idx)]) {
					isCount = false;
					break;
				}
			}
			if(isCount) result++;
		}
		
		System.out.println(result);
		br.close();
	}
	
	public static int getParent(int x) {
		if(x == parent[x]) return x;
		return parent[x] = getParent(parent[x]);
	}
	
	public static void unionParent(int x, int y) {
		x = getParent(x);
		y = getParent(y);
		
		if(known[x]) parent[y] = x;
		else if(known[y]) parent[x] = y;
		else if(x > y) parent[x] = y;
		else parent[y] = x;
	}
}
