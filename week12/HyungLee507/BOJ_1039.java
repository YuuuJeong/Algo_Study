
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Exchange {

    static int max, K, len;
    static String str;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        str = st.nextToken();
        K = Integer.parseInt(st.nextToken());
        len = str.length();
        visited = new boolean[K + 1][(int) Math.pow(10, len)];

        max = Integer.MIN_VALUE;
        if (len <= 2 && notZeroCount(str) == 1) {
            System.out.println(-1);
        } else {
            dfs(str, 0);
            System.out.println(max);
        }
    }

    private static int notZeroCount(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                cnt++;
            }
        }
        return cnt;
    }

    private static void dfs(String str, int cnt) {
        int temp = Integer.parseInt(str);
        if (cnt == K) {
            max = Math.max(temp, max);
            return;
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int nextStep = switchNum(i, j, str);
                if (nextStep == -1 || visited[cnt + 1][nextStep]) {
                    continue;
                }
                visited[cnt + 1][nextStep] = true;
                dfs(String.valueOf(nextStep), cnt + 1);
            }
        }
    }

    // 메서드 구현 완.
    private static int switchNum(int i, int j, String value) {
        StringBuilder sb = new StringBuilder(value);
        char temp1 = sb.charAt(i);
        char temp2 = sb.charAt(j);
        if (temp2 == '0' && i == 0) {
            return -1;
        }
        sb.replace(i, i + 1, String.valueOf(temp2));
        sb.replace(j, j + 1, String.valueOf(temp1));

        int result = Integer.parseInt(sb.toString());
        return result;
    }


}
