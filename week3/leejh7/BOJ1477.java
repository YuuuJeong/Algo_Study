package leejh7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1477 {

    static int N, M, L;
    static int[] arr;

    static boolean condition(int mid) {
        int cnt = arr[0] <= mid ? 0 : 1;

        for(int i=1; i<arr.length; i++) {
            int diff = arr[i] - arr[i-1];
            if (diff > mid) {
                cnt +=  (diff - 1) / mid;
            }
        }
        return M >= cnt;
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
                result = mid;
            } else {
                l = mid + 1;
            }
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N] = 0;
        arr[N+1] = L;
        Arrays.sort(arr);
        System.out.println(bs(1, L-1));
    }
}
