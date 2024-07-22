package tinyfrogs;

import java.io.*;
import java.util.*;

public class BOJ14567 {

    static class Subject {
        int prev;
        List<Integer> next;

        public Subject() {
            prev = 0;
            next = new ArrayList<>();
        }
    }

    static Subject[] subjects;
    static int[] time;
    static int N, M;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        subjects = new Subject[N + 1];

        for (int i = 1; i <= N; i++) {
            subjects[i] = new Subject();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            subjects[first].next.add(second);
            subjects[second].prev += 1;
        }

        time = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            searchFirst(i);
        }

        solve();

        for (int i = 1; i <= N; i++) {
            sb.append(time[i]).append(" ");
        }

        System.out.println(sb);

        br.close();
    }

    static void searchFirst(int i) {
        if (subjects[i].prev == 0) {
            q.offer(i);
            time[i] = 1;
        }
    }

    static void solve() {
        while (!q.isEmpty()) {
            int current = q.poll();
            for (int next : subjects[current].next) {
                if (time[next] < 1)
                    subjects[next].prev -= 1;
                if (subjects[next].prev > 0)
                    continue;
                time[next] += time[current] + 1;
                q.offer(next);
            }
        }
    }

}