import java.io.*;
import java.util.*;

public class BOJ1477 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] arr = new int[N + 2];
		arr[N + 1] = L;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		int[] dist = new int[N + 1];
		for(int i = 0; i <= N; i++) {
			dist[i] = arr[i + 1] - arr[i];
		}
		
		int left = 1;
		int right = L - 1;
		int ans = 0;
		while(left <= right) {
			int mid = left + (right - left) / 2;
			
			int cnt = 0;
			for(int i = 0; i < dist.length; i++) {
				cnt += dist[i] % mid == 0 ? (dist[i] / mid) - 1 : dist[i] / mid;
			}
			
			if(cnt > M) {
				left = mid + 1;
			} else {
				ans = mid;
				right = mid - 1;
			}
		}
		
		System.out.println(ans);
		br.close();
	}
}
