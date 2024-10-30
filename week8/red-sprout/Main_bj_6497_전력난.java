import java.io.*;
import java.util.*;

public class Main_bj_6497_전력난 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			if(m == 0 && n == 0) break;
			
			int total = 0;
			List<int[]>[] graph = new List[m];
			boolean[] visited = new boolean[m];
			int[] cost = new int[m];
			for(int i = 0; i < m; i++) {
				graph[i] = new ArrayList<>();
				cost[i] = Integer.MAX_VALUE;
			}
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				graph[x].add(new int[] {y, z});
				graph[y].add(new int[] {x, z});
				total += z;
			}
			
			int mst = 0, cnt = 0;
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
			cost[0] = 0;
			pq.offer(new int[] {0, cost[0]});
			while(!pq.isEmpty()) {
				int[] cur = pq.poll();
				int minVertex = cur[0];
				int min = cur[1];
				if(visited[minVertex]) continue;
				visited[minVertex] = true;
				mst += min;
				if(cnt++ == m - 1) break;
				for(int[] edge : graph[minVertex]) {
					if(!visited[edge[0]] && cost[edge[0]] > edge[1]) {
						cost[edge[0]] = edge[1];
						pq.offer(edge);
					}
				}
			}
			
			sb.append(total - mst).append("\n");
		}

		System.out.print(sb.toString());
		br.close();
	}
}
