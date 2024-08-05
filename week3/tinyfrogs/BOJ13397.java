package tinyfrogs;

import java.util.*;
import java.io.*;

public class BOJ13397 {
    public static void main(String[] args) throws Exception {
        /*
         * 구간을 나눠서 최댓값의 최소값을 구해야한다 -> 매개변수 탐색
         * 구간을 어떻게 나눠야하나?
         * 	(최대 - 최소) / 2 값을 만족하는 구간이 있는 개수를 구한다
         * 	연속된 수들로 이루어져 있으므로 정렬을 할 수 없다.
         * 	그럼 0 ~ N-1 순서대로 비교를 한다
         * 		비교할 때 -> 0, 1, 2, ... 마다 최소, 최대값을 찾아본다
         * 		그 후 (최대 - 최소)가 만족하는 구간이면 구간 갯수를 늘리고
         * 		최소, 최대값을 현재의 값으로 설정 -> 현재부터 구간의 크기를 다시지정해야하기 때문에
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];


        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        max = max - min;
        min = 0;

        int maxValue = -1;
        int minValue = 10001;
        int result = 0;
        while(min <= max) {
            int mid = (max + min) / 2;
            int count = 0;

            for(int i = 0 ; i < N; i++) {
                maxValue = Math.max(maxValue, arr[i]);
                minValue = Math.min(minValue, arr[i]);

                if(maxValue- minValue >= mid) {
                    maxValue = arr[i];
                    minValue = arr[i];
                    count++;
                }
            }

            if(count < M) {
                max = mid - 1;
            }else {
                min = mid + 1;
                result = mid;
            }
        }

        System.out.println(result);

    }
}
