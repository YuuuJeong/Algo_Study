package leejh7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Soft_자동차테스트 {

    static int findIdx(int target, int[] arr) {
        int lt = 0, rt = arr.length - 1;
        int mid = -1;

        while (lt <= rt) {
            mid = (lt + rt) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                rt = mid - 1;
            } else {
                lt = mid + 1;
            }
        }
        return 0;
    }

    static int solution(int idx, int length) {
        return idx * (length - idx - 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int n, q;
        int[] arr;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i < q; i++) {
            int target = Integer.parseInt(br.readLine());
            int idx = findIdx(target, arr);
            sb.append(solution(idx, arr.length)).append("\n");
        }
        System.out.println(sb.toString());
    }

}
