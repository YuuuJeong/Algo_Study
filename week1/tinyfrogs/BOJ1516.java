package tinyfrogs;

import java.io.*;
import java.util.*;

public class BOJ1516 {

    static class Building {
        int time;
        int prev;

        List<Integer> next;

        public Building() {
            this.time = 0;
            prev = 0;
            next = new ArrayList<>();
        }
    }

    static int[] result;
    static Building[] buildings;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        buildings = new Building[N + 1];
        result = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            buildings[i] = new Building();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            buildings[i].time = time;
            result[i] = time;
            while (true) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) break;
                buildings[num].next.add(i);
                buildings[i].prev++;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (buildings[i].prev == 0) {
                q.offer(i);
            }
        }

        solve();

        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append("\n");
        }

        System.out.println(sb);
    }

    static void solve() {
        while (!q.isEmpty()) {
            int current = q.poll();
            for (int next : buildings[current].next) {
                result[next] = Math.max(result[next], result[current] + buildings[next].time);
                buildings[next].prev--;
                if (buildings[next].prev > 0) continue;
                q.offer(next);
            }
        }
    }
}
