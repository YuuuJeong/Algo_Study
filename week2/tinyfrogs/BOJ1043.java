package tinyfrogs;

import java.util.*;
import java.io.*;

public class BOJ1043 {

    static class Person {
        int parents;
        boolean lier;

        public Person() {
            lier = false;

        }
    }

    static Person[] persons;
    static List<Integer>[] party;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        persons = new Person[N + 1];

        for (int i = 0; i <= N; i++) {
            persons[i] = new Person();
            persons[i].parents = i;
        }

        st = new StringTokenizer(br.readLine(), " ");

        int lCount = Integer.parseInt(st.nextToken());

        for (int i = 0; i < lCount; i++) {
            int l = Integer.parseInt(st.nextToken());
            persons[l].lier = true;
        }

        party = new ArrayList[M];

        //각각의 파티 사람들 추가 + 진실을 아는 사람 union
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            party[i] = new ArrayList<>();
            int pCount = Integer.parseInt(st.nextToken());

            for (int j = 0; j < pCount; j++) {
                party[i].add(Integer.parseInt(st.nextToken()));
            }

            //진실을 아는사람 찾기
            for (int j = 0; j < pCount - 1; j++) {
                for (int k = 1; k < pCount; k++) {
                    union(party[i].get(j), party[i].get(k));
                }
            }
        }

        int result = 0;
        //거짓말을 할 수 있는 파티 수
        for (int i = 0; i < M; i++) {
            boolean check = false;
            for (Person p : persons) {
                if (persons[p.parents].lier) {
                    check = true;
                    break;
                }
            }

            if (!check)
                result++;
        }

        sb.append(result);
        System.out.print(sb.toString());
        br.close();

    }

    static int find(int x) {
        if (persons[x].parents == x)
            return x;

        return persons[x].parents = find(persons[x].parents);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (persons[x].lier)
            persons[y].parents = x;
        else if (persons[y].lier)
            persons[x].parents = y;
        else if (x == y)
            return;
        else if (x > y)
            persons[x].parents = y;
        else
            persons[y].parents = x;
    }
}
