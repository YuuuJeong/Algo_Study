import java.io.*;
import java.util.*;

public class BOJ2110 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		int[] dist = new int[N - 1];
		for(int i = 0; i < N - 1; i++) {
			dist[i] = arr[i + 1] - arr[i];
		}
		
		int left = 1;
		int right = arr[N - 1] - arr[0];
		int ans = 1;
		while(left <= right) {
			int mid = left + (right - left) / 2;
			int cnt = 1, tmp = 0;
			for(int i = 0; i < N - 1; i++) {
				tmp += dist[i];
				if(mid <= tmp) {
					cnt++;
					tmp = 0;
				}
			}
			
			if(cnt < C) {
				right = mid - 1;
			} else {
				ans = mid;
				left = mid + 1;
			}
		}
		
		System.out.println(ans);
		br.close();
	}
}
