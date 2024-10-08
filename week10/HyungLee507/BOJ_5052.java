
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ_5052 {

    static boolean result;

    private static class Node {
        boolean isEnd;
        Map<Character, Node> child;

        public Node() {
            child = new HashMap<>();
            this.isEnd = false;
        }

        private void insert(String word) {
            Node trieNode = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                trieNode.child.putIfAbsent(ch, new Node());
                trieNode = trieNode.child.get(ch);
                if (i == word.length() - 1) {
                    trieNode.isEnd = true;
                }
            }
        }

        private boolean contains(String word) {
            Node trieNode = this;
            for (int i = 0; i < word.length(); i++) {
                trieNode = trieNode.child.get(word.charAt(i));

                if (trieNode == null) {
                    return false;
                }
                if (trieNode.isEnd) {
                    return true;
                }
            }
            return trieNode.isEnd;
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int iterationCount = Integer.parseInt(bf.readLine());
        for (int i = 0; i < iterationCount; i++) {
            int N = Integer.parseInt(bf.readLine());
            result = true;
            Node root = new Node();
            List<String> list = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                list.add(bf.readLine());
            }
            Collections.sort(list);
            for (int j = 0; j < N; j++) {
                if (root.contains(list.get(j))) {
                    result = false;
                    break;
                } else {
                    root.insert(list.get(j));
                }
            }
            sb.append(result ? "YES" : "NO");
            if (i < iterationCount - 1) {
                sb.append('\n');
            }
        }
        System.out.println(sb);
        bf.close();
    }

}
