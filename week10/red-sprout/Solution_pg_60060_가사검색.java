import java.util.*;

class Solution {
    class Node {
        Map<Integer, Integer> count = new HashMap<>();
        Map<Character, Node> children = new HashMap<>();
        boolean isEnd = true;
    }
    
    class Trie {
        Node rootPre = new Node();
        Node rootPost = new Node();
        
        void add(String str) {
            addPre(rootPre, 0, str);
            addPost(rootPost, str.length() - 1, str);
        }
        
        void addPre(Node node, int idx, String str) {
            if(idx == str.length()) {
                node.isEnd = true;
                return;
            }
            char c = str.charAt(idx);
            if(!node.children.containsKey(c)) node.children.put(c, new Node());
            node.count.put(str.length() - idx, node.count.getOrDefault(str.length() - idx, 0) + 1);
            addPre(node.children.get(c), idx + 1, str);
        }
        
        void addPost(Node node, int idx, String str) {
            if(idx < 0) {
                node.isEnd = true;
                return;
            }
            char c = str.charAt(idx);
            if(!node.children.containsKey(c)) node.children.put(c, new Node());
            node.count.put(idx, node.count.getOrDefault(idx, 0) + 1);
            addPost(node.children.get(c), idx - 1, str);
        }
        
        int count(String str) {
            if(str.charAt(0) != '?') {
                return countPre(rootPre, 0, str);
            } else {
                return countPost(rootPost, str.length() - 1, str);
            }
        }
        
        int countPre(Node node, int idx, String str) {
            char now = str.charAt(idx);
            if(now == '?') return node.count.getOrDefault(str.length() - idx, 0);
            if(!node.children.containsKey(now)) return 0;
            return countPre(node.children.get(now), idx + 1, str);
        }
        
        int countPost(Node node, int idx, String str) {
            char now = str.charAt(idx);
            if(now == '?') return node.count.getOrDefault(idx, 0);
            if(!node.children.containsKey(now)) return 0;
            return countPost(node.children.get(now), idx - 1, str);
        }
    }
    
    public int[] solution(String[] words, String[] queries) {
        Trie trie = new Trie();
        int[] answer = new int[queries.length];
        for(String s : words) {
            trie.add(s);
        }
        for(int i = 0; i < queries.length; i++) {
            answer[i] = trie.count(queries[i]);
        }
        return answer;
    }
}
