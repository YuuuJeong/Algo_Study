package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1005_ACM_Craft {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 건물 개수
			int K = Integer.parseInt(st.nextToken()); // 건설 순서 규칙 개수

			int[] demandTime = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++) { // 각 건물의 건설 소요 시간
				demandTime[n] = Integer.parseInt(st.nextToken());
			}

			ArrayList<ArrayList<Integer>> adjBuilding = new ArrayList<>();
			for (int n = 0; n <= N; n++) {
				adjBuilding.add(new ArrayList<>()); // 각 건물의 선행 건설 정보를 담기 위한 ArrayList 초기화
			}

			int[] inDegree = new int[N + 1];
			for (int k = 1; k <= K; k++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				adjBuilding.get(x).add(y); // x 건설 후 지을 수 있는 건물 y 넣기
				inDegree[y]++;
			}

			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken()); // 마지막으로 건설해야 하는 건물 번호

			Queue<Integer> queue = new ArrayDeque<>();
			for (int i = 1; i <= N; i++) {
				if (inDegree[i] == 0) {
					queue.offer(i);
				}
			}

			int[] finTime = new int[N + 1];
			for (int no = 1; no <= N; no++) {
				finTime[no] = demandTime[no];
			}

			while (!queue.isEmpty()) {
				int buildingNo = queue.poll();

				List<Integer> adjs = adjBuilding.get(buildingNo);

				for (int adj : adjs) {
					inDegree[adj]--; // 현재 건물이 지어져야 지어질 수 있는 건물들의 진입차수 1씩 감소
					// 여러 개의 건물이 지어진 후에야 지어질 수 있는 건물은 inDegree[adj]==0 조건을 만족하지 못할 수 있음
					// 현재 완공된 buildingNo번 건물 이후 연결된 건물의 완공 시간 우선 갱신해주기
					if (finTime[adj] < demandTime[adj] + finTime[buildingNo]) {
						finTime[adj] = demandTime[adj] + finTime[buildingNo];
					}
					if (inDegree[adj] == 0) {
						queue.offer(adj);
						// if (finTime[adj] < demandTime[adj] + finTime[buildingNo]) {
						// finTime[adj] = demandTime[adj] + finTime[buildingNo];
						// }
						// 여기에서 finTime 갱신 시 queue에 나중에 들어간 건물 때문에 finTime의 갱신이 올바르게 이뤄지지 않음
					}
				}
			}

			sb.append(finTime[W]).append("\n");
		}
		System.out.println(sb);
	}

}
