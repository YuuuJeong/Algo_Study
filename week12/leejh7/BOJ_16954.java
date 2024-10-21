import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ_16954 {

    static class Player {
        int r, c;
        public Player(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Brick {
        int r, c;

        public Brick(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N = 8;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1, 0};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1, 0};

    static List<Brick> bricks = new ArrayList<Brick>();

    static class Node {
        boolean[][] visited = new boolean[N][N];
        Player player;
        int cnt;

        public Node(boolean[][] visited, Player player, int cnt) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    this.visited[i][j] = visited[i][j];
                }
            }
            this.player = player;
            this.cnt = cnt;
        }
    }

    static void bfs() {
        boolean[][] visited = new boolean[N][N];
        Player player = new Player(7, 0);
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(visited, player, 0));

        while(!q.isEmpty()) {
            Node node = q.poll();
            boolean[][] vis = node.visited;
            Player p = node.player;
            int cnt = node.cnt;

            if(p.r == 0 && p.c == 7) {
                System.out.println("1");
                return;
            }

            for(int d=0; d<9; d++) {
                int rr = p.r + dr[d];
                int cc = p.c + dc[d];

                if(rr < 0 || rr >= N || cc < 0 || cc >= N) continue;
                if(d != 8 && vis[rr][cc]) continue;
                boolean flag = false;
                for(Brick b : bricks) {
                    if (b.r + cnt == rr && b.c == cc) {
                        flag = true;
                        break;
                    }
                    if(b.r + cnt + 1 == rr && b.c == cc) {
                        flag = true;
                        break;
                    }
                }
                if(flag) continue;
                vis[rr][cc] = true;
                q.offer(new Node(vis, new Player(rr, cc), cnt + 1));
            }
        }
        System.out.println("0");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < N; j++) {
                if(input.charAt(j) == '#') {
                    bricks.add(new Brick(i, j));
                }
            }
        }

        bfs();
    }

}
