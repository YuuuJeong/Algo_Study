import java.io.*;
import java.util.*;

public class Main_bj_11779_최소비용구하기2 {
	static List<int[]>[] graph;
	static int[] before;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		graph = new List[n + 1];
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new int[] {b, c});
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		dijkstra(n, s, e);
		
		br.close();
	}
	
	public static void dijkstra(int n, int s, int e) {
		boolean[] visited = new boolean[n + 1];
		int[] dist = new int[n + 1];
		int[] before = new int[n + 1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(before, -1);
		
		dist[s] = 0;
		before[s] = 0;
		pq.offer(new int[] {s, dist[s]});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int v = cur[0];
			int d = cur[1];
			
			if(visited[v]) continue;
			visited[v] = true;
			
			for(int[] edge : graph[v]) {
				if(!visited[edge[0]] && dist[edge[0]] > d + edge[1]) {
					dist[edge[0]] = d + edge[1];
					before[edge[0]] = v;
					pq.offer(new int[] {edge[0], dist[edge[0]]});
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(e);
		while(stack.peek() != s) {
			stack.push(before[stack.peek()]);
		}
		
		sb.append(dist[e]).append("\n").append(stack.size()).append("\n");
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		
		System.out.print(sb.toString());
	}
}
