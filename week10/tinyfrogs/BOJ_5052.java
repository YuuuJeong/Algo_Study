package tinyfrogs;

import java.io.*;

public class BOJ_5052 {

    static class Trie {

        Trie[] nodes = new Trie[26];
        boolean isEnd;
        //0~9까지 이므로 배열의 크기는 10까지

        public void insert(String number) {
            Trie root = this;
            for (int i = 0; i < number.length(); i++) {
                int num = Integer.parseInt(number.charAt(i) + "");
                if (root.nodes[num] == null) root.nodes[num] = new Trie();
                root = root.nodes[num];
            }
            root.isEnd = true;
        }

        public boolean search(String number) {
            Trie root = this;
            for (int i = 0; i < number.length(); i++) {
                int num = Integer.parseInt(number.charAt(i) + "");
                root = root.nodes[num];
                if (root.isEnd && i != number.length() - 1) return true;
            }
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            Trie trie = new Trie();
            int N = Integer.parseInt(br.readLine());

            String[] num = new String[N];
            for (int i = 0; i < N; i++) {
                num[i] = br.readLine();
                trie.insert(num[i]);
            }

            //문자 하나씩 더 해가며 비교?
            //이때 중간에 맞는 번호가 존재하면 바로 NO출력
            boolean check = true;
            for (int i = 0; i < N; i++) {
                if (trie.search(num[i])) {
                    check = false;
                    break;
                }
            }

            if (!check) {
                System.out.println("NO");
                continue;
            }
            System.out.println("YES");
        }

        br.close();
    }
}
