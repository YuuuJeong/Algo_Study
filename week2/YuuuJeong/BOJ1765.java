import java.io.*;
import java.util.*;
import java.io.*;
import java.util.*;
public class BOJ1765 {
    static int[] parent;
    static int[] enemy;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    int studentNum = Integer.parseInt(br.readLine().trim());
	    parent = new int[studentNum + 1];
	    enemy = new int[studentNum + 1];
	    
	
	    for (int i = 1; i <= studentNum; i++) {
	        parent[i] = i;
	        enemy[i] = 0; 
	    }
	
	    st = new StringTokenizer(br.readLine());
	    int relationNum = Integer.parseInt(st.nextToken());
	    
	    for (int i = 0; i < relationNum; i++) {
	        st = new StringTokenizer(br.readLine());
	        String type = st.nextToken();
	        int a = Integer.parseInt(st.nextToken());
	        int b = Integer.parseInt(st.nextToken());
	
	        if (type.equals("F")) {
	            union(a, b);
	        } else {
	
	            if (enemy[a] == 0) {
	                enemy[a] = b;
	            } else {
	                union(enemy[a], b);
	            }
	            if (enemy[b] == 0) {
	                enemy[b] = a;
	            } else {
	                union(enemy[b], a);
	            }
	        }
	    }
	
	
	    Set<Integer> teamSet = new HashSet<>();
	    for (int i = 1; i <= studentNum; i++) {
	        if (parent[i] == i) {
	            teamSet.add(i);
	        }
	    }
	
	    System.out.println(teamSet.size());
	}

	static int find(int x) {
	    if (x != parent[x]) {
	        parent[x] = find(parent[x]);
	    }
	    return parent[x];
	}

	static void union(int a, int b) {
	    a = find(a);
	    b = find(b);
	    if (a != b) {
	        parent[b] = a;
	    }
	}
}