import java.io.*;
import java.util.*;

public class Main_bj_14725_개미굴 {
	static class Node {
		TreeMap<String, Node> child = new TreeMap<>();
		boolean end;
	}
	static class Trie {
		Node root = new Node();
		void insert(String[] arr) {
			Node node = this.root;
			for(int i = 0; i < arr.length; i++) {
				node.child.putIfAbsent(arr[i], new Node());
				node = node.child.get(arr[i]);
			}
			node.end = true;
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			search(0, root, sb);
			return sb.toString();
		}
		void search(int depth, Node node, StringBuilder sb) {
			if(node.end == true) return;
			for(String key : node.child.keySet()) {
				for(int i = 0; i < depth; i++) {
					sb.append("--");
				}
				sb.append(key).append("\n");
				search(depth + 1, node.child.get(key), sb);
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr;
		Trie trie = new Trie();
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			arr = new String[k];
			for(int j = 0; j < k; j++) {
				arr[j] = st.nextToken();
			}
			trie.insert(arr);
		}
		System.out.print(trie);
		br.close();
    }
}
