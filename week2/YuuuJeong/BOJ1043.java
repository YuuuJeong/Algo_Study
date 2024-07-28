import java.io.*;
import java.util.*;

public class BOJ1043 {
	public static ArrayList<Integer>[] party;
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        int n, m;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken()); 
        m = Integer.parseInt(st.nextToken()); 
        
        int k; 
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        int[] knowPeople = new int[k];
        for (int i = 0; i < k; i++) {
            knowPeople[i] = Integer.parseInt(st.nextToken());
        } 

        parent = new int[n + 1];
        party = new ArrayList[m];
        
        for (int i = 0; i < m; i++) { 
            party[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int partyNum = Integer.parseInt(st.nextToken());
            for (int j = 0; j < partyNum; j++) {
                party[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < m; i++) {
            int first_man = party[i].get(0);
            for (int j = 1; j < party[i].size(); j++) {
                union(first_man, party[i].get(j));
            } 
        }
        
        int count = 0;
        for (int i = 0; i < m; i++) {
            int leader = party[i].get(0);
            boolean flag = true;
            for (int j = 0; j < k; j++) {
                if (isitsame(leader, knowPeople[j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) count++;
          
        }
        System.out.println(sb.append(count).toString());
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a; 
        
    }

    public static int find(int a) {
        if (parent[a] == a) {
      
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    public static boolean isitsame(int a, int b) {
        if (find(a) == find(b)) { 
            return true;
        } else return false;
    }

}
