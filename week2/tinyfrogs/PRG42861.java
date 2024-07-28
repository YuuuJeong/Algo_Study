package tinyfrogs;

import java.util.*;

class Solution {

    static int[] parent;

    public int solution(int n, int[][] costs) {
        int answer = 0;

        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;

        //낮은 비용으로 정렬
        Arrays.sort(costs, (o1, o2) -> Integer.compare(o1[2], o2[2]));

        //최소 비용 선 연결하기
        for (int i = 0; i < costs.length; i++) {
            int first = find(costs[i][0]);
            int second = find(costs[i][1]);
            int cost = costs[i][2];
            if (first != second) {
                union(first, second);
                answer += cost;
            }
        }

        return answer;
    }

    static int find(int x) {
        if (parent[x] == x)
            return x;

        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x > y)
            parent[x] = y;
        else
            parent[y] = x;
    }
}