import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    static int N, M;
    static List<Integer> adjustCity;
    static int[] parent;

    private static class Road implements Comparable<Road> {
        int start;
        int end;
        int cost;

        public Road(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(bf.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            if (M == 0 && N == 0) {
                break;
            }
            parent = IntStream.range(0, M).toArray();
            PriorityQueue<Road> roads = new PriorityQueue<>();
            long answer = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                roads.add(new Road(start, end, cost));
                answer += cost;
            }
            int count = 0;
            while (count < M - 1) {
                Road poll = roads.poll();
                if (find(poll.start) != find(poll.end)) {
                    union(poll.start, poll.end);
                    count++;
                    answer -= poll.cost;
                }
            }
            sb.append(answer).append('\n');
        }
        System.out.println(sb);
        bf.close();
    }

    private static void union(int x, int y) {
        int parX = find(x);
        int parY = find(y);
        if (parX == parY) {
            return;
        }
        parent[parX] = parY;
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}