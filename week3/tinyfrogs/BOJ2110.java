package tinyfrogs;

import java.io.*;
import java.util.*;

public class BOJ2110 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] house = new int[N];

        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        int min = 1;
        int max = house[N - 1];
        while (min <= max) {
            int mid = (min + max) / 2;
            int lastHouse = 0;
            int count = 1;

            //첫번째 부터 시작하여 mid에 만족하는 거리이면 count 증가 후 마지막 설치 위치 저장
            for (int i = 1; i < N; i++) {
                int dis = house[i] - house[lastHouse];
                if (dis >= mid) {
                    lastHouse = i;
                    count++;
                }
            }

            if (count < C)
                max = mid - 1;
            else {
                min = mid + 1;
            }

        }

        //마지막에 min = mid + 1 이므로 -1을 해야 최대 거리
        System.out.println(min - 1);
    }
}
