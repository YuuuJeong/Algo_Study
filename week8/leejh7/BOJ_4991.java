import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int W, H, result;
	static char[][] board;
	static List<int[]> dust;
	static boolean[] visited;
	static int[] cleaner;
	static int[][][][] dist;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static void bfs(int sr, int sc) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { sr, sc, 0 });
		dist[sr][sc][sr][sc] = 0;
		while (!q.isEmpty()) {
			int[] p = q.poll();
			for (int i = 0; i < 4; i++) {
				int rr = p[0] + dx[i];
				int cc = p[1] + dy[i];
				if (rr < 0 || rr >= H || cc < 0 || cc >= W)
					continue;
				if (board[rr][cc] == 'x')
					continue;
				if (dist[sr][sc][rr][cc] != 0)
					continue;
				q.offer(new int[] { rr, cc, p[2] + 1 });
				dist[sr][sc][rr][cc] = dist[sr][sc][p[0]][p[1]] + 1;
			}
		}
	}

	static void perm(int depth, int[] order) {
		if (depth == dust.size()) {
			int res = 0;
			int curR = cleaner[0], curC = cleaner[1];
			for (int i = 0; i < dust.size(); i++) {
				int[] p = dust.get(order[i]);
				if (dist[curR][curC][p[0]][p[1]] == 0) {
					res = Integer.MAX_VALUE;
					break;
				}
				res += dist[curR][curC][p[0]][p[1]];
				curR = p[0];
				curC = p[1];
			}
			result = Math.min(result, res);
			return;
		}
		
		for (int i = 0; i < dust.size(); i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			order[depth] = i;
			perm(depth + 1, order);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine());

			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			if (W == 0 && H == 0)
				break;

			board = new char[H][W];
			dust = new ArrayList<>();
			dist = new int[H][W][H][W];
			result = Integer.MAX_VALUE;

			for (int i = 0; i < H; i++) {
				String input = br.readLine();
				for (int j = 0; j < W; j++) {
					board[i][j] = input.charAt(j);
					if (board[i][j] == 'o') {
						cleaner = new int[] { i, j };
					} else if (board[i][j] == '*') {
						dust.add(new int[] { i, j });
					}
				}
			}

			for (int sr = 0; sr < H; sr++) {
				for (int sc = 0; sc < W; sc++) {
					bfs(sr, sc);
				}
			}

			visited = new boolean[dust.size()];
			perm(0, new int[dust.size()]);
			sb.append(result == Integer.MAX_VALUE ? -1 : result).append("\n");
		}
		System.out.println(sb.toString());
	}
}
