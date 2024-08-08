import java.io.*;
import java.util.*;

public class BOJ1477 {
		static int N;
		static int M;
		static int L;
		static int[] breakPlacePos;
		static int[] distance;
		static int upper;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		breakPlacePos = new int[N+2];
		
		st = new StringTokenizer(br.readLine());
		breakPlacePos[0] = 0;
		breakPlacePos[1] = L;
		
		for(int i = 2; i < N+2; i++) {
			breakPlacePos[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(breakPlacePos);
		distance = new int[N+1];
		
		for(int i = 0 ; i < N+1; i++) {
			distance[i] = breakPlacePos[i+1] - breakPlacePos[i]-1;
		}
		
		int first = 1 ;
		int end = L-1;
		while(first <= end) {
			int middle = (first + end) / 2;
			int count = 0;
			for(int i = 0; i < N+1; i++) {
				count += (distance[i] / middle);
			}
			
			if(count > M) {
				first = middle+1;
			} else {
				end = middle-1;
			}
		}
		
		System.out.println(first);
		
	}
	

}

