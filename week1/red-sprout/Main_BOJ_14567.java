import java.io.*;
import java.util.*;

public class Main_BOJ_14567 {
	static class Node {
		int time;
		int prev;
		List<Integer> next;
		
		Node() {
			this.time = 0;
			this.prev = 0;
			this.next = new ArrayList<>();
		}
	}
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/week1/input_bj_14567.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int T = 2;
		int T = 1;
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new LinkedList<>();
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
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				nodes[A].next.add(B);
				nodes[B].prev++;
			}
			
			for(int i = 1; i <= N; i++) {
				if(nodes[i].prev == 0) {
					nodes[i].time = 1;
					q.offer(i);
				}
			}
			
			while(!q.isEmpty()) {
				int now = q.poll();
				for(int next : nodes[now].next) {
					if(nodes[next].time > 0) continue;
					nodes[next].prev--;
					if(nodes[next].prev > 0) continue;
					nodes[next].time = nodes[now].time + 1;
					q.offer(next);
				}
			}
			
			for(int i = 1; i <= N; i++) {
				sb.append(nodes[i].time).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}
}