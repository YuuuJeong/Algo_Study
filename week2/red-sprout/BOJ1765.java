import java.io.*;
import java.util.*;

public class BOJ1765 {
	static int[] friend;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[] check = new int[n + 1];
		friend = new int[n + 1];
		List<Integer>[] enemy = new List[n + 1];
		for(int i = 0; i <= n ; i++) {
			friend[i] = i;
			check[i] = -1;
			enemy[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			if(c == 'F') {
				unionParent(p, q);
			} else {
				enemy[p].add(q);
				enemy[q].add(p);
			}
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1; i <= n; i++) {
			for(int idx : enemy[i]) q.offer(idx);
			while(!q.isEmpty()) {
				int e = q.poll();
				for(int f : enemy[e]) {
					unionParent(i, f);
				}
			}
		}
		
		Set<Integer> set = new HashSet<>();
		for(int i = 1; i <= n; i++) {
			set.add(findParent(i));
		}
		
		System.out.println(set.size());
		br.close();
	}
	
	public static int findParent(int x) {
		if(x == friend[x]) return x;
		return friend[x] = findParent(friend[x]);
	}
	
	public static void unionParent(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		if(x > y) friend[x] = y;
		else friend[y] = x;
	}
}
