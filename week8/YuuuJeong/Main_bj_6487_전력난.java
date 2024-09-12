import java.io.*;
import java.util.*;

public class Main_bj_6487_전력난{

	static class Node implements Comparable<Node>{
		int to, from;
		long value;

		public Node(int to, int from, long value) {
			this.to = to;
			this.from = from;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.value, o.value);
		}
		

	}

	static long totalCost;
	static int[] parents;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			if (m == 0 && n == 0) break;
			totalCost = 0;
			pq = new PriorityQueue<>();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				totalCost += value;
				pq.add(new Node(to, from, value));
			}

			parents = new int[m];
			Arrays.fill(parents, -1);

			long cost = solve();
			System.out.println(totalCost - cost);
		}
		br.close();
	}

	static long solve() {
		long cost = 0;
		while (!pq.isEmpty()) {
			Node p = pq.poll();
			if (find(p.to) == find(p.from)) continue;
			cost += p.value;
			union(p.to, p.from);
		}
		
		return cost;
	}

	static int find(int x) {
		if (parents[x] < 0)
			return x;
		
		return parents[x] = find(parents[x]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a== b) return;
		
		if(parents[a] <= parents[b]) {
			parents[a] += parents[b];
			parents[b] = a;
		}

		else{
			parents[b] += parents[a];
			parents[a] = b;
		}
	}

}
