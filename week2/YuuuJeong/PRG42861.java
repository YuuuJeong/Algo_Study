import java.util.*;

// 섬 연결하
class Solution {
    public int[] parents;
    
    public int find(int a) {
        if (parents[a] == a) return a;
        else return parents[a] = find(parents[a]);
    }
    
    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) 
            parents[b] = a;
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parents = new int[n+1];
        for (int i = 1; i <= n; i++)
            parents[i] = i;
        
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
        for (int i = 0; i < costs.length; i++) {
            int a = costs[i][0];
            int b = costs[i][1];
            int val = costs[i][2];
            if (find(a) != find(b)) {
                union(a, b);
                answer += val;
            }
        }
        
        return answer;
    }
}