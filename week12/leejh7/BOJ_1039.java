import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1039 {

    static class Node {
        String num;
        int cnt;

        public Node() {
        }

        public Node(String num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    static String N;
    static int K;
    static int answer = -1;

    static void bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(N, 0));
        boolean[] visited = new boolean[1000001];

        int prevCnt = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            String num = node.num;
            int cnt = node.cnt;

            if(cnt == K) {
                answer = Math.max(answer, Integer.parseInt(num));
                continue;
            }

            if(prevCnt != cnt) {
                for(int i=0; i<visited.length; i++) {
                    visited[i] = false;
                }
            }
            prevCnt = cnt;

            for(int i=0; i<N.length() - 1; i++) {
                for(int j=i+1; j<N.length(); j++) {
                    StringBuilder sb = new StringBuilder(num);

                    char tmp = sb.charAt(i);
                    sb.setCharAt(i, sb.charAt(j));
                    sb.setCharAt(j, tmp);

                    if(sb.charAt(0) == '0') continue;
                    if(visited[Integer.parseInt(sb.toString())]) continue;

                    visited[Integer.parseInt(sb.toString())] = true;
                    q.offer(new Node(sb.toString(), cnt + 1));
                }
            }

        }


    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = st.nextToken();
        K = Integer.parseInt(st.nextToken());

        bfs();
        System.out.println(answer);
    }
}
