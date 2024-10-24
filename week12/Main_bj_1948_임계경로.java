import java.io.*;
import java.util.*;

public class Main_bj_1948_임계경로 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[] prev = new int[n + 1];
		List<int[]>[] g = new List[n + 1];
		List<int[]>[] rg = new List[n + 1];
		for(int i = 1; i <= n; i++) {
			g[i] = new ArrayList<>();
			rg[i] = new ArrayList<>();
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			g[a].add(new int[] {b, c});
			rg[b].add(new int[] {a, c});
			prev[b]++;
		}
		st = new StringTokenizer(br.readLine(), " ");
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int[] dist = new int[n + 1];
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(s);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int[] edge : g[cur]) {
				dist[edge[0]] = Math.max(dist[edge[0]], dist[cur] + edge[1]);
				if(--prev[edge[0]] == 0) q.offer(edge[0]);
			}
		}
		int cnt = 0;
		boolean[] v = new boolean[n + 1];
		q.offer(e);
		v[e] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int[] edge : rg[cur]) {
				if(dist[cur] == dist[edge[0]] + edge[1]) {
					cnt++;
					if(!v[edge[0]]) {
						q.offer(edge[0]);
						v[edge[0]] = true;
					}
				}
			}
		}
		System.out.println(dist[e]);
		System.out.println(cnt);
		br.close();
	}
}
