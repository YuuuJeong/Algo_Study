import java.io.*;
import java.util.*;


public class Main_bj_11779_최소비용구하기2 {
	static class Node implements Comparable<Node>{
		int id;
		int weight;
		
		public Node(int id, int weight) {
			this.id = id;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		List<Node>[] nodes = new ArrayList[n+1];
		
		Queue<Node> pq = new PriorityQueue<>();
		int[] cost = new int[n+1];
		for(int i = 0; i <= n; i++) {
			nodes[i] = new ArrayList<>();
		}
		
		Arrays.fill(cost, Integer.MAX_VALUE);
		for(int i = 0; i < m; i ++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int id = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			Node edge = new Node(id,weight);
			nodes[from].add(edge);
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int dest = Integer.parseInt(st.nextToken());
		cost[start] = 0;
		
		pq.offer(new Node(start, 0));
		
		int[] before = new int[n+1];
		List<Integer> path = new ArrayList<>();
		while(!pq.isEmpty()) {
			Node e = pq.poll();
			if(e.id == dest) {
				path.add(dest);
				int curId = dest;
				while(true) {
					curId = before[curId];
					path.add(curId);
					if(curId == start) break;				
				}
				break;
			}
			
			if(cost[e.id] < e.weight) {
				continue;
			}
			
			for(Node curNode: nodes[e.id]) {
				if(cost[e.id] + curNode.weight < cost[curNode.id]) {
					cost[curNode.id] = cost[e.id] + curNode.weight;
					pq.offer(new Node(curNode.id, cost[curNode.id]));
					before[curNode.id] = e.id;
				}
			}
			
		}
//		System.out.println(Arrays.toString(path));
//		System.out.println(Arrays.toString(before));
		System.out.println(cost[dest]);
		System.out.println(path.size());
		for(int i = path.size()-1; i>=0; i--) {
			System.out.print(path.get(i)+" ");
		}
	}

}
