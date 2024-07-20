
import java.io.*;
import java.util.*;

public class BOJ2458 {
	static List<List<Integer>> taller = new ArrayList<List<Integer>>();
	static List<List<Integer>> smaller= new ArrayList<List<Integer>>();
	static int N;
	static int M;
	static int ans;
	public static void solution() {
		for(int i = 1; i <= N; i++) {
			int smallCount = 0;
			int tallCount = 0;
			Queue<Integer> q = new LinkedList<>();
			boolean[] visited = new boolean[N+1];
			q.offer(i);
			while(!q.isEmpty()) {
				int value = q.poll();
				for(int smallIndex = 0; smallIndex < smaller.get(value).size(); smallIndex++) {
					int curValue = smaller.get(value).get(smallIndex);
					if(visited[curValue] == false) {
						visited[curValue] = true;
						smallCount ++;
						q.offer(smaller.get(value).get(smallIndex));
					}
				}
			}
			
			q.offer(i);
			while(!q.isEmpty()) {
				int value = q.poll();
				for(int tallIndex = 0; tallIndex < taller.get(value).size(); tallIndex++) {
					int curValue = taller.get(value).get(tallIndex);
					if(visited[curValue] == false) {
						visited[curValue] = true;
						tallCount ++;
						q.offer(curValue);
					}
				}
			}
			
			if((smallCount + tallCount) == N-1 ) {
				ans += 1;
			}
		}
		
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i <= N; i++) {
			taller.add(new ArrayList<Integer>());
			smaller.add(new ArrayList<Integer>());
			
		}
		
		for(int i  = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int smallS = Integer.parseInt(st.nextToken());
			int tallS = Integer.parseInt(st.nextToken());
			smaller.get(tallS).add(smallS);
			taller.get(smallS).add(tallS);
		}
		
		solution();
		
		System.out.println(ans);
		br.close();
	}

}
