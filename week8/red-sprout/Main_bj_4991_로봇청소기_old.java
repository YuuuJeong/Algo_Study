import java.io.*;
import java.util.*;

public class Main_bj_4991_로봇청소기_old {
	static int h, w, ans, checked;
	static int[][] dist;
	static char[][] map;
	static boolean[][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static List<int[]> list = new ArrayList<>();
	static Queue<int[]> q = new LinkedList<>();
	static final int INF = 100000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = null;
		StringBuilder sb = new StringBuilder();
		while (true) {
			info = br.readLine().split(" ");
			w = Integer.parseInt(info[0]);
			h = Integer.parseInt(info[1]);
			ans = INF;
			if (w == 0 && h == 0)
				break;

			map = new char[h][w];
			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();
			}

			clean();
			sb.append(ans == INF ? -1 : ans).append("\n");
		}

		System.out.print(sb);
		br.close();
	}

	public static void clean() {
		list.clear();
		int[] start = new int[2];
		int idx = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				char c = map[i][j];
				switch (c) {
					case 'o':
						start[0] = i;
						start[1] = j;
						idx = list.size();
					case '*':
						list.add(new int[] { i, j });
						break;
				}
			}
		}

		int size = list.size();
		dist = new int[size][size];
		initDist(size);
		fwMovement(size);
		checked = 1 << idx;
		dfs(1, 0, size, idx);
	}

	public static void fwMovement(int size) {
		for (int k = 0; k < size; k++) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
	}

	public static void dfs(int cnt, int total, int size, int idx) {
		if (cnt == size) {
			ans = Math.min(ans, total);
			return;
		}

		for (int i = 0; i < size; i++) {
			if ((checked & (1 << i)) != 0)
				continue;
			if (dist[idx][i] == INF)
				continue;
			checked |= (1 << i);
			dfs(cnt + 1, total + dist[idx][i], size, i);
			checked &= ~(1 << i);
		}
	}

	public static int bfs(int[] start, int[] end) {
		q.clear();
		q.add(new int[] { start[0], start[1], 0 });
		visited[start[0]][start[1]] = true;

		while (!q.isEmpty()) {
			int[] now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				if (nr < 0 || nr >= h || nc < 0 || nc >= w)
					continue;
				if (visited[nr][nc] || map[nr][nc] == 'x')
					continue;
				if (nr == end[0] && nc == end[1])
					return now[2] + 1;
				visited[nr][nc] = true;
				q.add(new int[] { nr, nc, now[2] + 1 });
			}
		}

		return INF;
	}

	public static void initVisited(boolean[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			Arrays.fill(arr[i], false);
		}
	}

	public static void initDist(int size) {
		for (int i = 0; i < dist.length; i++) {
			Arrays.fill(dist[i], INF);
		}

		visited = new boolean[h][w];
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {
				int d = bfs(list.get(i), list.get(j));
				initVisited(visited);
				dist[i][j] = d;
				dist[j][i] = d;
			}
		}
	}
}
