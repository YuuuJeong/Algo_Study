import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// x번과 y번 집 사이에 양방향 도로가 있다고 했으므로 무방향 그래프
// 도시의 모든 집을 불이 켜진 길로 왕래할 수 있어야 함
// 즉, 가로등을 모두 켜두었을 때 드는 비용에서 모든 집들을 최소 비용으로 연결했을 때 드는 비용을 빼면 그게 바로 절약할 수 있는 최대 액수
public class BOJ6497_전력난 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			if (m == 0 && n == 0) {
				System.out.println(sb);
				break;
			}

			int[][] map = new int[m][m];
			int totalP = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				// x번 집 <--- z ---> y번 집
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());

				map[x][y] = map[y][x] = z;
				totalP += z;
			}

			int[] cost = new int[m];
			Arrays.fill(cost, Integer.MAX_VALUE);
			boolean[] visited = new boolean[m];

			cost[0] = 0;
			int mst = 0, cnt = 0;
			for (int i = 0; i < m; i++) {
				int minVertex = -1;
				int min = Integer.MAX_VALUE;

				for (int j = 0; j < m; j++) {
					if (!visited[j] && min > cost[j]) {
						minVertex = j;
						min = cost[j];
					}
				}

				visited[minVertex] = true;
				mst += min;

				if (++cnt == m) {
					break;
				}

				for (int j = 0; j < m; j++) {
					if (!visited[j] && map[minVertex][j] != 0 && cost[j] > map[minVertex][j]) {
						cost[j] = map[minVertex][j];
					}
				}
			}

			sb.append(totalP - mst).append("\n");
		}
	}
}
