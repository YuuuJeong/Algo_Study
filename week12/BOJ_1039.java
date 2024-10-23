import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1039 {
	static int[][] memo;
	static int K;
	static int M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		String num = st.nextToken();
		K = Integer.parseInt(st.nextToken());
		M = num.length();

		memo = new int[K + 1][1000001];
	
		int[] nums = new int[M];
		for (int i = 0; i < M; i++) {
			nums[i] = num.charAt(i) - '0';
		}
		System.out.println(DFS(nums, 0));
	}

	private static int DFS(int[] numArr, int depth) {
		int num = toInt(numArr);
		if (depth == K) {
			return num;
		}

		int memoValue = memo[depth][num];
		if (memoValue != 0) {
			return memoValue;
		}

		memoValue = -1;
		for (int i = 0; i < M - 1; i++) {
			for (int j = i + 1; j < M; j++) {
				if (i == 0 && numArr[j] == 0) { 
					continue; 
				}

				int tempNumber = numArr[i];
				numArr[i] = numArr[j];
				numArr[j] = tempNumber;
				
				int temp = DFS(numArr, depth + 1);
				memoValue = Math.max(memoValue, temp);
				numArr[j] = numArr[i];
				numArr[i] = tempNumber;
			}
		}

		memo[depth][num] = memoValue;
		return memo[depth][num];
	}



	private static int toInt(int[] arr) {
		int result = 0;
		for (int i = 0; i < arr.length; i++) {
			result = result * 10 + arr[i];
		}
		return result;
	}
}
