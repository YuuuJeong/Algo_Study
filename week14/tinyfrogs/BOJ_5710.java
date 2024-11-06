import java.io.*;
import java.util.*;

public class BOJ_5710 {

    static int A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            if (A == 0 && B == 0) return;

            int totalCost = getTotalW(A);
            solve(totalCost);
        }
    }

    static void solve(int totalCost) {
        int left = 0;
        int right = totalCost / 2;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            int me = getCost(mid);
            int neighbor = getCost(totalCost - mid);

            int diff = Math.abs(neighbor - me);
            if (diff > B) left = mid + 1; //차이가 더 커지기 때문에 상근이의 가격을 높여야 한다
            else if(diff < B) right = mid - 1; //차이가 더 작아지기 때문에 상근이의 가격을 낮춰야 한다
            else{
                System.out.println(getCost(mid));
                break;
            }
        }
    }

    static int getTotalW(int totalCost) {
        if (totalCost <= 200) return totalCost / 2;
        else if (totalCost <= 29900) return (totalCost - 200) / 3 + 100;
        else if (totalCost <= 4979900) return (totalCost - 29900) / 5 + 10000;
        else return (totalCost - (4979900)) / 7 + 1000000;
    }

    static int getCost(int w) {
        if (w <= 100) return w * 2;
        else if (w <= 10000) return 200 + 3 * (w - 100);
        else if (w <= 1000000) return 200 + 29700 + 5 * (w - 10000);
        else return 200 + 29700 + 4950000 + 7 * (w - 1000000);
    }
}
