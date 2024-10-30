import java.io.*;
import java.util.*;

public class Main_bj_4991_로봇청소기_new {
	static int w, h;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if(w == 0 && h == 0) break;
			map = new char[h][w];
			
			int r = 0, c = 0;
			char dirty = '0';
			for(int i = 0; i < h; i++) {
				String row = br.readLine();
				for(int j = 0; j < w; j++) {
					map[i][j] = row.charAt(j);
					switch(map[i][j]) {
					case 'o':
						r = i; c = j; map[i][j] = '.';
						break;
					case '*':
						map[i][j] = dirty++;
						break;
					}
				}
			}
			
			int dirt = dirty - '0';
			sb.append(bfs(r, c, dirt)).append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}
	
	public static int bfs(int r, int c, int dirt) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[h][w][1 << dirt];
		
		q.offer(new int[] {r, c, 0, 0});
		visited[r][c][0] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			int row = cur[0];
			int col = cur[1];
			int move = cur[2];
			int v = cur[3];
			if(v == (1 << dirt) - 1) return move;
			
			for(int d = 0; d < 4; d++) {
				int nr = row + dr[d];
				int nc = col + dc[d];
				if(nr < 0 || nr >= h || nc < 0 || nc >= w || visited[nr][nc][v] || map[nr][nc] == 'x') continue;
				
				if(map[nr][nc] == '.') {
					q.offer(new int[] {nr, nc, move + 1, v});
					visited[nr][nc][v] = true;
					continue;
				}
				
				if('0' <= map[nr][nc] && map[nr][nc] <= '9') {
					int idx = map[nr][nc] - '0';
					int nv = v | (1 << idx);
					if(visited[nr][nc][nv]) continue;
					q.offer(new int[] {nr, nc, move + 1, nv});
					visited[nr][nc][nv] = true;
				}
			}
		}
		
		return -1;
	}
}
