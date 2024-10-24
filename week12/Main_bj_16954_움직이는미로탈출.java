import java.io.*;
import java.util.*;

public class Main_bj_16954_움직이는미로탈출 {
	static int[] dr = {0, 0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dc = {0, 1, 1, 0, -1, -1, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] map = new char[8][8];
		List<int[]> wallList = new ArrayList<>();
		for(int i = 0; i < 8; i++) {
			String row = br.readLine();
			for(int j = 0; j < 8; j++) {
				char c = row.charAt(j);
				map[i][j] = c;
				if(c == '#') wallList.add(new int[] {i, j});
			}
		}
		boolean check = true;
		boolean[][] visited = new boolean[8][8];
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {7, 0});
		for(int i = 0; i < 9; i++) {
			int size = q.size();
			if(size == 0) {
				check = false;
				break;
			}
			for(int j = 0; j < 8; j++) {
				Arrays.fill(visited[j], false);
			}
			for(int j = 0; j < size; j++) {
				int[] cur = q.poll();
				if(map[cur[0]][cur[1]] == '#') continue;
				for(int d = 0; d < 9; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if(nr < 0 || nr >= 8 || nc < 0 || nc >= 8 || visited[nr][nc] || map[nr][nc] == '#') continue;
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
			for(int j = 0; j < 8; j++) {
				Arrays.fill(map[j], '.');
			}
			for(int[] w : wallList) {
				if(++w[0] < 8) map[w[0]][w[1]] = '#';
			}
		}
		System.out.println(check ? 1 : 0);
		br.close();
	}
}
