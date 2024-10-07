import java.io.*;
import java.util.*;

public class Main_bj_2169_로봇조종하기_dfs {
	static int N, M, answer;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {1, 0, 0};
	static int[] dc = {0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		answer = Integer.MIN_VALUE;
		visited = new boolean[N][M];
		dfs(0, 0, map[0][0]);
		System.out.println(answer);
		br.close();
	}
	public static void dfs(int row, int col, int value) {
		if(row == N - 1 && col == M - 1) {
			answer = Math.max(answer, value);
			return;
		}
		visited[row][col] = true;
		for(int d = 0; d < 3; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];
			if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;
			dfs(nr, nc, value + map[nr][nc]);
		}
		visited[row][col] = false;
	}
}
