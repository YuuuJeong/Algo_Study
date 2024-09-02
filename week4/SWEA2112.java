import java.io.*;
import java.util.*;

public class SWEA2112 {
    static int D, W, K, answer;
    static int[] temp;
    static int[][] films, copy;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("res/a/input2112.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        for(int test = 1; test <= T; test++) {
            sb.append("#").append(test).append(" ");
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            films = new int[D][W];
            for(int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++) {
                    films[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            answer = 0;
            copy = new int[D][W];
            flag = false;
            if(!passed()) {                
                for(int i = 1; i <= D; i++) {
                    temp = new int[i];
                    comb(0, 0, i);
                    if(flag) {
                        answer = i;
                        break;
                    }
                }
            }
            sb.append(answer).append("\n");
        }
        
        System.out.println(sb.toString());
        br.close();
    }
    
    public static void comb(int idx, int now, int N) {
        if(idx == N) {
            paint(films, copy);
            test(0);
            paint(copy, films);
            return;
        }
        
        for(int i = now; i < D; i++) {
            temp[idx] = i;
            comb(idx + 1, i + 1, N);
        }
    }
    
    public static void test(int idx) {
        if(idx == temp.length) {
            flag = flag || passed();
            return;
        }
        
        Arrays.fill(films[temp[idx]], 0);
        test(idx + 1);
        
        Arrays.fill(films[temp[idx]], 1);
        test(idx + 1);
    }
    
    public static boolean passed() {
        for(int j = 0; j < W; j++) {
            int maxCnt = 0;
            int cnt = 1;
            int now = -1;
            for(int i = 0; i < D; i++) {
                if(now != films[i][j]) {
                    maxCnt = Math.max(maxCnt, cnt);
                    cnt = 1;
                    now = films[i][j];
                    continue;
                }
                cnt++;
                now = films[i][j];
            }
            maxCnt = Math.max(maxCnt, cnt);
            if(maxCnt < K) return false;
        }
        return true;
    }
    
    public static void paint(int[][] from, int[][] to) {
        for(int i = 0; i < D; i++) {
            for(int j = 0; j < W; j++) {
                to[i][j] = from[i][j];
            }
        }
    }
}
