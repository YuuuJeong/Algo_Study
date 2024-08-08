import java.io.*;
import java.util.*;
public class BOJ10775 {
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int gateNum = Integer.parseInt(st.nextToken());
		
		parents = new int[gateNum+1];
		
		for(int i = 1; i<=gateNum ; i++) {
			parents[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		int planeNum = Integer.parseInt(st.nextToken());
		int count = 0;
		for(int i = 0 ; i < planeNum; i++) {
			st = new StringTokenizer(br.readLine());
			int target = Integer.parseInt(st.nextToken());
			if(find(target) == 0 ) break;
			count++;
			union(find(target), find(target)-1);
		}
//		for(int i = 0 ; i < planeNum; i++) {
//			st = new StringTokenizer(br.readLine());
//			int endGateNum = Integer.parseInt(st.nextToken());
//			boolean isCan = false;
//			for(int j = endGateNum; j >= 1; j--) {
////				System.out.println("j" + j);
////				System.out.println(Arrays.toString(isDoking));
//				if(!isDoking[j]) {
//					isDoking[j] = true;
//					isCan = true;
//					break;
//				}
//			}
//			
//			if(isCan) count++;
//			if(!isCan) break;
//		}
		
		sb.append(count);
		System.out.println(sb.toString());
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		parents[a] = b;
	}
	
	static int find(int x) {
		if(x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
	
}
