import java.io.*;
import java.util.*;

public class Main_bj_5670_휴대폰자판 {
	static class Node {
		int count = 0;
		Map<Character, Node> child = new HashMap<>();
		boolean isExist = false;
	}
	static class Trie {
		Node root = new Node();
		void add(String str) {
			Node node = root;
			node.count++;
			for(int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				node.child.putIfAbsent(c, new Node());
				node = node.child.get(c);
				node.count++;
			}
			node.isExist = true;
		}
		int search(String str) {
			Node node = root;
			int cnt = 0;
			int before = Integer.MAX_VALUE;
			for(int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				node = node.child.get(c);
				if(before != node.count) cnt++;
				before = node.count;
			}
			return cnt;
		}
	}
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			while(true) {
				int N = Integer.parseInt(br.readLine());
				Trie trie = new Trie();
				String[] arr = new String[N];
				for(int i = 0; i < N; i++) {
					arr[i] = br.readLine();
					trie.add(arr[i]);
				}
				double answer = 0.0;
				for(int i = 0; i < N; i++) {
					answer += trie.search(arr[i]);
				}
				sb.append(String.format("%.2f", answer / N)).append('\n');
			}
		} catch(Exception e) {
			System.out.print(sb.toString());
		}
	}
}
