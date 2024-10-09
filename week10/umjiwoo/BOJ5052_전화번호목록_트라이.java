

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ5052_전화번호목록_트라이 {
	static class TrieNode {
		TrieNode[] children = new TrieNode[10]; // 0~9 숫자에 대한 자식 노드
		boolean isEndOfNumber; // 이 노드가 전화번호의 끝인지 여부

		public TrieNode() {
			this.isEndOfNumber = false;
		}
	}

	static class Trie {
		TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		// 전화번호 추가와 동시에 접두어 관계 파악
		public boolean insert(String phoneNumber) {
			TrieNode cur = root;
			boolean isNewNode = false; // 새로운 노드를 추가했는지 여부
			boolean isPrefix = false; // 접두어 관계가 있는지 여부

			for (int i = 0; i < phoneNumber.length(); i++) {
				int num = phoneNumber.charAt(i) - '0'; // 숫자 변환

				// 현재 노드의 자식에 이 숫자가 없다면 새로 추가
				if (cur.children[num] == null) {
					cur.children[num] = new TrieNode();
					isNewNode = true; // 새로운 노드를 추가한 경우
				}

				cur = cur.children[num]; // 그 다음 num을 삽입해주기 위해 새롭게 만들어진 트라이 노드로 cur 갱신

				// 새로운 노드가 계속 생성되지 않고 타고 내려오다가 마지막 노드를 만난 경우
				if (cur.isEndOfNumber) {
					isPrefix = true; // 앞서 insert 했던 phoneNumber가 지금 넣으려고 하는 번호의 접두어
				}
			}

			// phoneNumber의 모든 번호를 다 넣고 나서 맨 마지막 TrieNode의 isEndOfNumber=true 체크해주기
			cur.isEndOfNumber = true;

			// 만약 번호 중간에 새로운 노드를 만들지 않았거나, 이미 다른 번호가 이 번호의 접두어였다면
			if (!isNewNode || isPrefix) {
				// 넣으려고 하는 번호가 이미 다 들어가 있어서 중간에 TrieNode가 한 번도 생성되지 않았거나
				// 다른 번호가 지금 넣으려고 하는 번호의 접두어면
				return false; // 접두어 관계 o
			}

			return true; // 접두어 관계 x
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

		for (int t = 0; t < T; t++) {
			Trie trie = new Trie(); // 트라이 생성
			boolean isConsistent = true; // 일관성 여부 체크

			// 전화번호 입력
			int n = Integer.parseInt(br.readLine()); // 전화번호의 수
			for (int i = 0; i < n; i++) {
				String phoneNumber = br.readLine();
				if (!trie.insert(phoneNumber)) {
					isConsistent = false; // 입력받은 전화번호 중 접두어 관계가 하나라도 발견된 경우 일관성 x
				}
			}

			if (isConsistent) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}

		System.out.println(sb);
	}
}
