import java.util.*;
import java.io.*;

public class Codetree_maze {
    static int N, M, K, result, remainPerson;
    static int[][] map;
    static int[][] personMap;
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    static class Point {
        int x;
        int y;
        int dist;
        boolean exit;


        public Point(int x, int y){
            this.x = x;
            this.y = y;
            dist = 0;
            exit = false;
        }
    }

    static Point[] points;
    static Point exitPoint;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        inputInit();

        for(int i = 0 ; i < K; i++){

            if(remainPerson == 0) break;
            movePlayer();
            if(remainPerson == 0) break;
            rotate();
        }


        sb.append(result).append("\n").append(exitPoint.x+1).append(" ").append(exitPoint.y+1);
        System.out.println(sb);
    }

    static void inputInit() throws Exception {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        remainPerson = M;
        map = new int[N][N];
        personMap = new int[N][N];
        points = new Point[M];

        //맵 등록
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        //사람 등록
        for(int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            points[i] = new Point(x, y);
            personMap[x][y] += 1;
        }

        st = new StringTokenizer(br.readLine(), " ");
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        exitPoint = new Point(x, y);

    }

    static void movePlayer(){
        System.out.println("movePlayer");
        for(int i = 0 ; i < M; i++){
            //탈출했는지 확인
            if(points[i].exit) continue;

            int curDis = getDis(exitPoint.x, exitPoint.y, points[i].x, points[i].y);
            int min = Integer.MAX_VALUE;
            int direction = -1;

            for(int d = 0 ; d < 4; d++){
                //거리 확인
                int nx = points[i].x + dir[d][0];
                int ny = points[i].y + dir[d][1];

                //범위 밖 -> 이동 x
                if(!isInGrid(nx, ny)) continue;

                //거리가 큼  -> 이동 x
                int futureDis = getDis(exitPoint.x, exitPoint.y, nx, ny);
                if(futureDis > curDis) continue;

                //벽이 있는지 확인
                if(map[nx][ny] > 0) continue;

                //상하좌우 우선이므로 등호는 뺀다
                if(min > futureDis) {
                    min = futureDis;
                    direction = d;
                }
            }

            //움직일 수 없으면 다음 사람으로
            if(direction == -1) continue;

            //움직일 수 있으면 움직이기 person 위치 배열에 이동
            personMap[points[i].x][points[i].y] -= 1;
            points[i].x += dir[direction][0];
            points[i].y += dir[direction][1];
            personMap[points[i].x][points[i].y] += 1;
            result++;

            //만약 탈출위치랑 같으면 exit = true
            if(points[i].x == exitPoint.x && points[i].y == exitPoint.y) {
                personMap[points[i].x][points[i].y] -= 1;
                points[i].exit = true;
                remainPerson--;
            }
        }
    }


    static void rotate(){
        System.out.println("rotate");
        int len = 0;
        int x = 0, y = 0;
        boolean check = false;
        for(int k = 2; k <= N; k++){
            for(int i = 0 ; i <= N - k; i++){
                for(int j = 0 ; j < N - k; j++){
                    check = findArea(i, j, k);
                    if(check) {
                        len = k;
                        x = i;
                        y = j;
                        check = true;
                        break;
                    }
                }
                if(check) break;
            }
            if(check) break;
        }

        //회전시킨다
        rotateArea(x, y, len);
    }

    static boolean findArea(int i, int j, int length){
        System.out.println("findArea");
        boolean hasPeople = false, hasExit = false;

        for(int x = i; x < i + length; x++){
            for(int y = j; j < j + length; y++){
                System.out.println("Hello");
                //사람 있는지 확인
                if(!hasPeople && personMap[x][y] > 0) hasPeople = true;

                //탈출구 있는지 확인
                if(!hasExit && exitPoint.x == x && exitPoint.y == y)  hasExit = true;

                //둘다 있으면 true 반환
                if(hasPeople && hasExit) return true;
            }
        }

        return false;
    }

    static void rotateArea(int i, int j, int length){
        System.out.println("rotateArea");
        int[][] copyMap = new int[N][N];
        int[][] copyPersonMap = new int[N][N];
        int nx = 0, ny = 0;
        for(int x = 0; x < length; x++){
            for(int y = 0; y < length; y++){
                copyMap[y][length - 1 - x] = map[x + i][y + j];
                copyPersonMap[y][length - 1 - x] = personMap[x + i][y + j];

                if(exitPoint.x == i + x && exitPoint.y == j + y){
                    nx = j + y;
                    ny = length - 1 - x + i;
                }
            }
        }

        exitPoint.x = nx;
        exitPoint.y = ny;
        for (int x = i; x < i + length; x++) {
            for (int y = j; y < j + length; y++) {
                map[x][y] = copyMap[x - i][y - j];
                if (map[x][y] > 0)
                    map[x][y]--;

                personMap[x][y] = copyPersonMap[x - i][y - j];
                for (int k = 0; k < M; ++k) {
                    if(personMap[x][y] == k){
                        points[k].x = x;
                        points[k].y = y;
                    }
                }
            }
        }

    }

    static boolean isInGrid(int x, int y){
        if(x >= 0 && x < N && y >= 0 && y < N) return true;
        return false;
    }

    static int getDis(int ex, int ey, int px, int py){
        return Math.abs(ex - px) + Math.abs(ey - py);
    }
}