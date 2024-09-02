import java.io.*;
import java.util.*;

public class Main_17472 {
    static int N, M, parent[], map[][];
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static final int MAX = 1000;
    static int[][] dist;
    static PriorityQueue<Edge> pq;

    static class Edge implements Comparable<Edge> {
        int start, end, dist;

        Edge(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
        
        public String toString() {
        	return start + " " + end + " " + dist;
        }
    }

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

        int group = 1;
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] > 0 && !visited[i][j]) {
                    dfs(i, j, group);
                    group++;
                }
            }
        }

        group--;
        parent = new int[group + 1];
        for(int i = 1; i <= group; i++) parent[i] = i;

        dist = new int[group + 1][group + 1];
        for(int i = 1; i <= group; i++) {
        	Arrays.fill(dist[i], MAX);
        }
        
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < M; j++) {
        		if(map[i][j] > 0) makeBridge(i, j);
        	}
        }
        
        pq = new PriorityQueue<>();
        for(int i = 1; i <= group; i++) {
        	for(int j = i + 1; j <= group; j++) {
        		pq.offer(new Edge(i, j, dist[i][j]));
        	}
        }

        System.out.println(mst());
        br.close();
    }

    public static void dfs(int row, int col, int group) {
        map[row][col] = group;
        visited[row][col] = true;
        for(int d = 0; d < 4; d++) {
            int nr = row + dr[d];
            int nc = col + dc[d];
            if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] == 0) continue;
            dfs(nr, nc, group);
        }
    }
    
    public static void makeBridge(int row, int col) {
    	int self = map[row][col];
    	for(int d = 0; d < 4; d++) {
    		int length = 0;
    		int other = self;
    		int nr = row, nc = col;
    		while(true) {
    			nr += dr[d];
    			nc += dc[d];
    			if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == self) {
    				length = MAX;
    				break;
    			}
    			if(map[nr][nc] > 0) {
    				other = map[nr][nc];
    				break;
    			}
    			length++;
    		}
    		
    		if(1 < length && length < MAX) {
    			dist[self][other] = Math.min(dist[self][other], length);
    			dist[other][self] = Math.min(dist[other][self], length);    			
    		}
    	}
    }
    
    public static int mst() {
    	int answer = 0;
    	boolean isConnected = false;
    	while(!pq.isEmpty()) {
    		isConnected = checkConnected();
    		if(isConnected) break;
    		
    		Edge e = pq.poll();
    		if(e.dist == MAX) return -1;
    		
    		int x = getParent(e.start);
    		int y = getParent(e.end);
    		if(x == y) continue;
    		
    		answer += e.dist;
        if(x < y) {
        	parent[y] = x;
        } else {
        	parent[x] = y;
        }
    	}
    	return answer;
    }
    
    public static boolean checkConnected() {
    	for(int i = 1; i < parent.length; i++) if(getParent(i) != 1) return false;
    	return true;
    }
    
    public static int getParent(int x) {
    	if(parent[x] == x) return x;
    	return parent[x] = getParent(parent[x]);
    }
}
