import java.io.*;
import java.util.*;

public class Main {

   public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken()); 
		int Q = Integer.parseInt(st.nextToken());
		
		int[] fuels = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			fuels[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(fuels);
		for(int i = 0; i < Q; i++) {
			int middleFuel = Integer.parseInt(br.readLine());
			int beforeSize = getBeforeMiddleFuelSize(fuels, middleFuel);
			sb.append(beforeSize * (N-beforeSize-1)).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	public static int getBeforeMiddleFuelSize(int[] fuels, int targetFuel) {
		for(int i = 0; i < fuels.length; i++) {
			if(fuels[i] == targetFuel) {
				return i;
			}
		}
		
		return fuels.length-1;
	}

}
