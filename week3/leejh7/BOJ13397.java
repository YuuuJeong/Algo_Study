package leejh7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13397 {
    static int[] arr;
    static int N, M;

    static boolean condition(int mid) {
        int cnt = 1;
        int low = arr[0], high = arr[0];
        for(int i=0; i<N; i++) {
            low = Math.min(low, arr[i]);
            high = Math.max(high, arr[i]);
            if (high - low > mid) {
                cnt += 1;
                if (M < cnt) {
                    return false;
                }
                high = arr[i];
                low = arr[i];
            }
        }
        return true;
    }

    static int bs(int low, int high) {
        int result = high;
        int l = low;
        int r = high;
        int mid;

        while(l <= r) {
            mid = (l + r) / 2;
            if(condition(mid)) {
                r = mid - 1;
                result = Math.min(result, mid);
            } else {
                l = mid + 1;
            }
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int low = 0, high = -1;
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            high = Math.max(high, arr[i]);
        }

        System.out.println(bs(low, high));
    }
}
