
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class CodeTree_MazeRunner {

    static int[][] map;
    static int N, M, K;
    static int exitX;
    static int exitY;
    static boolean flag;

    static class Person {
        int x;
        int y;
        boolean dirty;

        public Person(int x, int y) {
            this.x = x;
            this.y = y;
            dirty = false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Person person = (Person) o;
            return x == person.x && y == person.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static LinkedList<Person> persons;

    public static void main(String[] args) throws IOException {
        initData();
        int moveCount = 0;
        for (int i = 0; i < K; i++) {
            moveCount += move();
            if (persons.isEmpty()) {
                break;
            }
            List<Integer> lotData = getLotationData();
            lotation(lotData.get(0), lotData.get(1), lotData.get(2));
//            System.out.println(moveCount);
//            System.out.println(persons);
//            System.out.println("exitx = " + exitX + " exitY = " + exitY);
            clean();
        }
        System.out.println(moveCount);
        System.out.println(exitX + " " + exitY);
    }

    private static void clean() {
        flag = false;
        for (Person person : persons) {
            person.dirty = false;
        }
    }

    private static List<Integer> getLotationData() {
        List<Integer> temp = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= (N - i + 1); j++) {
                for (int k = 1; k <= (N - i + 1); k++) {
                    if (containAll(j, k, i)) {
                        temp.add(j);
                        temp.add(k);
                        temp.add(i);
                        return temp;
                    }
                }
            }
        }
        return null;
    }

    private static boolean containAll(int x, int y, int size) {
        if (x > exitX || exitY < y || exitX >= (x + size) || exitY >= (y + size)) {
            return false;
        }
//        for (int i = x; i < x + size; i++) {
//            for (int j = y; j < y + size; j++) {
//                for (Person person : persons) {
//                    int nowX = person.x;
//                    int nowY = person.y;
//                    if (nowX <= x && nowY <= y && nowX < (x + size) && nowY < (y + size)) {
//                        return true;
//                    }
//                }
//            }
//        }
        for (Person person : persons) {
            int nowX = person.x;
            int nowY = person.y;
            if (nowX >= x && nowY >= y && nowX < (x + size) && nowY < (y + size)) {
                return true;
            }
        }
        return false;
    }

    private static void lotation(int x, int y, int size) {

        int[][] tempMap = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int nextX = j + x;
                int nextY = size - i - 1 + y;
                changeLocation(x + i, y + j, nextX, nextY);
                int value = map[x + i][y + j];
                if (value > 0) {
                    tempMap[j][size - i - 1] = value - 1;
                } else {
                    tempMap[j][size - i - 1] = value;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[x + i][y + j] = tempMap[i][j];
            }
        }
    }

    private static void changeLocation(int x, int y, int nextX, int nextY) {
        if (x == exitX && y == exitY && !flag) {
            exitX = nextX;
            exitY = nextY;
            flag = true;
            return;
        }
        for (Person person : persons) {
            if (person.x == x && person.y == y && !person.dirty) {
                person.x = nextX;
                person.y = nextY;
                person.dirty = true;
            }
        }
    }

    private static void initData() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        persons = new LinkedList<>();
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            persons.add(new Person(x, y));
        }
        st = new StringTokenizer(bf.readLine());
        exitX = Integer.parseInt(st.nextToken());
        exitY = Integer.parseInt(st.nextToken());
    }

    // 1번 메서드 구현 완.
    private static int move() {
        int count = 0;
        int size = persons.size();
        for (int i = 0; i < size; i++) {
            Person person = persons.poll();
            int nowX = person.x;
            int nowY = person.y;
            if (exitX > nowX && map[nowX + 1][nowY] == 0) {
                person.x = nowX + 1;
                count++;
            } else if (exitX < nowX && map[nowX - 1][nowY] == 0) {
                person.x = nowX - 1;
                count++;
            } else if (exitY > nowY && map[nowX][nowY + 1] == 0) {
                person.y = nowY + 1;
                count++;
            } else if (exitY < nowY && map[nowX][nowY - 1] == 0) {
                person.y = nowY - 1;
                count++;
            }
            // 여기 로직이 잘못되 부러
            if (person.x != exitX || person.y != exitY) {
                persons.add(person);
            }
        }
        return count;
    }

}
