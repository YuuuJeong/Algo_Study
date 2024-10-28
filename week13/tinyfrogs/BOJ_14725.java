import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14725 {

    static class Node {
        Map<String, Node> children = new HashMap<>();
    }

    static Node root = new Node();

    public static void insert(String words) {
        Node cur = root;
        String[] wordList = words.split(" ");
        for (int i = 0; i < wordList.length; i++) {
            if (!cur.children.containsKey(wordList[i])) cur.children.put(wordList[i], new Node());
            cur = cur.children.get(wordList[i]);
        }
    }

    public static void print(Node root, int depth) {
        Node cur = root;
        Map<String, Node> children = cur.children;
        List<String> wordList = new ArrayList<>();
        wordList.addAll(children.keySet());
        Collections.sort(wordList);

        for (String word : wordList) {
            for (int i = 0; i < depth; i++) System.out.print("--");
            System.out.println(word);
            print(cur.children.get(word), depth + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            String words = "";
            for (int k = 0; k < K; k++) {
                words += st.nextToken();
                words += " ";
            }
            insert(words.trim());
        }
        print(root, 0);
    }
}
