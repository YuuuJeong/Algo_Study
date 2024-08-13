package SWEA5653;

import java.io.*;
import java.util.*;

public class Solution {
	static class Cell {
		int life;
		int time; // time < life : 비활성, life <= time < 2 * life : 활성, time >= 2 * life : 죽음
		int row;
		int col;
		boolean isValid; // map 안에서 존재하는지 - 생사 여부 상관없음
		boolean isPlace; // 같은 그리드 내에서 map에 배치 가능한지 
		
		Cell(int life, int row, int col) {
			this.life = life;
			this.time = 0;
			this.row = row;
			this.col = col;
			this.isValid = false; // 대기큐에서 우선 대기후 추가하므로 기본은 false로 잡아둔다.
			this.isPlace = true;
		}
	}
	
	static Queue<Cell> cellQ = new ArrayDeque<>();
	static Queue<Cell> waitQ = new ArrayDeque<>();
	static Cell[][] map = new Cell[800][800];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("res/input5653.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= T; test++) {
			sb.append("#").append(test).append(" ");
			
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			for(int i = 0; i < 800; i++) {
				Arrays.fill(map[i], null);
			}
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < M; j++) {
					int life = Integer.parseInt(st.nextToken());
					if(life > 0) {
						Cell cell = new Cell(life, i + 400, j + 400);
						cell.isValid = true;
						cellQ.offer(cell);
						map[i + 400][j + 400] = cell;
					}
				}
			}
			
			for(int i = 1; i <= K; i++) spread();
			
			int answer = 0;
			while(!cellQ.isEmpty()) {
				Cell c = cellQ.poll();
				if(c.time < 2 * c.life) answer++;
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}
	
	public static void spread() {
		while(!cellQ.isEmpty()) {
			Cell c = cellQ.poll();
			c.time++;
			if(c.time > 2 * c.life) continue;
			waitQ.offer(c);
			if(c.life >= c.time) continue;
			for(int d = 0; d < 4; d++) {
				int nr = c.row + dr[d];
				int nc = c.col + dc[d];
				Cell next = map[nr][nc];
				if(next != null && next.isValid) continue;
				Cell newCell = null;
				if(next == null) {
					newCell = new Cell(c.life, nr, nc);
					map[nr][nc] = newCell;
					waitQ.offer(newCell);
				} else if(next.isValid || next.life >= c.life) {
					continue;
				} else {
					newCell = new Cell(c.life, nr, nc);
					next.isPlace = false; // waitQ 에서 해당하는 Cell은 무시해주기
					map[nr][nc] = newCell;
					waitQ.offer(newCell);
				}
			}
		}
		
		while(!waitQ.isEmpty()) {
			Cell c = waitQ.poll();
			if(!c.isPlace) continue;
			c.isValid = true;
			cellQ.offer(c);
			map[c.row][c.col] = c;
		}
	}
}
