
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9019 {

    static int A, B;
    static boolean[] visited;
    static String[] commands;
    static Queue<Integer> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            visited = new boolean[10000];
            commands = new String[10000];
            Arrays.fill(commands, "");

            q = new ArrayDeque<>();
            q.offer(A);
            visited[A] = true;

            System.out.println(bfs());
        }
    }


    static String bfs() {
        while (!q.isEmpty()) {
            if (visited[B]) return commands[B];

            int num = q.poll();

            //D
            int D = (num * 2) % 10000;
            if (!visited[D]) {
                q.offer(D);
                visited[D] = true;
                commands[D] = commands[num] + "D";
            }

            //S
            int S = 0;
            if (num == 0) S = 9999;
            else S = num - 1;
            if (!visited[S]) {
                q.offer(S);
                visited[S] = true;
                commands[S] = commands[num] + "S";
            }

            //L
            int L = (num % 1000) * 10 + num / 1000;
            if (!visited[L]) {
                q.offer(L);
                visited[L] = true;
                commands[L] = commands[num] + "L";
            }

            //R
            int R = +(num % 10) * 1000 + (num / 10);
            if (!visited[R]) {
                q.offer(R);
                visited[R] = true;
                commands[R] = commands[num] + "R";
            }
        }

        return "";
    }
}
