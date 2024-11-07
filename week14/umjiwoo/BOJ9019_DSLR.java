import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9019_DSLR {
	static class Node {
		int num;
		String command;

		Node(int num, String command) {
			this.num = num;
			this.command = command;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());

			sb.append(bfs(start, target)).append("\n");
		}
		System.out.print(sb);
	}

	// BFS를 통해 최소 명령어를 찾기
	static String bfs(int start, int target) {
		boolean[] visited = new boolean[10000]; // 연산으로 나올 수 있는 최대 값 : 9999
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(start, ""));
		visited[start] = true;

		while (!queue.isEmpty()) {
			Node current = queue.poll();

			if (current.num == target) {
				return current.command;
			}

			// D: 현재 값 * 2 (9999보다 크면 % 10000으로 조정)
			int nextD = (current.num * 2) % 10000;
			if (!visited[nextD]) { // 현재 수에 이미 도달한 적 있음 ; 더 가봤자 연산 횟수 늘어남 ; 가지 않음
				visited[nextD] = true;
				queue.add(new Node(nextD, current.command + "D"));
			}

			// S: 현재 값 - 1 (0이면 9999로)
			int nextS = (current.num == 0) ? 9999 : current.num - 1;
			if (!visited[nextS]) {
				visited[nextS] = true;
				queue.add(new Node(nextS, current.command + "S"));
			}

			// L, R 연산은 연산으로 나올 수 있는 최대 자리수가 4자리 수임을 이용
			// L: 왼쪽으로 회전
			// (num %1000) * 10 => 왼쪽으로 한 칸 땡겨짐
			// 가장 큰 자리의 수가 1000의 자리 ; 몫이 맨 끝자리로 가서 붙음 & 100의 자리 이하 ; 0으로 채워짐
			int nextL = (current.num % 1000) * 10 + current.num / 1000;
			if (!visited[nextL]) {
				visited[nextL] = true;
				queue.add(new Node(nextL, current.command + "L"));
			}

			// R: 오른쪽으로 회전
			// (num % 10) * 1000 => 마지막 자리 수만 남음 * 1000
			// num / 10 => 마지막 자리 외 앞의 수 남음
			int nextR = (current.num % 10) * 1000 + current.num / 10;
			if (!visited[nextR]) {
				visited[nextR] = true;
				queue.add(new Node(nextR, current.command + "R"));
			}
		}
		return ""; // 미도달 시 빈 문자열 반환
	}
}
