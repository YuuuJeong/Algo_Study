import java.io.*;
import java.util.*;

public class BOJ_14890 {

    static int N, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        int answer = 0;

        int[][] grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int i = 0; i < N; i++) {
            int[] forward = grid[i];
            answer += isRoad(forward);
        }


        for (int j = 0; j < N; j++) {
            int[] forward = new int[N];
            for (int i = 0; i < N; i++) {
                forward[i] = grid[i][j];
            }
            answer += isRoad(forward);
        }
        
        System.out.println(answer);
        br.close();
    }


    static int isRoad(int[] heights) {
        int curHeight = heights[0];
        int len = 1;
        boolean[] isInstall = new boolean[N]; 

        for (int i = 1; i < N; i++) {
        	//높이가 같다면 길이를 더해나간다.
            if (heights[i] == curHeight) { 
                len++;
            } 
            
            //높이차가 1인 경우 (올라가는 모양)
            else if (heights[i] - curHeight == 1) { 
                if (len >= L) {
                    for (int j = i - L; j < i; j++) {
                        if (isInstall[j]) return 0; 
                    }
                    curHeight = heights[i];
                    len = 1;
                } else {
                    return 0;
                }
            } 
            
            //높이차이가 1인 경우(내려가는 모양)
            else if (curHeight - heights[i] == 1) { 
                if (i + (L - 1) < N) {
                    for (int j = i + 1; j < i + (L); j++) {
                        if (heights[i] != heights[j] || isInstall[j]) {
                            return 0;
                        }
                    }
              
                    Arrays.fill(isInstall, i + 1, i + L, true);
                    if (L == 1) isInstall[i] = true;


                    curHeight = heights[i];
                    len = 1;
                    i += (L - 1);
                } else {
                    return 0;
                }
            } 
            
            //높이차이가 2 이상
            else {
                return 0;
            }
            
        }
        //끝까지 다 돌면 설치가능
        return 1;
    }
}
