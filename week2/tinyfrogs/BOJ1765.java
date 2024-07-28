package tinyfrogs;

import java.io.*;
import java.util.*;

public class BOJ1765 {

    static class Person {
        int parents;
        List<Integer> enemy;

        public Person() {
            enemy = new ArrayList<>();
        }
    }

    static Person[] people;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int m = Integer.parseInt(st.nextToken());

        people = new Person[n + 1];
        for (int i = 0; i < n + 1; i++) {
            people[i] = new Person();
            people[i].parents = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String relation = st.nextToken();
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            if (relation.equals("E")) {
                people[first].enemy.add(second);
                people[second].enemy.add(first);
            }

            if (relation.equals("F")) {
                union(first, second);
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int e : people[i].enemy)
                for (int f : people[e].enemy)
                    union(i, f);
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!list.contains(find(people[i].parents)))
                list.add(find(people[i].parents));
        }

        sb.append(list.size());
        System.out.println(sb);

    }

    static int find(int x) {
        if (people[x].parents == x)
            return x;

        return people[x].parents = find(people[x].parents);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y)
            return;

        if (x < y)
            people[y].parents = x;
        else
            people[x].parents = y;
    }
}
