
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HSAT_6246 {

    static int n, q;
    static int[] cars;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        cars = new int[n];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            cars[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cars);
        for (int i = 0; i < q; i++) {
            int number = Integer.parseInt(bf.readLine());
            int index = getIndex(number);
            if (index == -1) {
                sb.append(0).append('\n');
            } else {
                sb.append(getAnswer(index)).append('\n');
            }
        }
        System.out.println(sb);
    }

    private static int getIndex(int number) {
        for (int i = 0; i < n; i++) {
            if (cars[i] == number) {
                return i;
            }
        }
        return -1;
    }

    private static int getAnswer(int index) {
        int left = index;
        int right = n - index - 1;
        return left * right;
    }


}