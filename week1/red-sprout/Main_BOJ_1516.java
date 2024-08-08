import java.io.*;
import java.util.*;

public class Main_BOJ_1516 {
	static class Building {
		int time;
		int prev;
		List<Integer> next;
		
		Building() {
			this.time = 0;
			this.prev = 0;
			this.next = new ArrayList<>();
		}
		
		void add(int idx) {
			next.add(idx);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int[] result = new int[N + 1];
		Building[] buildings = new Building[N + 1];
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1; i <= N; i++) {			
			buildings[i] = new Building();
		}
		
		for(int i = 1; i <= N; i++) {
			int num;
			st = new StringTokenizer(br.readLine());
			boolean isFirst = true;
			while((num = Integer.parseInt(st.nextToken())) != -1) {
				if(isFirst) {
					buildings[i].time = num;
					result[i] = num;
					isFirst = false;
					continue;
				}
				buildings[num].add(i);
				buildings[i].prev++;
			}
		}
		
		for(int i = 1; i <= N; i++) {
			if(buildings[i].prev == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int idx = q.poll();
			Building now = buildings[idx];
			for(int i : now.next) {
				result[i] = Math.max(result[i], result[idx] + buildings[i].time);
				buildings[i].prev--;
				if(buildings[i].prev == 0) q.offer(i);
			}
		}
		
		for(int i = 1; i <= N; i++) {
			sb.append(result[i]).append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}
}