import java.io.*;
import java.util.*;

class Solution {
	static int N, answer;
	static int[][] s;
	static Queue<Integer>[] wq, sq;
	static List<Person> list;
	static int[][] distance;
	static class Person {
		int row;
		int col;
		int time; // 계단 내려가는 시간(< K)
		int status; // 0: 이동 안함 1: 대기중 2: 계단 3: 이동 완료
		
		Person(int row, int col) {
			this.row = row;
			this.col = col;
			this.time = 0;
			this.status = 0;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input2383.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= T; test++) {
			sb.append("#").append(test).append(" ");
			
			N = Integer.parseInt(br.readLine());
			s = new int[2][3];
			wq = new Queue[2];
			sq = new Queue[2];
			list = new ArrayList<>();
			int idx = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					int data = Integer.parseInt(st.nextToken());
					if(data == 1) {
						list.add(new Person(i, j));
					} else if(data > 1) {
						s[idx] = new int[] {i, j, data};
						wq[idx] = new ArrayDeque<>();
						sq[idx] = new ArrayDeque<>();
						idx++;
					}
				}
			}
			
			distance = new int[list.size()][2];
			answer = Integer.MAX_VALUE;
			selection(0);
			
			sb.append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}
	
	public static void selection(int cnt) {
		if(cnt == list.size()) {
			initStatus();
			operation();
			return;
		}
		
		int row = list.get(cnt).row;
		int col = list.get(cnt).col;
		
		distance[cnt][0] = dist(row, col, s[0][0], s[0][1]);
		distance[cnt][1] = 0;
		selection(cnt + 1);
		
		distance[cnt][0] = dist(row, col, s[1][0], s[1][1]);
		distance[cnt][1] = 1;
		selection(cnt + 1);
	}
	
	public static void operation() {
		int time = 0;
		while(!check()) {
			for(int i = 0; i < list.size(); i++) {
				Person p = list.get(i);
				int d = distance[i][0];
				int idx = distance[i][1];
				if(p.status == 0) {
					if(d == time) {
						p.status = 1;
						wq[idx].offer(i);
					}
					continue;
				}
				if(p.status == 2) {
					p.time++;
					if(sq[idx].peek() != i) continue;
					if(p.time == s[idx][2]) {
						p.status = 3;
						sq[idx].poll();
						continue;
					}
					continue;
				}
				if(p.status == 3) continue;
			}
			for(int i = 0; i < list.size(); i++) {
				Person p = list.get(i);
				int idx = distance[i][1];
				if(p.status == 1) {
					if(wq[idx].peek() != i) continue;
					if(sq[idx].size() < 3) {
						p.status = 2;
						wq[idx].poll();
						sq[idx].offer(i);
						continue;
					}
					continue;
				}
			}
			time++;
		}
		answer = Math.min(answer, time);
	}
	
	public static void initStatus() {
		for(Person p : list) {
			p.time = 0;
			p.status = 0;
		}
	}
	
	public static boolean check() {
		for(Person p : list) if(p.status != 3) return false;
		return true;
	}
	
	public static int dist(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}
}
