import java.io.*;
import java.util.*;

public class CodeTree_메이즈러너{


	
	
	static class Person{
		int r;
		int c;
		boolean isArrived;
		public Person(int r, int c) {
			this.r = r;
			this.c = c;
			this.isArrived = false;
		}
	}
	
	static class Square implements Comparable<Square>{
		int r;
		int c;
		int size;
		
		public Square(int r, int c, int size) {
			this.r = r;
			this.c = c;
			this.size = size;
		}
		@Override
		public int compareTo(Square o) {
			if(size != o.size) {
				return Integer.compare(size, o.size);
			}
			if(r != o.r) {
				return Integer.compare(r, o.r);
			}
			return Integer.compare(c, o.c);
		}
	}
	
	
	static int[][] grid;
	static int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static Person[] people;
	static final int BLANK = 0;
	static int exitR;
	static int exitC;
	static int N,M,K;
	static int arriveCnt = 0;
	static int moveCnt = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = 1;
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			grid = new int[N+1][N+1];
			people = new Person[M];
			for(int r = 1; r <= N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 1 ; c <= N ;c++) {
					grid[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0 ; i < M ; i++) {
				st = new StringTokenizer(br.readLine());
				people[i] = new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			exitR = Integer.parseInt(st.nextToken()); exitC = Integer.parseInt(st.nextToken());
			simulate();
			sb.append(moveCnt+"\n");
			sb.append(exitR + " " + exitC+"\n");
		}
		
		System.out.println(sb);
	}

	private static void simulate() {
		int time = 1;
		while(arriveCnt < M && time <= K) {
//			System.out.println(time +"분 시작 \n\n\n");
			move();
//			System.out.println(moveCnt);
			if(arriveCnt == M) break;
			Square s = selectSquare();
//			System.out.println(s.size + " " +s.r + " "+s.c);
			rotateSubArray(s.r, s.c, s.size, s.size);
			
//			for(int i = 0 ; i< M ; i++) {
//				System.out.println("PERSON: " + people[i].r + " " + people[i].c);
//			}
//			System.out.println("EXIT " + exitR + " "  +exitC);
//			for(int r = 1; r<=N; r++) {
//				System.out.println(Arrays.toString(grid[r]));
//			}
//			System.out.println(time +"분 끝  \n\n\n");
			time++;
		}
	}

	private static Square selectSquare() {
		PriorityQueue<Square> pq = new PriorityQueue<>();
		for(int i = 0; i < M ; i++) {
			if(people[i].isArrived) continue;
			Square s = makeSquare(people[i].r, people[i].c);
			pq.offer(s);
		}

		return pq.poll();
	}

	private static Square makeSquare(int r, int c) {
		
		// 차이중 더 큰 것을 변의 길이로 채
		int distR = Math.abs(r - exitR) + 1;
	    int distC = Math.abs(c - exitC) + 1;
	    int dist = Math.max(distR, distC);


	    // 
	    int startR = Math.min(r, exitR);
	    int startC = Math.min(c, exitC);

	    if (distR > distC) {
	        startC = Math.min(startC, Math.max(c, exitC) - dist + 1);
	    } else if (distC > distR) {
	        startR = Math.min(startR, Math.max(r, exitR) - dist + 1);
	    }

	    startR = Math.max(startR, 1);
	    startC = Math.max(startC, 1);
//	    System.out.println(startR+ " " + startC + " " + dist);
	    return new Square(startR, startC, dist);
	}


	private static void move() {
		for(Person p: people) {
			if(p.isArrived) continue;
			int curDist = getDistance(p.r, p.c, exitR, exitC);
			int targetDir = -1;
			for(int d = 0; d < 4; d++) {
				int[] curD = directions[d];
				int nr = curD[0] + p.r;
				int nc = curD[1] + p.c;
				if(!isInGrid(nr,nc)) continue;
				if(grid[nr][nc] > 0) continue;
				int nDist = getDistance(nr,nc,exitR, exitC);
				if(curDist > nDist) {
					targetDir = d;
					curDist = nDist;
				}
			}
			
			if(targetDir != -1) {
				p.r += directions[targetDir][0];
				p.c += directions[targetDir][1];
				if(p.r == exitR && p.c == exitC) {
					arriveCnt ++;
					p.isArrived = true;
				}
				moveCnt++;
			}
			
		}
		
	}

	
	
	public static void rotateSubArray(int startRow, int startCol, int a, int b) {
	    int[][] temp = new int[a][b];
	    for (int i = 0; i < a; i++) {
	        for (int j = 0; j < b; j++) {
	            temp[j][b - 1 - i] = grid[startRow + i][startCol + j];
	        }
	    }
	    
	    for (int i = 0; i < a; i++) {
	        for (int j = 0; j < b; j++) {
	        	if(temp[i][j] > 0) temp[i][j] -= 1;
	            grid[startRow + i][startCol + j] = temp[i][j];
	        }
	    }
	    
	    for (Person p : people) {
	        if (isInSubArray(p.r, p.c, startRow, startCol, a, b)) {
	            int newR = startRow + (p.c - startCol);
	            int newC = startCol + (b - 1 - (p.r - startRow));
	            p.r = newR;
	            p.c = newC;
	        }
	    }

	    if (isInSubArray(exitR, exitC, startRow, startCol, a, b)) {
	        int newExitR = startRow + (exitC - startCol);
	        int newExitC = startCol + (b - 1 - (exitR - startRow));
	        exitR = newExitR;
	        exitC = newExitC;
	    }
	}


	private static boolean isInSubArray(int r, int c, int startRow, int startCol, int a, int b) {
	    return r >= startRow && r < startRow + a && c >= startCol && c < startCol + b;
	}

	
	public static int getDistance(int r, int c, int r1, int c1) {
		return Math.abs(r-r1) + Math.abs(c-c1);
	}
	
	
	public static boolean isInGrid(int r, int c) {
		return r > 0 &&  r <= N && c > 0 && c <= N;
	}
}
