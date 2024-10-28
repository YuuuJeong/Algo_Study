import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14725 {

    static class Node {
        String key;
        Map<String, Node> children = new TreeMap<>();

        Node(String key) {
            this.key = key;
        }
    }

    static class Trie {
        Node head = new Node(null);

        public void insert(List<String> words) {
            Node cur = this.head;
            for (String word : words) {
                if (!cur.children.containsKey(word)) {
                    cur.children.put(word, new Node(word));
                }
                cur = cur.children.get(word);
            }
        }

        public void search() {
            Node cur = this.head;
            recur(0, cur);
        }

        private void recur(int depth, Node node) {
            if (node.children.isEmpty()) {
                return;
            }
            for (Map.Entry<String, Node> entry : node.children.entrySet()) {
                sb.append("--".repeat(depth));
                sb.append(entry.getKey());
                sb.append("\n");
                recur(depth + 1, entry.getValue());
            }
        }
    }

    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            List<String> words = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                words.add(st.nextToken());
            }
            trie.insert(words);
        }
        trie.search();
        System.out.println(sb.toString());
    }
}
