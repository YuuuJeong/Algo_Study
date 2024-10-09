import java.io.*;

public class Main_bj_5052_전화번호목록 {
	static class Node {
		boolean isEnd = false;
		Node[] children = new Node[10];
	}
	
	static class Trie {
		Node root = new Node();
		void add(String str) {
			add(root, 0, str);
		}
		
		void add(Node node, int idx, String str) {
			if(idx == str.length()) {
				node.isEnd = true;
				return;
			}
			int now = str.charAt(idx) - '0';
			if(node.children[now] == null) node.children[now] = new Node();
			add(node.children[now], idx + 1, str);
		}
		
		boolean search(String str) {
			return search(root, 0, str);
		}

		boolean search(Node node, int idx, String str) {
			if(idx == str.length()) return true;
			int now = str.charAt(idx) - '0';
			if(node.isEnd) return true;
			if(node.children[now] == null) return false;
			return search(node.children[now], idx + 1, str);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int test = 0; test < t; test++) {
			Trie trie = new Trie();
			int n = Integer.parseInt(br.readLine());
			boolean flag = false;
			for(int i = 0; i < n; i++) {
				String str = br.readLine();
				if(flag) continue;
				flag = trie.search(str);					
				trie.add(str);
			}
			sb.append(flag ? "NO" : "YES").append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}
}
