import java.util.HashMap;
import java.util.Map;

class PRG_가사검색 {
	static class TrieNode {
		// 단어 길이 기억용 해시맵, 단어길이n:n길이를 가진 단어 개수
		Map<Integer, Integer> cntWordLenFromNow = new HashMap<>();
		Map<Character, TrieNode> children = new HashMap<>(); // 연결될 트라이 노드
	}

	class Trie {
		TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		public void insert(String word) {
			TrieNode cur = root;
			char[] wordToChar = word.toCharArray(); // 입력받은 문자열을 각 문자로 쪼개기
			for (int i = 0; i < wordToChar.length; i++) {
				// 트라이 노드에 단어의 길이 저장;쿼리의 모든 문자가 와일드카드일 수 있으므로 root부터 길이 별 단어 개수 카운팅 必
				int wordLen = word.length() - i; // 남은 문자 수
				cur.cntWordLenFromNow.compute(wordLen, (k, v) -> (v == null) ? 1 : v + 1);

				// 트라이 타고 가다가 다음에 오는 문자가 없으면 새로운 트라이노드 생성 & 추가
				// 있으면 해당 트라이노드 리턴
				cur = cur.children.computeIfAbsent(wordToChar[i], k -> new TrieNode());
			}
		}

		public int search(String word) {
			TrieNode cur = root;
			char[] wordToChar = word.toCharArray();
			for (int i = 0; i < wordToChar.length; i++) {
				// 문자가 와일드카드인 경우 해당 지점으로부터의 문자 개수가 동일한 트라이노드 개수 가져오기
				if (wordToChar[i] == '?') {
					int remainingLen = word.length() - i;
					return cur.cntWordLenFromNow.getOrDefault(remainingLen, 0);
				}

				// 문자가 와일드카드가 아닌 경우 다음 트라이노드 탐색
				cur = cur.children.get(wordToChar[i]);
				if (cur == null) {
					return 0; // 쿼리가 트라이에 존재하지 않는 경우
				}
			}
			return 0;
		}
	}

	public int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length]; // 쿼리 개수만큼 크기를 설정

		Trie trieF = new Trie();
		Trie trieB = new Trie();
		for (int i = 0; i < words.length; i++) {
			String word = words[i];

			// 모든 단어에 대해 정방향,역방향 트라이노드 생성
			// 정방향
			trieF.insert(word);

			// 역방향
			word = new StringBuilder(word).reverse().toString(); // 문자열 뒤집기
			trieB.insert(word);
		}

		for (int i = 0; i < queries.length; i++) {
			int ans = 0;
			String query = queries[i];

			if (query.charAt(0) != '?') {
				// 와일드 카드가 접미사인 경우 -> 정방향으로 저장한 트라이노드들 중에서 탐색
				ans = trieF.search(query);
			} else {
				// 와일드 카드가 접두사인 경우 -> 문자열 뒤집은 후 역방향으로 저장한 트라이노드들 중에서 탐색
				query = new StringBuilder(query).reverse().toString();
				ans = trieB.search(query);
			}

			answer[i] = ans; // answer 배열에 결과 저장
		}

		return answer;
	}
}
