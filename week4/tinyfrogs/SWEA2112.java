package tinyfrogs;

import java.io.*;
import java.util.*;


public class SWEA2112 {

    public class Solution {

        static int D, W, K;
        static int[][] map;
        static int[][] copyMap;
        static int resultCount, min;

        public static void main(String[] args) throws Exception {
            //System.setIn(new FileInputStream("res/input_2112.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = null;

            int tc = Integer.parseInt(br.readLine());

            for (int t = 1; t <= tc; t++) {
                st = new StringTokenizer(br.readLine(), " ");
                D = Integer.parseInt(st.nextToken());
                W = Integer.parseInt(st.nextToken());
                K = Integer.parseInt(st.nextToken());


                map = new int[D][W];
                copyMap = new int[D][W];


                for (int i = 0; i < D; i++) {
                    st = new StringTokenizer(br.readLine(), " ");
                    for (int j = 0; j < W; j++) {
                        int num = Integer.parseInt(st.nextToken());
                        map[i][j] = num;
                        copyMap[i][j] = num;
                    }
                }

                //무조건 D번하면 다 할 수 있으므로 D 설정
                min = D;
                resultCount = 0;
                //2. 전부 통과가 되는지 확인
                boolean hos = checkComb();

                if (hos) {
                    sb.append("#").append(t).append(" ").append(resultCount).append("\n");
                    continue;
                }


                //3. 위에서 말한 행동 실행 -> 하나씩 차례대로 실행(A, B) 둘다
                //3-1. 한 개의 행이 안되면 두 개의 행 세 개의 행... 으로 진행
                //3-2. 만약 그 중에 통과되는 것이 있으면 그 값 반환 후 탈출
                solve(0, 0);
                sb.append("#").append(t).append(" ").append(min).append("\n");

            }

            System.out.print(sb);

        }

        static void solve(int count, int index) {
            //D번 다 바꾸면 끝이니깐 횟수가 D보다 크면 종료
            if (count > D) {
                return;
            }

            //index가 D보다 같으면 초기화, 전부 확인이 되면 최소값 비교
            if (index == D) {
                if (checkComb()) {
                    min = Math.min(min, count);
                }
                return;
            }

            //그냥 index만 증가시켜 검사를 실행하게 유도
            solve(count, index + 1);

            //0으로 값 대입 후 행값 증가
            dosage(index, 0);
            solve(count + 1, index + 1);


            //1로 값 대입 후 행값 증가
            dosage(index, 1);
            solve(count + 1, index + 1);

            //다시 초기화 -> 다 초기화 하면 안되나?
            deepCopy(index);

        }

        static void dosage(int d, int num) {
            for (int i = 0; i < W; i++) {
                map[d][i] = num;
            }
        }

        static boolean checkComb() {
            boolean check = true;
            for (int i = 0; i < W; i++) {
                int count = 1;
                int currentNum = map[0][i];
                for (int j = 1; j < D; j++) {
                    //현재 값이 일치하면 값을 증가시킨다.
                    if (map[j][i] == currentNum) {
                        count++;
                    }
                    //현재 값과 다르다면
                    //그 후 currentNum을 바꾸고 count를 증가시킨다.
                    else {
                        currentNum = map[j][i];
                        count = 1;
                    }

                    if (count == K)
                        break;
                }

                if (count < K) {
                    return false;
                }
            }


            return check;
        }

        static void deepCopy(int d) {
            for (int i = 0; i < W; i++) {
                map[d][i] = copyMap[d][i];
            }
        }

    }

}
