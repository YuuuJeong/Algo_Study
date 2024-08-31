import java.io.*;
import java.util.*;

class Solution_4014_활주로건설 {
	static int N, X;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int test = 1; test <= T; test++) {
			sb.append("#").append(test).append(" ");
			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 0;
			for(int i = 0; i < N; i++) {
				if(isRoad(map[i])) answer++;
			}
			
			map = rotation(map);
			for(int i = 0; i < N; i++) {
				if(isRoad(map[i])) answer++;
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}
	
	public static boolean isRoad(int[] arr) {
		int left = 0, right = 0;
		int[] height = {-1, -1, arr[0]};
		while(left < N) {
			height[0] = height[1];
			height[1] = height[2];
			height[2] = -1;
			for(right = left; right < N; right++) {
				if(arr[right] != height[1]) {
					height[2] = arr[right];
					break;
				}
			}
			if(!check(height, left, right)) return false;
			left = right;
		}
		return true;
	}
	
	public static boolean check(int[] height, int left, int right) {
		if(height[0] > height[1]) {
			if(height[0] > height[1] + 1) return false;
			if(height[1] < height[2]) {
				if(height[1]  + 1 < height[2]) return false;
				if(right - left < 2 * X) return false;
				return true;
			}
			if(right - left < X) return false;
			return true;
		}
		
		if(height[1] < height[2]) {
			if(height[1] + 1 < height[2]) return false;
			if(right - left < X) return false;
			return true;
		}
		
		return true;
	}
	
	public static int[][] rotation(int[][] map) {
		int[][] result = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				result[j][N - 1 - i] = map[i][j];
			}
		}
		return result;
	}
}
