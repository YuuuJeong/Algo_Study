import java.io.*;
import java.util.*;

public class Main_bj_8980_택배 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		int[][] info = new int[M][3];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
			info[i][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(info, (o1, o2) -> o1[1] - o2[1]);
		int answer = 0;
		int[] remain = new int[N + 1];
		Arrays.fill(remain, C);
		for(int i = 0; i < M; i++) {
			int[] cur = info[i];
			int start = cur[0];
			int end = cur[1];
			int box = cur[2];
			int min = Integer.MAX_VALUE;
			for(int j = start; j < end; j++) {
				min = Math.min(min, remain[j]);
			}
			box = Math.min(box, min);
			answer += box;
			for(int j = start; j < end; j++) {
				remain[j] -= box;
			}
		}
		System.out.println(answer);
		br.close();
	}
}
