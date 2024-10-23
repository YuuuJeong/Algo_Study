import java.io.*;
import java.util.*;

public class BOJ_16954 {
	

	
	static class Pos {
	    int r, c;
	    Pos (int r, int c) {
	        this.r = r;
	        this.c = c;
	    }
	}
	
    static Pos startPos = new Pos(7,0);
    static Pos endPos = new Pos(0,7);
	static int R = 8, C = 8;
	static char grid[][];
    static boolean visited[][];
	static int[][] directions = {{0,0}, 
								{-1,0}, {-1,1}, {0,1}, {1,1}, 
								{1,0}, {1,-1}, {0,-1}, {-1,-1}};
	static final int CAN_ARRIVE = 1;
	static final int CAN_NOT_ARRIVE = 0;


	public static void main(String[] args) throws Exception {

		 BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		 grid = new char[8][8];
		 for (int i=0; i<8; i++) {
		        String str = br.readLine();
		        for (int j=0; j<8; j++) {
		            grid[i][j] = str.charAt(j);
		        }
		}
		visited = new boolean[R][C];
	    bfs();
	}
	


	
	private static void bfs() {
	    Queue<Pos> Q = new ArrayDeque<>();
	    Q.add(new Pos(7, 0)); 
	    while(!Q.isEmpty()) {

	        visited = new boolean[8][8]; 
	        int size = Q.size();           
	        for (int t=0; t<size; t++) {
	            Pos cur = Q.poll();
	            if (grid[cur.r][cur.c] == '#') continue;
	            if (!checkWall()) {
	                System.out.println(CAN_ARRIVE);
	                return;
	            }
	            
	            if(cur.r == endPos.r && cur.c == endPos.c){
	            	 System.out.println(CAN_ARRIVE);
		                return;
	            }

	            for (int i=0; i<9; i++) {
	            	int[] d = directions[i];
	                int nr = cur.r + d[0];
	                int nc = cur.c + d[1];
	                if(!isInGrid(nr,nc)) continue;
	                if (grid[nr][nc] == '#') continue; 
	                if (visited[nr][nc]) continue; 
	                Q.add(new Pos(nr, nc));
	                visited[nr][nc] = true;
	            }
	        }
	        moveWall();
	    }
	    System.out.println( CAN_NOT_ARRIVE );
	}

	private static boolean isInGrid(int r, int c) {
		return r >= 0 && r< R && c >= 0 && c < C;
	}
	private static void moveWall() {
	    for (int i=7; i>=0; i--) {
	        for (int j=0; j<8; j++) {
	            if (grid[i][j] == '#') {
	                grid[i][j] = '.';
	                if (i != 7) {
	                    grid[i+1][j] = '#';
	                }
	            }
	        }
	    }
	}

	private static boolean checkWall() {
	    for (int i=0; i<8; i++) {
	        for (int j=0; j<8; j++) {
	            if (grid[i][j] == '#')
	                return true;
	        }
	    }
	    
	    return false;
	}


}
