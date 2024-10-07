import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10844 {
    static long MOD = 1000000000L;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numberLength = Integer.parseInt(bf.readLine());
        long[][] numbers = new long[numberLength + 1][10];
        for (int i = 1; i < 10; i++) {
            numbers[1][i] = 1;
        }

        for (int i = 2; i <= numberLength; i++) {
            numbers[i][0] = numbers[i - 1][1];
            numbers[i][9] = numbers[i - 1][8];
            for (int j = 1; j <= 8; j++) {
                numbers[i][j] = (numbers[i - 1][j - 1] + numbers[i - 1][j + 1]) % MOD;
            }

        }
        long total = 0;
        for (int i = 0; i < 10; i++) {
            total = (total + numbers[numberLength][i]) % MOD;
        }
        System.out.println(total);
    }


}