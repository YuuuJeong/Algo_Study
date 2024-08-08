package umjiwoo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1765 {
	static int[] parent;
    static int[] rank;
    
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
		StringBuilder sb=new StringBuilder();
		
		int n=Integer.parseInt(br.readLine());
		
		List<List<Integer>> eInfo=new ArrayList<>();
		List<List<Integer>> fInfo=new ArrayList<>();
		
		for(int i=0;i<n+1;i++) {
			eInfo.add(new ArrayList<>());
			fInfo.add(new ArrayList<>());
		}
		
		int m=Integer.parseInt(br.readLine());
		
		for(int i=1;i<m+1;i++) {
			st=new StringTokenizer(br.readLine());
			
			String e_or_f=st.nextToken();
			int mem1=Integer.parseInt(st.nextToken());
			int mem2=Integer.parseInt(st.nextToken());
			
			if(e_or_f.equals("E")) {
				eInfo.get(mem1).add(mem2);
				eInfo.get(mem2).add(mem1);
			}
			if(e_or_f.equals("F")){
				fInfo.get(mem1).add(mem2);
				fInfo.get(mem2).add(mem1);
			}
		}
		
//		for(List<Integer> e:eInfo) {
//			if(e.size()>1) {
//				for(int i=0;i<e.size();i++) {
//					for(int j=i+1;j<e.size();j++) {
//						fInfo.get(e.get(i)).add(e.get(j));
//					}
//				}
//			}
//		}
//		
//		System.out.println(fInfo);
		
		// Union-Find 초기화
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        // 친구 관계 처리 (Union)
        for (int i = 1; i <= n; i++) {
            for (int friend : fInfo.get(i)) {
                union(i, friend);
            }
        }

        // 적 관계 처리 (간접 친구 관계)
        for (int i = 1; i <= n; i++) {
            for (int enemy : eInfo.get(i)) {
                for (int indirectFriend : eInfo.get(enemy)) {
                    union(i, indirectFriend);
                }
            }
        }

        // 서로소 집합의 개수 구하기
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (parent[i] == i) {
                count++;
            }
        }

        System.out.println(count);
    }
}
