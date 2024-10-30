import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ16954_움직이는미로탈출 {
	static char[][] map;
	static Deque<int[]> position;
	static boolean[][] visited;

	// 상 하 좌 우 안움직임 좌상 우상 좌하 우하
	static int[] dr = { -1, 1, 0, 0, 0, -1, -1, 1, 1 };
	static int[] dc = { 0, 0, -1, 1, 0, -1, 1, -1, 1 };

	static boolean arrived;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[8][8];
		for (int i = 0; i < 8; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		position = new ArrayDeque<>();
		position.offer(new int[] { 7, 0 });
		arrived = false;

		for (int i = 0; i < 9; i++) { // 벽 최대 이동 횟수
			if (arrived)
				break;

			visited = new boolean[8][8];

			int size = position.size(); // 돌면서 큐 사이즈 계속 달라지므로 처음에 받아놓고 시작
			for (int j = 0; j < size; j++) {
				int[] p = position.poll();

				if (map[p[0]][p[1]] == '#')
					continue;

				moveCharacter(p[0], p[1]);
			}
			moveWall();
//			for (char[] m : map) {
//				System.out.println(Arrays.toString(m));
//			}
//			System.out.println();
		}

		if (position.size() > 0)
			System.out.println(1);
		else
			System.out.println(0);

	}

	static void moveCharacter(int r, int c) {
		for (int d = 0; d < 9; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (isInRange(nr, nc) && !visited[nr][nc] && map[nr][nc] != '#') {
				if (nr == 0 && nc == 7) {
					arrived = true;
				}
				visited[nr][nc] = true;
				position.offer(new int[] { nr, nc });
			}
		}
		return;
	}

	static void moveWall() {
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				if (map[i][j] == '#') {
					map[i][j] = '.';
					if (i + 1 < 8)
						map[i + 1][j] = '#';
				}
			}
		}
	}

	static boolean isInRange(int r, int c) {
		if (r >= 0 && r < 8 && c >= 0 && c < 8)
			return true;
		else
			return false;
	}
}
