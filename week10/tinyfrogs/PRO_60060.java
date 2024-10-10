package tinyfrogs;

import java.util.*;

public class PRO_60060 {

    static class Trie{
        
        //해당 Trie 경로에 몇 개의 문자열이 존재하는지 저장
        Map<Integer, Integer> lengthMap = new HashMap<>();
        
        //문자열 개수
        Trie[] nodes = new Trie[26];

        /*
        문자열 삽입
        문자열을 삽입할 때 문자열의 길이를 기록합니다.
         */
        public void insert(String word){
            Trie root = this;
            int length = word.length();
            lengthMap.put(length, lengthMap.getOrDefault(length, 0) + 1);
            for(int i = 0 ; i < length; i++){
                int idx = word.charAt(i) - 'a';
                if(root.nodes[idx] == null) root.nodes[idx] = new Trie();
                root = root.nodes[idx];
                root.lengthMap.put(length, root.lengthMap.getOrDefault(length, 0) + 1);
            }
        }

        /*
        와일드카드가 나타나면 해당 길이의 문자열 개수를 반환한다
         */
        public int search(String word, int i){
            if(word.charAt(i) == '?') return lengthMap.getOrDefault(word.length(), 0);
            int idx = word.charAt(i) - 'a';
            if(nodes[idx] == null) return 0;
            else return nodes[idx].search(word, i + 1);
        }
    }

    static String reverse(String str){
        return new StringBuilder(str).reverse().toString();
    }

    public static int[] solution(String[] words, String[] queries) {
        int[] result = new int[queries.length];
        Trie frontTrie = new Trie();
        Trie backTrie = new Trie();
        for(String word : words){
            frontTrie.insert(word);
            backTrie.insert(reverse(word));
        }

        for(int i = 0 ; i < queries.length; i++){
            String query = queries[i];

            //와일드 카드가 앞이라면 뒤에서부터 검색
            if(query.charAt(0) == '?') result[i] = backTrie.search(reverse(query), 0);
            else result[i] = frontTrie.search(query, 0);
        }

        return result;
    }

}
