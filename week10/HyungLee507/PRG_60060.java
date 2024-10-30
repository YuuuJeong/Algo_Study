import java.util.HashMap;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children;
    Map<Integer, Integer> lengthCount; // ? 이후의 해당 길이의 단어 개수 저장

    TrieNode() {
        children = new HashMap<>();
        lengthCount = new HashMap<>();
    }
}

class Trie {
    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    // 단어를 트라이에 삽입하면서 각 노드의 lengthCount를 갱신
    public void insert(String word) {
        TrieNode node = root;
        int length = word.length();

        for (char ch : word.toCharArray()) {
            node.lengthCount.put(length, node.lengthCount.getOrDefault(length, 0) + 1);
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.lengthCount.put(length, node.lengthCount.getOrDefault(length, 0) + 1); // 마지막 노드도 갱신
    }

    // 쿼리에 맞는 단어 개수 찾기
    public int countMatches(String query) {
        TrieNode node = root;
        int length = query.length(); // 쿼리의 길이

        for (char ch : query.toCharArray()) {
            if (ch == '?') {
                return node.lengthCount.getOrDefault(length, 0); // 해당 길이에 해당하는 단어 개수 반환
            }
            if (!node.children.containsKey(ch)) {
                return 0; // 매칭되는 단어가 없으면 0 반환
            }
            node = node.children.get(ch);
        }
        return node.lengthCount.getOrDefault(length, 0); // 해당 길이에 해당하는 단어 개수 반환
    }
}

class Solution {
    public int[] solution(String[] words, String[] queries) {
        Trie trie = new Trie();
        Trie reverseTrie = new Trie();

        for (String word : words) {
            trie.insert(word); //  정방향 삽입
            reverseTrie.insert(new StringBuilder(word).reverse().toString()); // 역방향 삽입
        }

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            if (query.charAt(0) == '?') {
                // 접미사 패턴일 경우
                String reversedQuery = new StringBuilder(query).reverse().toString();
                result[i] = reverseTrie.countMatches(reversedQuery);
            } else {
                // 접두사 패턴일 경우
                result[i] = trie.countMatches(query);
            }
        }

        return result;
    }
}
