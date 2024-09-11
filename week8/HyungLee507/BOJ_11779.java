import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class City implements Comparable<City> {
        int cityIndex;
        int cost;

        public City(int cityIndex, int cost) {
            this.cityIndex = cityIndex;
            this.cost = cost;
        }

        @Override
        public int compareTo(City o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int n, m;
    static List<City>[] adjustCity;
    static int[] cost;
    static int[] prev;
    static ArrayDeque<Integer> channel;


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(bf.readLine());
        m = Integer.parseInt(bf.readLine());
        adjustCity = new ArrayList[n + 1];
        cost = new int[n + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            adjustCity[i] = new ArrayList<>();
        }
        channel = new ArrayDeque<>();
        prev = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjustCity[start].add(new City(end, cost));
        }
        st = new StringTokenizer(bf.readLine());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());
        search(startCity);
        StringBuilder sb = new StringBuilder();
        sb.append(cost[endCity]).append('\n');
        getStack(startCity, endCity);
        sb.append(channel.size()).append('\n');
        while (!channel.isEmpty()) {
            sb.append(channel.poll()).append(' ');
        }
        System.out.println(sb);
    }

    private static void getStack(int startCity, int endCity) {
        int temp = endCity;
        while (temp != startCity) {
            channel.offerFirst(temp);
            temp = prev[temp];
        }
        channel.offerFirst(startCity);
    }

    private static void search(int startCity) {
        PriorityQueue<City> queue = new PriorityQueue<>();
        queue.add(new City(startCity, 0));
        cost[startCity] = 0;

        while (!queue.isEmpty()) {
            City now = queue.poll();
            int currentIndex = now.cityIndex;

            if (now.cost > cost[currentIndex]) {
                continue;
            }

            for (City next : adjustCity[currentIndex]) {
                int newCost = cost[currentIndex] + next.cost;

                if (newCost < cost[next.cityIndex]) {
                    cost[next.cityIndex] = newCost;
                    prev[next.cityIndex] = currentIndex; // 이전 도시를 기록
                    queue.add(new City(next.cityIndex, newCost));
                }
            }
        }
    }

}