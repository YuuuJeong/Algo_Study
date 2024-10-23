import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CodeTree_왕실의기사대결 {
	// 상우하좌
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int L, N, Q;
	static int[][] map;
	static knight[] knights;
	static List<int[]> walls;
	static List<Integer> affectedKnight;

	static class knight {
		int r;
		int c;
		int h;
		int w;
		int k;
		int damage;
		boolean out;

		public knight(int r, int c, int h, int w, int k) {
			super();
			this.r = r;
			this.c = c;
			this.h = h;
			this.w = w;
			this.k = k;
			this.damage = 0;
			this.out = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		// map 정보 입력
		map = new int[L + 1][L + 1];
		walls = new ArrayList<>();
		for (int i = 1; i <= L; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= L; j++) {
				int mapInfo = Integer.parseInt(st.nextToken());
				map[i][j] = mapInfo;
				if (mapInfo == 2) {
					walls.add(new int[] { i, j });
				}
			}
		}

		// 기사 정보 입력
		knights = new knight[N + 1];
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken()); // 방패 세로
			int w = Integer.parseInt(st.nextToken()); // 방패 가로
			int k = Integer.parseInt(st.nextToken()); // 체력

			knight knight = new knight(r, c, h, w, k);
			knights[n] = knight;
		}

		// 명령 입력
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()); // i번째 기사(이미 사라진 기사 주어질 수 있음)
			int d = Integer.parseInt(st.nextToken()); // d방향으로 1칸 이동

			if (knights[i].out) // 이미 맵에서 사라진 기사에게 내려진 명령은 스킵
				continue;

			if (checkMove(i, d)) {
				affectedKnight = new ArrayList<>();
				moveKnight(i, d);
				for (Integer k : affectedKnight) {
					calcDamage(k);
				}
			}
		}

		int res = 0;
		for (int k = 1; k <= N; k++) {
			if (!knights[k].out) {
				res += knights[k].damage;
			}
		}

		System.out.println(res);
	}

	public static boolean checkMove(int i, int d) {
		for (int r = 0; r < knights[i].h; r++) {
			for (int c = 0; c < knights[i].w; c++) {
				// 방패 영역까지를 기사 하나로 취급하므로 방패 크기 고려해주기
				int nr = knights[i].r + dr[d];
				int nc = knights[i].c + dc[d];

				// 기사가 움직인 위치가 맵 밖(체스판 밖도 벽으로 간주)이면 아무것도 안하고 return
				if (nr < 1 || nr > L || nc < 1 || nc > L) {
					return false;
				}

				// 기사가 움직인 위치가 벽이어도 아무것도 안하고 return
				if (map[nr][nc] == 2) {
					return false;
				}

				for (int k = 1; k <= N; k++) {
					if (k == i)
						continue;
					if (!knights[k].out && knights[k].r == nr && knights[k].c == nc) {
						checkMove(k, d);
					}
				}
			}
		}
		return true;
	}

	public static void moveKnight(int i, int d) {
		int startR = knights[i].r;
		int startC = knights[i].c;

		for (int r = 0; r < knights[i].h; r++) {
			for (int c = 0; c < knights[i].w; c++) {
				// 방패도 같이 이동
				int shieldR = startR + r;
				int shieldC = startC + c;

				int nr = shieldR + dr[d];
				int nc = shieldC + dc[d];

				if (nr < 1 || nr > L || nc < 1 || nc > L) {
					return;
				}

				if (map[nr][nc] == 2) {
					return;
				}

				for (int k = 1; k <= N; k++) {
					if (k == i)
						continue;
					if (!knights[k].out && knights[k].r == nr && knights[k].c == nc) {
						affectedKnight.add(k);
						moveKnight(k, d);
					}
				}
			}
		}

		knights[i].r = startR + dr[d];
		knights[i].c = startC + dc[d];

		return;
	}

	public static void calcDamage(int k) {
		// 기사의 현재 위치에서 w x h 내에 놓여있는 함정 수만큼 대미지
		int startR = knights[k].r;
		int startC = knights[k].c;

		int d = 0;
		for (int r = 0; r < knights[k].h; r++) {
			for (int c = 0; c < knights[k].w; c++) {
				if (map[startR + r][startC + c] == 1)
					d++;
			}
		}

		// 현재 체력 이상의 대미지를 받는 경우 맵에서 사라지게 됨
		knights[k].damage += d;
		if (knights[k].k - knights[k].damage <= 0)
			knights[k].out = true;

		return;
	}
}
