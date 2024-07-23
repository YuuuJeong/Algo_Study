package tinyfrogs;

import java.io.*;
import java.util.*;

public class BOJ2458_2 {

    static class Person {
        List<Integer> prev;
        List<Integer> next;

        public Person() {
            prev = new ArrayList<>();
            next = new ArrayList<>();
        }
    }

    static Person[] people;
    static boolean[] visited;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int result = 0;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        people = new Person[N + 1];

        for (int i = 1; i <= N; i++) {
            people[i] = new Person();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            people[first].next.add(second);
            people[second].prev.add(first);
        }

        for (int i = 1; i <= N; i++) {
            //현재 위치에서 나보다 작은 사람들 구하는 함수
            visited = new boolean[N + 1];
            visited[i] = true;
            int prevCount = searchPrev(i);

            //현재 위치에서 나보다 큰 사람들 구하는 함수
            int nextCount = searchNext(i);

            if (prevCount + nextCount == N - 1) result++;
        }

        sb.append(result);
        System.out.println(sb);

        br.close();

    }

    static int searchPrev(int i) {
        int sum = 0;
        q.offer(i);
        while (!q.isEmpty()) {
            int current = q.poll();
            List<Integer> prevs = people[current].prev;
            if (!prevs.isEmpty()) {
                for (int prev : prevs) {
                    if (!visited[prev]) {
                        visited[prev] = true;
                        q.offer(prev);
                        sum++;
                    }
                }
            }
        }
        return sum;
    }

    static int searchNext(int i) {
        int sum = 0;
        q.offer(i);
        while (!q.isEmpty()) {
            int current = q.poll();
            List<Integer> nexts = people[current].next;
            if (!nexts.isEmpty()) {
                for (int next : nexts) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                        sum++;
                    }
                }
            }
        }
        return sum;
    }
}
