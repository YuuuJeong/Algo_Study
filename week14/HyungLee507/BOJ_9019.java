import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T, answer, result;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            answer = Integer.parseInt(st.nextToken());
            result = Integer.parseInt(st.nextToken());

            visited = new boolean[10000];
            visited[answer] = true;

            Queue<Register> queue = new LinkedList<>();
            queue.add(new Register(answer, ""));

            while (!queue.isEmpty()) {
                Register cur = queue.poll();

                if (cur.num == result) {
                    sb.append(cur.command).append("\n");
                    break;
                }

                if (!visited[cur.D()]) {
                    queue.add(new Register(cur.D(), cur.command + "D"));
                    visited[cur.D()] = true;
                }
                if (!visited[cur.S()]) {
                    queue.add(new Register(cur.S(), cur.command + "S"));
                    visited[cur.S()] = true;
                }
                if (!visited[cur.L()]) {
                    queue.add(new Register(cur.L(), cur.command + "L"));
                    visited[cur.L()] = true;
                }
                if (!visited[cur.R()]) {
                    queue.add(new Register(cur.R(), cur.command + "R"));
                    visited[cur.R()] = true;
                }

            }
        }
        System.out.println(sb);

    }

    static class Register {
        int num;
        String command;

        Register(int num, String command) {
            this.num = num;
            this.command = command;
        }

        int D() {
            return (num * 2) % 10000;
        }

        int S() {
            return num == 0 ? 9999 : num - 1;
        }

        int L() {
            return num % 1000 * 10 + num / 1000;
        }

        int R() {
            return num % 10 * 1000 + num / 10;
        }
    }
}