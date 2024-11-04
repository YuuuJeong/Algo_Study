import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] degree, buildTime;
    static int N;
    static List<Integer>[] adjustBuilding;

    private static class City {
        int num;
        int time;

        public City(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int testCase = 1; testCase <= T; testCase++) {
            // init
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            adjustBuilding = new ArrayList[N + 1];
            degree = new int[N + 1];
            buildTime = new int[N + 1];
            st = new StringTokenizer(bf.readLine());
            // 인접 빌딩 리스트와 건설 시간 초기화
            for (int i = 1; i <= N; i++) {
                adjustBuilding[i] = new ArrayList<>();
                buildTime[i] = Integer.parseInt(st.nextToken());
            }
            // 인접 빌딩과 차수 초기화
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(bf.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                adjustBuilding[start].add(end);
                degree[end]++;
            }

            int destination = Integer.parseInt(bf.readLine());
            int totalTime = getReachTotalTime(destination);
            sb.append(totalTime).append('\n');
        }
        System.out.println(sb);
    }

    private static int getReachTotalTime(int destination) {

        int totalTime = 0;
        Queue<City> queue = new ArrayDeque<>();
        int[] finTime = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                queue.add(new City(i, 0));
            }
        }
        while (!queue.isEmpty()) {
            City now = queue.poll();
            // 종착점에 도착시 끝남.
            if (now.num == destination) {
                finTime[destination] += buildTime[now.num];
                break;
            }
            for (int nextCity : adjustBuilding[now.num]) {
                degree[nextCity]--;
                finTime[nextCity] = Integer.max(finTime[nextCity], now.time + buildTime[now.num]);
            }
            for (int nextCity : adjustBuilding[now.num]) {
                if (degree[nextCity] == 0) {
                    queue.add(new City(nextCity, finTime[nextCity]));
                }
            }
        }
        return finTime[destination];
    }

}