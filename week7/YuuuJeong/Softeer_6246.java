import java.io.*;
import java.util.*;

public class Main {

   
	static int[][] direction = {{-1,0}, {0,1}, {1,0}, {0,-1}};
	static int[][] grid;
	static int N, M;
	static int ans = 0;
	static boolean[][] visited;
	static int size = 0;
	static int[][] pos;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];

		
		for(int r = 1 ; r<= N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= N; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int startR = 0, startC = 0;
		pos = new int[M][2];
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(i == 0 ) {
				startR = r; startC = c;
			}
			pos[size++] = new int[]{r, c};
		}

		visited[startR][startC] = true;
		dfs(startR, startC, 1);
		System.out.println(ans);
	}
	
	public static void dfs(int r, int c, int cnt) {
		if(cnt == M) {
			ans++;
			return;
		}
		
		for(int[] d :direction) {
			int nr = r + d[0];
			int nc = c + d[1];
//			System.out.println(Arrays.toString(pos[cnt]) + " " + nr + " " + nc);
			if(isInGrid(nr, nc) && !visited[nr][nc] && grid[nr][nc] == 0) {
				if(nr == pos[cnt][0] && nc == pos[cnt][1]) {
					visited[nr][nc] = true;
					dfs(nr, nc, cnt+1);
					visited[nr][nc] = false;
				}
				else {
					visited[nr][nc] = true;
					dfs(nr, nc, cnt);
					visited[nr][nc] = false;
				}
			}
		}
		
		return;
	}
	
	public static boolean isInGrid(int r, int c ) {
		return r >= 1 && r<= N && c >= 1 && c<=N;
	}
}
