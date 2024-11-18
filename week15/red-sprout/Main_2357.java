import java.io.*;
import java.util.*;

public class Main_2357 {
	static int N, M, len, size;
	static int[] minTree, maxTree;
	static void update(int idx, int val) {
		update(1, 0, N - 1, idx, -val, minTree);
		update(1, 0, N - 1, idx, val, maxTree);
	}
	static int[] get(int a, int b) {
		int[] result = new int[2];
		int ts = Math.min(a - 1, b - 1);
		int te = Math.max(a - 1, b - 1);
		result[0] = -get(1, 0, N - 1, ts, te, minTree);
		result[1] = get(1, 0, N - 1, ts, te, maxTree);
		return result;
	}
	static void update(int node, int s, int e, int idx, int val, int[] tree) {
		if(idx < s || e < idx) return;
		if(s == e) {
			tree[node] = val;
			return;
		}
		int mid = (s + e) / 2;
		update(2 * node, s, mid, idx, val, tree);
		update(2 * node + 1, mid + 1, e, idx, val, tree);
		tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
	}
	static int get(int node, int s, int e, int ts, int te, int[] tree) {
		if(te < s || e < ts) return Integer.MIN_VALUE;
		if(ts <= s && e <= te) return tree[node];
		int mid = (s + e) / 2;
		int left = get(2 * node, s, mid, ts, te, tree);
		int right = get(2 * node + 1, mid + 1, e, ts, te, tree);
		return Math.max(left, right);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		len = 1 << (int)Math.ceil(Math.log(N) / Math.log(2));
		size = len << 1;
		minTree = new int[size];
		maxTree = new int[size];
		for(int i = 0; i < N; i++) {
			int val = Integer.parseInt(br.readLine());
			update(i, val);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int[] result = get(a, b);
			sb.append(result[0]).append(' ').append(result[1]).append('\n');
		}
		System.out.print(sb.toString());
		br.close();
	}
}
