import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5052 {

    static class Node {
        String key;
        boolean isEnd = false;
        Node[] children = new Node[10];

        public Node(String key) {
            this.key = key;
        }
    }

    static class Trie {
        Node head = new Node(null);

        boolean insert(String word) {
            boolean result = false, fix = false;
            Node cur = head;

            for(char ch : word.toCharArray()) {
                if(cur.children[ch - '0'] == null) {
                    cur.children[ch - '0'] = new Node(String.valueOf(ch));
                    if(!fix) {
                        result = true;
                    }
                } else if (cur.children[ch - '0'].isEnd) {
                    result = false;
                    fix = true;
                }
                cur = cur.children[ch - '0'];
            }

            cur.isEnd = true;
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= TC; tc++) {
            String result = "YES";
            Trie trie = new Trie();

            int N = Integer.parseInt(br.readLine());

            for(int i = 0; i < N; i++) {
                String key = br.readLine();
                if(!trie.insert(key)) {
                    result = "NO";
                }
            }
            System.out.println(result);
        }
    }

}
