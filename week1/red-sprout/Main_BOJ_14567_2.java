package week1;

import java.io.*;
import java.util.*;

public class Main_BOJ_14567_2 {
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/week1/input_bj_14567.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int T = 2;
		int T = 1;
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) {
					return o1[1] - o2[1];
				} else {
					return o1[0] - o2[0];
				}
			}
		});
		
		for(int test = 1; test <= T; test++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] result = new int[N + 1];
			Arrays.fill(result, 1);
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				pq.offer(new int[] {A, B});
			}
			
			while(!pq.isEmpty()) {
				int[] now = pq.poll();
				int a = now[0];
				int b = now[1];
				if(result[b] <= result[a]) result[b] = result[a] + 1;
			}
			
			for(int i = 1; i <= N; i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}
}
