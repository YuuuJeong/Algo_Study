import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// x번과 y번 집 사이에 양방향 도로가 있다고 했으므로 무방향 그래프
// 도시의 모든 집을 불이 켜진 길로 왕래할 수 있어야 함
// 즉, 가로등을 모두 켜두었을 때 드는 비용에서 모든 집들을 최소 비용으로 연결했을 때 드는 비용을 빼면 그게 바로 절약할 수 있는 최대 액수
public class BOJ6497_전력난_Kruskal {
	static int[] parents;

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

			int[][] map = new int[n][3];

			int totalP = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				// x번 집 <--- z ---> y번 집
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());

				map[i][0] = x;
				map[i][1] = y;
				map[i][2] = z;
				totalP += z;
			}

			Arrays.sort(map, (a, b) -> Integer.compare(a[2], b[2]));

			parents = new int[m];
			for (int i = 0; i < m; i++) {
				parents[i] = i;
			}

			int p = 0, v = 0;
			for (int[] _m : map) {
				if (union(_m[0], _m[1])) {
					p += _m[2];
					if (v++ == m - 1)
						break;
				}
			}

			sb.append(totalP - p).append("\n");
		}
	}

	static int find(int num) {
		if (parents[num] == num) {
			return num;
		} else
			return parents[num] = find(parents[num]);
	}

	static boolean union(int num1, int num2) {
		int root1 = find(num1);
		int root2 = find(num2);

		if (root1 == root2)
			return false;

		parents[root2] = root1;
		return true;
	}
}
