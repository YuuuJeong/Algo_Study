import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2357 {

    static void initMin(int node, int start, int end) {
        if (start == end) {
            minTree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            initMin(2 * node, start, mid);
            initMin(2 * node + 1, mid + 1, end);
            minTree[node] = Math.min(minTree[2 * node], minTree[2 * node + 1]);
        }
    }

    static void initMax(int node, int start, int end) {
        if (start == end) {
            maxTree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            initMax(2 * node, start, mid);
            initMax(2 * node + 1, mid + 1, end);
            maxTree[node] = Math.max(maxTree[2 * node], maxTree[2 * node + 1]);
        }

    }

    static void getMin(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return;

        if (left <= start && right >= end) {
            min = Math.min(min, minTree[node]);
            return;
        }

        int mid = (start + end) / 2;
        getMin(2 * node, start, mid, left, right);
        getMin(2 * node + 1, mid + 1, end, left, right);
    }

    static void getMax(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return;

        if (left <= start && right >= end) {
            max = Math.max(max, maxTree[node]);
            return;
        }

        int mid = (start + end) / 2;
        getMax(2 * node, start, mid, left, right);
        getMax(2 * node + 1, mid + 1, end, left, right);
    }

    static int N, M, min, max, arr[], minTree[], maxTree[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        minTree = new int[4 * N];
        maxTree = new int[4 * N];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initMax(1, 1, N);
        initMin(1, 1, N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            int sIndex = Integer.parseInt(st.nextToken());
            int eIndex = Integer.parseInt(st.nextToken());
            getMin(1, 1, N, sIndex, eIndex);
            getMax(1, 1, N, sIndex, eIndex);
            System.out.println(min + " " + max);
        }


    }
}