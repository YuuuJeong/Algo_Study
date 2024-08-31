package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            if (canPlaceSlope(map[i], l)) result++;  // 행 체크
            if (canPlaceSlope(getColumn(map, i), l)) result++;  // 열 체크
        }

        System.out.println(result);
    }

    // 한 행이나 열에 대해 경사로를 놓을 수 있는지 확인
    private static boolean canPlaceSlope(int[] line, int l) {
        int n = line.length;
        boolean[] used = new boolean[n];

        for (int i = 0; i < n - 1; i++) {
            if (line[i] == line[i + 1]) continue;  // 높이가 같다면 넘어감

            if (line[i] + 1 == line[i + 1]) {  // 오르막 경사로
                for (int j = 0; j < l; j++) {
                    if (i - j < 0 || line[i] != line[i - j] || used[i - j]) return false;
                    used[i - j] = true;
                }
            } else if (line[i] - 1 == line[i + 1]) {  // 내리막 경사로
                for (int j = 1; j <= l; j++) {
                    if (i + j >= n || line[i + 1] != line[i + j] || used[i + j]) return false;
                    used[i + j] = true;
                }
            } else {  // 높이 차이가 1보다 크면 경사로 설치 불가
                return false;
            }
        }

        return true;
    }

    // 주어진 맵에서 열을 추출하는 함수
    private static int[] getColumn(int[][] map, int index) {
        int n = map.length;
        int[] column = new int[n];
        for (int i = 0; i < n; i++) {
            column[i] = map[i][index];
        }
        return column;
    }
}
