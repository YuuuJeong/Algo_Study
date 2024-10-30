import java.io.*;
import java.util.*;

public class BOJ_8980 {

    static int N, C, M, result;

    static Point[] points;

    static class Point {
        int from, to, value;

        public Point(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        result = 0;
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        points = new Point[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }


        Arrays.sort(points, (o1, o2) -> o1.to - o2.to);
        int[] homeValues = new int[N + 1];
        for (int i = 0; i <= N; i++) homeValues[i] = C;

        findHomeValues(homeValues);
        System.out.println(result);
        br.close();
    }

    static void findHomeValues(int[] homeValues) {
        for (int i = 0; i < M; i++) {
            int start = points[i].from;
            int end = points[i].to;
            int value = points[i].value;

            int min = Integer.MAX_VALUE;

            for (int s = start; s < end; s++) {
                min = Math.min(min, homeValues[s]);
            }

            value = Math.min(value, min);
            result += value;

            for (int s = start; s < end; s++) {
                homeValues[s] -= value;
            }
        }
    }
}
