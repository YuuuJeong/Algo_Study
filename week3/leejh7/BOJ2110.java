package leejh7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110 {

    static int N, C;
    static int[] arr;

    static boolean condition(int mid) {
        int p = 0, cnt = 1;

        for(int i=1; i<N; i++) {
            int diff = arr[i] - arr[p];
            if (diff >= mid) {
                p = i;
                cnt += 1;
            }
        }

        return cnt >= C;
    }

    static int bs() {
        int result = arr[arr.length - 1];
        int l = 0;
        int r = arr[arr.length - 1];
        int mid = 0;

        while (l<=r) {
            mid = (l + r) / 2;
            if (condition(mid)) {
                l = mid + 1;
                result = mid;
            } else {
                r = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        System.out.println(bs());
    }

}

