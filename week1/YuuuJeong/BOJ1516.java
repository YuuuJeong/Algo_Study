import java.io.*;
import java.util.*;

public class BOJ1516 {
	static List<List<Integer>> graph = new ArrayList<List<Integer>>();
	static int[] indegree;
	static List<List<Integer>> time = new ArrayList<List<Integer>>();
	static int N;
	public static void solution() {
		
		Queue<Integer> q = new LinkedList<>();
		 for (int i = 1; i <= N; i++) {
	            if (indegree[i] == 0) {
	                q.offer(i);
	            }
	     }
		 
		 while(!q.isEmpty()) {
			 int curIndex = q.poll();
			 for(int i = 0 ; i < graph.get(curIndex).size();i++) {
//				  System.out.println(curIndex);
				  int candiIndex = graph.get(curIndex).get(i);		
//				  System.out.println("candiIndex"+ candiIndex);
				  indegree[candiIndex]--;
				  time.get(candiIndex).add(time.get(candiIndex).get(0) + findMaxTime(curIndex));
//				  System.out.println(candiIndex+ Arrays.toString(time[candiIndex]));
//				  System.out.println(indegree[candiIndex]);
				  if(indegree[candiIndex] == 0) {
//					  System.out.println("inhere"+ candiIndex);
					  q.offer(candiIndex);
				  }
				  

			 }
		 }
	}
	
	public static int findMaxTime(int index) {
		int maxValue = 0;
		for(int i = 0 ; i < time.get(index).size(); i++) {
			if(time.get(index).get(i) > maxValue) {
				maxValue = time.get(index).get(i);
			}
		}
		
		return maxValue;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i <=N ; i++) {
			graph.add(new ArrayList<Integer>());
			time.add(new ArrayList<Integer>());
		}
		
		indegree = new int[N+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int count = 0;
			while(st.hasMoreTokens()) {
				int value = Integer.parseInt(st.nextToken());
				if(count == 0) {
					time.get(i).add(value);
				}
				else if(count >= 1 && value >= 1) {
					graph.get(value).add(i);
					indegree[i] += 1;
				}
				
				count ++;
			}
		}
		
//		System.out.print(Arrays.toString(indegree));
		solution();
		for(int i = 1; i<=N; i++) {
//			sb.append(time[i]).append("");
			System.out.println(findMaxTime(i));
		}

//		System.out.println(Arrays.toString(time));
		br.close();

	}

}
