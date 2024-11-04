import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static int N;
    static StringBuilder sb;

    private static class Trie {

        private TreeMap<String, Trie> map;

        public Trie() {
            map = new TreeMap<>();
        }

        private void insert(String[] values, int cnt) {
            Trie temp = this;
            for (int i = 0; i < cnt; i++) {
                String value = values[i];
                if (!temp.map.containsKey(value)) {
                    temp.map.put(value, new Trie());
                }
                temp = temp.map.get(value);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        Trie root = new Trie();
        for (int i = 0; i < N; i++) {
            // 트라이 insert
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            String[] values = new String[cnt];
            for (int j = 0; j < cnt; j++) {
                values[j] = st.nextToken();
            }
            for (int j = 0; j < cnt; j++) {
                root.insert(values, cnt);
            }
        }
        sb = new StringBuilder();
        dfs(root, 0);
        System.out.println(sb);
        // 트라이 탐색하면서 출력.(근데 dfs 로 출력해야...)
    }

    private static void dfs(Trie trie, int depth) {
        for (String value : trie.map.keySet()) {
            for (int i = 0; i < depth; i++) {
                sb.append("--");
            }
            sb.append(value).append('\n');
            dfs(trie.map.get(value), depth + 1);
        }

    }

}