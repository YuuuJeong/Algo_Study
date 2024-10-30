import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 

 
public class BOJ_1005 {
    static int n;   
    static int k;   
    static int[] time;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
 
        for(int t= 0; t<T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            time = new int[n+1];
 
            List<List<Integer>> list = new ArrayList<List<Integer>>();
            for(int i=0; i<n+1; i++)
                list.add(new ArrayList<Integer>());
 
            int[] indegree = new int[n+1];
 
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++)
                time[i] = Integer.parseInt(st.nextToken());
    
            for(int i=0; i<k; i++) {
                st = new StringTokenizer(br.readLine());
    
                int preBuilding = Integer.parseInt(st.nextToken());
                int afterBuilding= Integer.parseInt(st.nextToken());
    
                list.get(preBuilding).add(afterBuilding);
                indegree[afterBuilding]++;
            }
 
            int dest = Integer.parseInt(br.readLine());
    
            sb.append(topologicalSort(indegree, list, dest)+"\n"); 
            
        }
        
        System.out.println(sb);
    }
 
    static int topologicalSort(int[] indegree, List<List<Integer>> list, int destination) {
        Queue<Integer> q = new ArrayDeque<>();
        int[] result = new int[n+1];
 

        for(int i=1; i<=n; i++) {
            result[i] = time[i];
            if(indegree[i] == 0) q.offer(i);
        }
 
 
        while(!q.isEmpty()) {
            int node = q.poll();
 
            for(Integer i : list.get(node)) {
                result[i] = Math.max(result[i], result[node] + time[i]);
                indegree[i]--;
 
                if(indegree[i] == 0)
                    q.offer(i);
            }
        }
 
        return result[destination];
    }
}
