import java.io.*;
import java.util.*;

public class Main_CodeTree_왕실의기사대결 {
    static int L, N, Q;
    static int[][] info; // 0 : 빈칸, 1 : 함정, 2 : 벽
    static int[][] map;
    static class Knight {
        int id, r, c, h, w, k, d;
        public Knight(int id, int r, int c, int h, int w, int k) {
            super();
            this.id = id;
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;
            this.d = 0;
        }
        @Override
        public String toString() {
            return "Knight [id=" + id 
                    + ", r=" + r + ", c=" + c + ", d=" + d
                    + "]";
        }
    }
    static Knight[] knight;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[] lazy;
    static Queue<Integer> q;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        info = new int[L][L];
        map = new int[L][L];
        knight = new Knight[N + 1];
        lazy = new boolean[N + 1];
        q = new ArrayDeque<>();
        for(int i = 0; i < L; i++) {
        	st = new StringTokenizer(br.readLine(), " ");
        	for(int j = 0; j < L; j++) {
        		info[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        for(int id = 1; id <= N; id++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            knight[id] = new Knight(id, r, c, h, w, k);
            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    map[r + i][c + j] = id;
                }
            }
        }
        for(int test = 0; test < Q; test++) {
            st = new StringTokenizer(br.readLine(), " ");
            int id = Integer.parseInt(st.nextToken());
            int direct = Integer.parseInt(st.nextToken());
            q.clear();
            Arrays.fill(lazy, false);
            query(id, direct);
        }
        print();
        br.close();
    }
    
    private static void query(int id, int direct) {
    	if(knight[id].k <= knight[id].d) return;
        lazy[id] = true;
        q.offer(id);
        while(!q.isEmpty()) {
            int cur = q.poll();
            if(!hasNext(cur, direct)) return;
        }
        move(direct);
        damage(id, direct);
    }
    
    private static void move(int direct) {
    	for(int i = 0; i < L; i++) {
    		Arrays.fill(map[i], 0);
    	}
		for(int i = 1; i <= N; i++) {
			Knight k = knight[i];
			if(lazy[i]) {
				k.r += dr[direct];
				k.c += dc[direct];
			}
		}
	}

	private static void damage(int id, int direct) {
		for(int i = 1; i <= N; i++) {
			Knight k = knight[i];
			if(lazy[i] && i != id) {
				for(int dr = 0; dr < k.h; dr++) {
					for(int dc = 0; dc < k.w; dc++) {
						if(info[k.r + dr][k.c + dc] == 1) k.d++;
					}
				}
			}
			if(k.k > k.d) {
				for(int dr = 0; dr < k.h; dr++) {
					for(int dc = 0; dc < k.w; dc++) {
						map[k.r + dr][k.c + dc] = k.id;
					}
				}
			}
		}
	}

	private static boolean hasNext(int cur, int direct) {
        int nr, nc;
        boolean flag = true;
        Knight k = knight[cur];
        switch(direct) {
        case 0:
            nr = k.r - 1;
            for(nc = k.c; nc < k.c + k.w; nc++) {
                flag = isInRange(nr, nc);
                if(!flag) break;
            }
            break;
        case 1:
        	nc = k.c + k.w;
        	for(nr = k.r; nr < k.r + k.h; nr++) {
        		flag = isInRange(nr, nc);
                if(!flag) break;
        	}
            break;
        case 2:
            nr = k.r + k.h;
            for(nc = k.c; nc < k.c + k.w; nc++) {
            	flag = isInRange(nr, nc);
                if(!flag) break;
            }
            break;
        case 3:
        	nc = k.c - 1;
        	for(nr = k.r; nr < k.r + k.h; nr++) {
        		flag = isInRange(nr, nc);
                if(!flag) break;
        	}
            break;
        }
        return flag;
    }

	private static boolean isInRange(int nr, int nc) {
		if(nr < 0 || nr >= L || nc < 0 || nc >= L || info[nr][nc] == 2) return false;
		if(map[nr][nc] == 0 || lazy[map[nr][nc]]) return true;
		lazy[map[nr][nc]] = true;
		q.offer(map[nr][nc]);
		return true;
	}

    private static void print() {
        int answer = 0;
        for(int i = 1; i <= N; i++) {
            if(knight[i].k > knight[i].d) answer += knight[i].d;
        }
        System.out.println(answer);
    }
}
