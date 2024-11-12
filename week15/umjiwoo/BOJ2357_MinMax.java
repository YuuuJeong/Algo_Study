import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2357_MinMax {
	static int[] minTree, maxTree, arr;
	static int n, m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 배열의 크기
		m = Integer.parseInt(st.nextToken()); // 쿼리 개수
		arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int size = 4 * n;
		minTree = new int[size];
		maxTree = new int[size];

		// 세그먼트 트리 초기화
		buildMinTree(0, n - 1, 1);
		buildMaxTree(0, n - 1, 1);

		// 쿼리 처리
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken()) - 1;
			int right = Integer.parseInt(st.nextToken()) - 1;
			int minResult = queryMin(0, n - 1, 1, left, right);
			int maxResult = queryMax(0, n - 1, 1, left, right);
			sb.append(minResult).append(" ").append(maxResult).append("\n");
		}

		System.out.print(sb);
	}

	// 최소값 세그먼트 트리 생성
	static int buildMinTree(int start, int end, int node) {
		if (start == end)
			return minTree[node] = arr[start];
		int mid = (start + end) / 2;
		return minTree[node] = Math.min(buildMinTree(start, mid, node * 2), buildMinTree(mid + 1, end, node * 2 + 1));
	}

	// 최대값 세그먼트 트리 생성
	static int buildMaxTree(int start, int end, int node) {
		if (start == end)
			return maxTree[node] = arr[start];
		int mid = (start + end) / 2;
		return maxTree[node] = Math.max(buildMaxTree(start, mid, node * 2), buildMaxTree(mid + 1, end, node * 2 + 1));
	}

	// 최소값 쿼리
	static int queryMin(int start, int end, int node, int left, int right) {
		if (right < start || end < left)
			return Integer.MAX_VALUE;
		if (left <= start && end <= right)
			return minTree[node];
		int mid = (start + end) / 2;
		return Math.min(queryMin(start, mid, node * 2, left, right), queryMin(mid + 1, end, node * 2 + 1, left, right));
	}

	// 최대값 쿼리
	static int queryMax(int start, int end, int node, int left, int right) {
		if (right < start || end < left)
			return Integer.MIN_VALUE;
		if (left <= start && end <= right)
			return maxTree[node];
		int mid = (start + end) / 2;
		return Math.max(queryMax(start, mid, node * 2, left, right), queryMax(mid + 1, end, node * 2 + 1, left, right));
	}
}
