import java.io.*;
import java.util.*;

public class BOJ13397 {
	static final int MAX = 0, MIN = 10001;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		
		int min = MIN;
		int max = MAX;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}
		
		int left = 0;
		int right = max - min;
		int ans = 0;
		while(left <= right) {
			int mid = left + (right - left) / 2;
			int cnt = 1;
			min = MIN;
			max = MAX;
			for(int i = 0; i < N; i++) {
				min = Math.min(min, arr[i]);
				max = Math.max(max, arr[i]);
				if(max - min > mid) {
					min = arr[i];
					max = arr[i];
					cnt++;
				}
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
