import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {


    static class City implements Comparable<City> {
        int index;
        int cost;

        public City(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(City o) {
            return Integer.compare(o.cost, this.cost);
        }
    }

    static int[] distance;
    static int[] degree;
    static boolean[] visited;
    static List<City>[] forwardAdjustCities;
    static List<City>[] backwardAdjustCities;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int cityCount = Integer.parseInt(bf.readLine());
        distance = new int[cityCount + 1];
        visited = new boolean[cityCount + 1];
        degree = new int[cityCount + 1];
        forwardAdjustCities = new List[cityCount + 1];
        backwardAdjustCities = new List[cityCount + 1];
        for (int i = 0; i <= cityCount; i++) {
            forwardAdjustCities[i] = new ArrayList<>();
            backwardAdjustCities[i] = new ArrayList<>();
        }
        int roads = Integer.parseInt(bf.readLine());

        for (int i = 0; i < roads; i++) {
            st = new StringTokenizer(bf.readLine());
            int city1 = Integer.parseInt(st.nextToken());
            int city2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            forwardAdjustCities[city1].add(new City(city2, cost));
            backwardAdjustCities[city2].add(new City(city1, cost));
            degree[city2]++;
        }
        st = new StringTokenizer(bf.readLine());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());
        int longestDistance = findLongestDistance(startCity, endCity);
        int runningRoads = getRoadCount(endCity);
        StringBuilder sb = new StringBuilder();
        sb.append(longestDistance).append("\n").append(runningRoads);
        System.out.println(sb);

    }

    private static int getRoadCount(int endCity) {
        int roadCount = 0;
        Arrays.fill(visited, false);
        Queue<City> queue = new ArrayDeque<>();
        queue.add(new City(endCity, distance[endCity]));
        visited[endCity] = true;
        while (!queue.isEmpty()) {
            City currentCity = queue.poll();
            for (City nextCity : backwardAdjustCities[currentCity.index]) {
                degree[nextCity.index]--;
                if (distance[nextCity.index] + nextCity.cost == distance[currentCity.index]) {
                    roadCount++;
                    if (!visited[nextCity.index]) {
                        visited[nextCity.index] = true;
                        queue.add(nextCity);
                    }
                }

            }
        }
        return roadCount;
    }

    private static int findLongestDistance(int startCity, int endCity) {

        Queue<City> queue = new ArrayDeque<>();
        queue.add(new City(startCity, 0));
        while (!queue.isEmpty()) {
            City currentCity = queue.poll();
            for (City nextCity : forwardAdjustCities[currentCity.index]) {
                degree[nextCity.index]--;
                distance[nextCity.index] = Integer.max(distance[nextCity.index],
                        distance[currentCity.index] + nextCity.cost);
                if (degree[nextCity.index] == 0) {
                    queue.add(new City(nextCity.index, distance[nextCity.index]));
                }

            }
        }
        return distance[endCity];
    }
}