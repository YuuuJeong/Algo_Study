import java.util.*;

class Solution {
    static int[] parent;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        for(int i = 0; i < costs.length; i++) {
            int[] edge = costs[i];
            if(find(edge[0]) == find(edge[1])) continue;
            union(edge[0], edge[1]);
            answer += edge[2];
        }
        return answer;
    }
    
    public int find(int x) {
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    
    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x > y) {
            parent[x] = y;
        } else {
            parent[y] = x;
        }
    }
}
