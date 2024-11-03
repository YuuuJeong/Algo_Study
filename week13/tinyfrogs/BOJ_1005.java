import java.io.*;
import java.util.*;

public class BOJ_1005 {

    static int N, K;

    static class Build {
        List<Integer> next;
        int prev;
        int time;

        Build(int time) {
            next = new ArrayList<>();
            prev = 0;
            this.time = time;
        }

    }

    static Build[] builds;
    static int[] times;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        //테스트 케이스 입력
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            //건물 정보 입력
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            builds = new Build[N + 1];
            times = new int[N + 1];

            //건물 시간 입력
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= N; i++) {
                int time = Integer.parseInt(st.nextToken());
                builds[i] = new Build(time);
            }

            //건물 순서 입력
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                builds[to].next.add(from);
                builds[from].prev++;
            }

            //마지막 건물
            int end = Integer.parseInt(br.readLine());

            ArrayDeque<Integer> queue = new ArrayDeque<>();

            //원래 건물 건설 시간 받기, 선행 건물이 없는 건물 Queue 넣기
            for (int i = 1; i <= N; i++) {
                times[i] = builds[i].time;
                if (builds[i].prev == 0) queue.add(i);
            }

            //t
            while (!queue.isEmpty()) {
                int idx = queue.poll();

                for (int i : builds[idx].next) {
                    times[i] = Math.max(times[i], times[idx] + builds[i].time);

                    builds[i].prev--;

                    if (builds[i].prev == 0) queue.offer(i);
                }
            }


            System.out.println(times[end]);
        }


    }
}
