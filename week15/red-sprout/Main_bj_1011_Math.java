import java.io.*;
import java.util.*;

public class Main_bj_1011_Math {
	static long dist;
	static long solution(long x, long y) {
		long left = -1;
		long right = y - x + 1;
		dist = y - x;
		while(left + 1 < right) {
			long mid = (left + right) / 2;
			if(isArrived(mid)) {
				right = mid;
			} else {
				left = mid;
			}
		}
		return right;
	}
	static boolean isArrived(long target) {
		long result = 0;
		for(long l = target; l > 0; l -= 2) {
			result += l;
			if(result >= dist) return true;
		}
		return result >= dist;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			sb.append(solution(x, y)).append('\n');
		}
		System.out.print(sb.toString());
		br.close();
	}
}
