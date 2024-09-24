import java.io.*;
import java.util.*;

public class Main_bj_1520_내리막길_dfs {
	static int N, M, answer;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
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
 		
		answer = 0;
		dfs(0, 0);
		System.out.println(answer);
		br.close();
	}
	
	public static void dfs(int row, int col) {
		if(row == N - 1 && col == M - 1) {
			answer++; return;
		}
		
		for(int d = 0; d < 4; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];
			if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] >= map[row][col]) continue;
			dfs(nr, nc);
		}
	}
}
