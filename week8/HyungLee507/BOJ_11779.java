package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;

        for (int i = 0; i < N; i++) {
            int[] temp = new int[N];
            for (int j = 0; j < N; j++) {
                temp[j] = map[i][N - 1 - j];
            }
            if (isCorrect(map[i])) {
                answer++;
            }
        }
        for (int i = 0; i < N; i++) {
            int[] temp = new int[N];
            for (int j = 0; j < N; j++) {
                temp[j] = map[j][i];
            }
            if (isCorrect(temp)) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private static boolean isCorrect(int[] arr) {
        int temp;
        int count = 1;
        int index = 0;
        while (index < N) {
            temp = arr[index];
            if (index + 1 >= N) {
                break;
            }
            int next = arr[index + 1];

            if (Math.abs(next - temp) >= 2) {
                return false;
            }
            if (next == temp) {
                count++;
                index++;
                continue;
            }
            // 올라갔을 때
            if (temp - next == -1) {
                if (count >= L) {
                    count = 1;
                    index++;
                } else {
                    return false;
                }
            } else if (temp - next == 1) {
                if (index + L >= N) {
                    return false;
                }
                for (int i = index + 1; i < index + 1 + L; i++) {
                    if (arr[i] != next) {
                        return false;
                    }
                }
                index += L;
                count = 0;
            }

        }
        return true;
    }

}