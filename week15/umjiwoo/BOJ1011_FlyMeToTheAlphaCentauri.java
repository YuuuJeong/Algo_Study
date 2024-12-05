import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1011_FlyMeToTheAlphaCentauri {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int dist = y - x;
			int sqrtD = (int) Math.sqrt(dist);

			if (dist == (sqrtD * sqrtD)) {
				sb.append(2 * sqrtD - 1);
			} else if ((sqrtD * sqrtD) < dist && dist <= (sqrtD * sqrtD) + sqrtD) {
				sb.append(2 * sqrtD);
			} else {
				sb.append(2 * sqrtD + 1);
			}

			sb.append("\n");
		}

		System.out.println(sb);
	}

}
