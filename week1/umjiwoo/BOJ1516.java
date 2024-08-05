import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1516 {

	public static void main(String[] args)  throws Exception{
		Scanner sc=new Scanner(System.in);
		
		int T=sc.nextInt();
		int[] buildTime=new int[T+1];
		int[] degree=new int[T+1];
		
		// 인접리스트
		List<List<Integer>> adj_list=new ArrayList<>();
		for(int t=1;t<=T+1;t++) {
			adj_list.add(new ArrayList<>());
		}

		// 입력받은 정보 받기
		for(int t=1;t<=T;t++) {
			// t번째 건물 짓는 데 걸리는 시간
			buildTime[t]=sc.nextInt();
			
			// 먼저 지어져야 하는 건물 정보 받기
			while(true) {
				int req=sc.nextInt();
				if(req==-1) break;
				else {
					// 먼저 지어져야 하는 건물 번호 리스트에 나중에 지어져야할 건물 추가
					adj_list.get(req).add(t);
					// 앞에 지어져야 하는 건물 수 카운트
					degree[t]++;
				}
			}
		}
		
		// 위상정렬 큐
		Queue<Integer> queue=new LinkedList<>();
		int[] res=new int[T+1];
		
		// 먼저 지어져야 하는 건물이 없는 경우 res[t]는 곧 buildTime[t]
		for(int t=1;t<=T;t++) {
			if(degree[t]==0) {
				queue.add(t);
				res[t]=buildTime[t];
			}
		}
		
		while(!queue.isEmpty()) {
			int cur=queue.poll(); // 큐의 맨 앞 원소 retrieve
			
			// cur 건물이 필요한 원소들 순회
			for(int nxt:adj_list.get(cur)) {
				res[nxt]=Math.max(res[nxt], res[cur]+buildTime[nxt]);
				
				// 앞에 지어져야할 건물이 더이상 없으면 큐에 넣기
				// 아직 있으면 degree[nxt] 값만 1 감소되고 넘어감
				if(--degree[nxt]==0) {
					queue.add(nxt);
				}
			}
		}
		for(int t=1;t<=T;t++) {
			System.out.println(res[t]);
		}
	}
}