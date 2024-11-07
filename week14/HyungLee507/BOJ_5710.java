
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(bf.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if (A == 0 && B == 0) {
                break;
            }
            int result = binSearch(A, B);
            sb.append(result).append('\n');
        }
        System.out.println(sb);
        bf.close();
    }

    private static int binSearch(int A, int B) {
        // mid 값은 watt로 잡음.
        int totalWatt = calcWatt(A);
        int left = -1;
        int right = totalWatt + 1;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            int price1 = applyPriceList(mid);
            int price2 = applyPriceList(totalWatt - mid);
            int diff = Math.abs(price1 - price2);
            if (diff <= B) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return applyPriceList(right);
    }

    private static int calcWatt(int price) {
        if (price <= 200) {
            return price / 2;
        } else if (price <= 29900) {
            return (price - 200) / 3 + 100;
        } else if (price <= 4979900) {
            return (price - 29900) / 5 + 10000;
        } else {
            return (price - 4979900) / 7 + 1000000;
        }
    }


    private static int applyPriceList(int watt) {
        if (watt <= 100) {
            return watt * 2;
        } else if (watt <= 10000) {
            return 200 + (watt - 100) * 3;
        } else if (watt <= 1000000) {
            return 200 + 29700 + (watt - 10000) * 5;
        } else {
            return 200 + 29700 + 4950000 + (watt - 1000000) * 7;
        }
    }
}
