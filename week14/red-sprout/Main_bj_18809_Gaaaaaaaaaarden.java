import java.io.*;
import java.util.*;

public class Main_bj_18809_Gaaaaaaaaaarden {
	static int N, M, G, R, answer;
	static int[][] map, visited;
	static List<int[]> list;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static Queue<int[]> greenQ, redQ, lazy;
	static void solution(int cur, int gIdx, int rIdx, int[] green, int[] red) {
		if(cur == list.size()) {
			if(gIdx == G && rIdx == R) {
				initVisited();
				answer = Math.max(answer, countFlower(green, red));
			}
			return;
		}
		solution(cur + 1, gIdx, rIdx, green, red);
		if(gIdx < G) {
			green[gIdx] = cur;
			solution(cur + 1, gIdx + 1, rIdx, green, red);
		}
		if(rIdx < R) {
			red[rIdx] = cur;
			solution(cur + 1, gIdx, rIdx + 1, green, red);
		}
	}
	static void initVisited() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				visited[i][j] = 0;
			}
		}
	}
	static int countFlower(int[] green, int[] red) {
		init(green, greenQ, 1);
		init(red, redQ, 2);
		while(!greenQ.isEmpty() || !redQ.isEmpty()) {
			updateLazy(greenQ);
			updateLazy(redQ);
			update();
		}
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(visited[i][j] == 3) cnt++;
			}
		}
		return cnt;
	}
	static void init(int[] start, Queue<int[]> q, int color) {
		for(int idx : start) {
			int[] pos = list.get(idx);
			q.offer(new int[] {pos[0], pos[1], color});
			visited[pos[0]][pos[1]] |= color;
		}
	}
	static void updateLazy(Queue<int[]> q) {
		int size = q.size();
		for(int i = 0; i < size; i++) {
			int[] cur = q.poll();
			for(int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(map[nr][nc] == 0 || visited[nr][nc] > 0) continue;
				lazy.offer(new int[] {nr, nc, cur[2]});
			}
		}
	}
	static void update() {
		int size = lazy.size();
		for(int i = 0; i < size; i++) {
			int[] cur = lazy.poll();
            if((visited[cur[0]][cur[1]] & cur[2]) != 0) continue;
			visited[cur[0]][cur[1]] |= cur[2];
			lazy.offer(cur);
		}
		while(!lazy.isEmpty()) {
			int[] cur = lazy.poll();
			if(visited[cur[0]][cur[1]] == 3) continue;
			if(cur[2] == 1) {
				greenQ.offer(cur);
			} else {
				redQ.offer(cur);
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) list.add(new int[] {i, j});
			}
		}
		answer = 0;
		greenQ = new ArrayDeque<>();
		redQ = new ArrayDeque<>();
		lazy = new ArrayDeque<>();
		solution(0, 0, 0, new int[G], new int[R]);
		System.out.println(answer);
		br.close();
	}
}
