import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11779_최소비용구하기2 {
	static int n, map[][];
	static List<Integer> path;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 도시 수
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken()); // 버스 수

		map = new int[m][3];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int startI = Integer.parseInt(st.nextToken()); // 출발 도시 번호
			int endI = Integer.parseInt(st.nextToken()); // 도착 도시 번호
			int cost = Integer.parseInt(st.nextToken()); // 출발도시->도착도시 비용

			map[startI][endI] = cost;
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		pq.offer(new int[] { start, 0 }); // 시작점 ~ 시작점 가중치 = 0

		boolean[] visited = new boolean[n];

		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);

		dist[start] = 0;
		path = new ArrayList<>();
		dijkstra(dist, visited, pq);
	}

	static void dijkstra(int[] dist, boolean[] visited, PriorityQueue<int[]> pq) {
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int curS = cur[0];
			int curCost = cur[2];

			path.add(curS);

			for (int[] _m : map) {
				int neighbor = _m[0];
				int nCost = _m[1];
			}
		}
	}
}
