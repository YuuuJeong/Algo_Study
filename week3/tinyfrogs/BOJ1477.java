package tinyfrogs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1477 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] way = new int[N + 2];

        way[0] = 0;
        way[N + 1] = L;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            way[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(way);

        int result = Integer.MAX_VALUE;
        int min = 1;
        int max = L - 1;
        while (min <= max) {
            int mid = (max + min) / 2;
            int count = 0;
            for (int i = 1; i <= N + 1; i++) {

                //i에서 i-1의 구간을 구해준다 -> 휴게소가 없는 구간
                int length = way[i] - way[i - 1];

                //휴게소가 없는 구간의 길이에서 몇 개를 설치할 수 있는가
                int lengthCount = length / mid;
                count += lengthCount;

                //이전 휴게소 + 길이 * 설치한 갯수가 지금 휴게소의 위치와 같을 경우 이미 설치했으므로 하나 빼준다
                if (way[i - 1] + mid * lengthCount == way[i])
                    count--;
            }

            if (count > M) min = mid + 1;
            else {
                max = mid - 1;
                result = Math.min(result, mid);
            }
        }
        sb.append(result);
        System.out.println(sb);

    }
}
