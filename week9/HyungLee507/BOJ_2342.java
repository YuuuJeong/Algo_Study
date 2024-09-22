
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2342 {
    static long[][][] dp;
    static List<Integer> steps;
    static long answer;

    public static void main(String[] args) throws IOException {
        steps = new ArrayList<>();
        answer = Integer.MAX_VALUE;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        while (true) {
            int step = Integer.parseInt(st.nextToken());
            if (step == 0) {
                break;
            }
            steps.add(step);
        }
        dp = new long[steps.size() + 1][5][5];

        for (int i = 0; i <= steps.size(); i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        dp[0][0][0] = 0;
        int first = steps.get(0);

        dp[1][0][first] = getCost(0, first);
        dp[1][first][0] = getCost(0, first);
        for (int i = 1; i < steps.size(); i++) {
            int next = steps.get(i);
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    dp[i + 1][j][next] = Math.min(dp[i + 1][j][next], dp[i][j][k] + getCost(k, next));
                    dp[i + 1][next][k] = Math.min(dp[i + 1][next][k], dp[i][j][k] + getCost(j, next));
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                answer = Math.min(dp[steps.size()][i][j], answer);
            }
        }
        System.out.println(answer);
    }

    // 비용 구하는 메서드
    public static int getCost(int pre, int now) {

        if (pre == now) {
            return 1;
        }
        if (pre == 0) {
            return 2;
        }
        if (((pre + now) % 2) == 0) {
            return 4;
        } else {
            return 3;
        }
    }


}
