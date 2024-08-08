import java.io.*;
import java.util.*;

 
public class BOJ2110 {
	
	public static int[] house;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		house = new int[N];
		
		for(int i = 0; i < N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(house);	
		
		
		int lower = 1;		
		int upper = house[N - 1] - house[0] + 1;	
		
		while(lower < upper) {	
			
			int mid = (upper + lower) / 2;
			
			if(canInstall(mid) < M) {
				upper = mid;
			}
			else {
				lower = mid + 1;
			}
		}
		System.out.println(lower - 1);
	
}

	public static int canInstall(int distance) {

		int count = 1;
		int lastLocate = house[0];
		
		for(int i = 1; i < house.length; i++) {
			int locate = house[i];
			
			if(locate - lastLocate >= distance) {
				count++;
				lastLocate = locate;
			}
		}
		return count;
		
	}
	
	
}
