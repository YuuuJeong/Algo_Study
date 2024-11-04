package week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ14725_개미굴 {
	static class TrieNode {
		TreeMap<String, TrieNode> childMap = new TreeMap<>();
		// key값에 의해 자동 정렬하기 위해 TreeMap 사용

		public void insert(String[] feeds, int idx) {
			if (idx == feeds.length)
				return;
			String feed = feeds[idx];
			childMap.putIfAbsent(feed, new TrieNode());
			childMap.get(feed).insert(feeds, idx + 1);
		}

		public void print(int depth, StringBuilder sb) {
			for (String key : childMap.keySet()) {
//				sb.append("--".repeat(depth));
				for (int i = 0; i < depth; i++) {
					sb.append("--");
				}
				sb.append(key).append("\n");
				childMap.get(key).print(depth + 1, sb);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 각 층을 따라 내려오면서 알게된 먹이 정보 개수
		TrieNode root = new TrieNode();

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken()); // 로봇 개미 한 마리가 알려준 먹이 정보 개수
			String[] feeds = new String[K];
			for (int k = 0; k < K; k++) {
				String s = st.nextToken();
				feeds[k] = s;
			}
			root.insert(feeds, 0);
		}

		root.print(0, sb);
		System.out.println(sb);
	}

}
