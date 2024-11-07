import java.io.*;
import java.util.*;

public class Main_bj_5710_전기요금 {
	static int solution(int A, int B) {
		int totalWh = g(A);
		int left = -1;
		int right = totalWh + 1;
		while(left + 1 < right) {
			int mid = (left + right) / 2;
			if(f(totalWh - mid) - f(mid) > B) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return f(right);
	}
	static int f(int x) {
		if(x <= 100) return 2 * x;
		if(x <= 10000) return 2 * 100 + 3 * (x - 100);
		if(x <= 1000000) return 2 * 100 + 3 * 9900 + 5 * (x - 10000);
		return 2 * 100 + 3 * 9900 + 5 * 990000 + 7 * (x - 1000000);
	}
	static int g(int y) {
		if(y <= 200) return y / 2;
		if(y <= 2 * 100 + 3 * 9900) return 100 + (y - 2 * 100) / 3;
		if(y <= 2 * 100 + 3 * 9900 + 5 * 990000) return 10000 + (y - 2 * 100 - 3 * 9900) / 5;
		return 1000000 + (y - 2 * 100 - 3 * 9900 - 5 * 990000) / 7;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			if(A == 0 && B == 0) break;
			sb.append(solution(A, B)).append('\n');
		}
		System.out.println(sb.toString().trim());
		br.close();
	}
}
