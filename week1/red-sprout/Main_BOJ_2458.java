import java.io.*;
import java.util.*;

public class Main_BOJ_2458 {
	static class Node {
		List<Integer> prev = new ArrayList<>();
		List<Integer> next = new ArrayList<>();
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 1;
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new ArrayDeque<>();
		for(int test = 1; test <= T; test++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			Node[] nodes = new Node[N + 1];
			
			for(int i = 1; i <= N; i++) {
				nodes[i] = new Node();
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				nodes[a].next.add(b);
				nodes[b].prev.add(a);
			}
			
			int answer = 0;
			for(int i = 1; i <= N; i++) {
				boolean[] visited = new boolean[N + 1];
				visited[i] = true;

				int lower = 0;
				q.offer(i);
				while(!q.isEmpty()) {
					int now = q.poll();
					for(int prev : nodes[now].prev) {
						if(visited[prev]) continue;
						visited[prev] = true;
						q.offer(prev);
						lower++;
					}
				}
				
				int upper = 0;
				q.offer(i);
				while(!q.isEmpty()) {
					int now = q.poll();
					for(int next : nodes[now].next) {
						if(visited[next]) continue;
						visited[next] = true;
						q.offer(next);
						upper++;
					}
				}
				
				if(lower + upper == N - 1) {
					answer++;
				}
			}
			sb.append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}
}