import java.io.*;
import java.util.*;

public class BOJ_8980 {

    static int N, C, M;
    static Point[] points;

    static class Point {
        int from;
        int to;
        int value;

        public Point(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        points = new Point[M];


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        //목적지가 빠른(가까운) 곳으로 -> 같으면 출발이 더 빠른 곳으로
        Arrays.sort(points, (o1, o2) -> {
            if (o1.to == o2.to) return o1.from - o2.from;
            return o1.to - o2.to;
        });

//        for(Point p : points)
//            System.out.println(p.from + " " + p.to + " " + p.value);

        //저장할 수 있는 공간 주기
        int[] homeValues = new int[N + 1];
        for (int i = 1; i <= N; i++) homeValues[i] = C;

        int ans = 0;

        //택배 경로 반복
        for (int i = 0; i < homeValues.length; i++) {
            int start = points[i].from;
            int end = points[i].to;
            int box = points[i].value;
            int recentValue = Integer.MAX_VALUE;

            for (int j = start; j < end; j++) {
                recentValue = Math.min(recentValue, homeValues[j]);
            }

            for (int j = start; j < end; j++) {
                homeValues[j] -= Math.min(recentValue, box);
            }
            ans += Math.min(recentValue, box);
        }

        System.out.println(ans);
    }
}
