import java.io.*;
import java.util.*;

public class Main_BOJ14890 {

    static int[][] map;
    static int N, L, result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        result = 0;
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //위에서 아래로(가로)
        for (int i = 0; i < N; i++) {
            if (checkCol(i)) result++;
        }

        for (int i = 0; i < N; i++) {
            if (checkRow(i)) result++;
        }
        System.out.print(result);
    }

    static boolean checkCol(int row) {

        boolean[] checked = new boolean[N];
        for (int col = 0; col < N - 1; col++) {
            //거리 구하기 1일 경우 내리막길 -1일 경우를 나눔 오르막길 0이면 같으므로 코드가 돌아가지 않는다
            int dis = map[row][col] - map[row][col + 1];

            //차이가 1이면 경사로가 안된다
            if (dis > 1 || dis < -1) return false;
            if (dis == 1) {
                //차이가 1인 기점이 l만큼 있어야한다.
                for (int i = 1; i <= L; i++) {
                    //높이가 N 이상 이거나 이미 연결되어 있으면checked[col+i]경사로가 안되므로 false
                    if (col + i >= N || checked[col + i]) return false;
                    //높이가 1만큼 차이가 나지 않는다면 false
                    if (map[row][col] - 1 != map[row][col + i]) return false;
                    checked[col + i] = true;
                }
            } else if (dis == -1) {
                //차이가 -1인 기점이 l만큼 있어야한다.
                for (int i = 0; i < L; i++) {
                    if (col - i < 0 || checked[col - i]) return false;
                    if (map[row][col] != map[row][col - i]) return false;
                    checked[col - i] = true;
                }
            }
        }

        return true;
    }

    static boolean checkRow(int col) {
        boolean[] checked = new boolean[N];
        for (int row = 0; row < N - 1; row++) {
            //거리 구하기 1일 경우 내리막길 -1일 경우 오르막길 0이면 같으므로 코드가 돌아가지 않는다
            int dis = map[row][col] - map[row + 1][col];
            if (dis > 1 || dis < -1) return false;
            if (dis == 1) {
                //차이가 1인 기점이 l만큼 있어야한다.
                for (int i = 1; i <= L; i++) {
                    //높이가 N 이상 이거나 이미 연결되어 있으면checked[col+i]경사로가 안되므로 false
                    if (row + i >= N || checked[row + i]) return false;
                    //높이가 1만큼 차이가 나지 않는다면 false
                    if (map[row][col] - 1 != map[row + i][col]) return false;
                    checked[row + i] = true;
                }
            } else if (dis == -1) {
                //차이가 -1인 기점이 l만큼 있어야한다.
                for (int i = 0; i < L; i++) {
                    if (row - i < 0 || checked[row - i]) return false;
                    if (map[row][col] != map[row - i][col]) return false;
                    checked[row - i] = true;
                }
            }
        }
        return true;
    }

}
