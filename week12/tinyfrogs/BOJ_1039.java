import java.io.*;
import java.util.*;

public class BOJ_1039 {

    static String N;
    static int K, result, visited[] = new int[1000001];
    static ArrayDeque<String> deque = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = st.nextToken();
        K = Integer.parseInt(st.nextToken());
        result = 0;

        //첫번쨰 숫자 que에 넣기
        deque.offer(N);

        //bfs 실행
        bfs();

        //교환이 불가능 -> -1
        if(deque.isEmpty()) System.out.println(-1);
            //교환이 가능 -> 최댓값 구하기
        else{
            for(String s : deque) result = Math.max(result, Integer.parseInt(s));
            System.out.println(result);
        }

    }

    private static void bfs() {

        //교환이 불가능하거나 횟수를 다 사용했을 경우
        while (!deque.isEmpty() && K > 0) {
            int size = deque.size();
            for (int k = 0; k < size; k++) {
                String s = deque.poll();

                //가능한 교환 다 실행
                for (int i = 0; i < s.length() - 1; i++) {
                    for (int j = i + 1; j < s.length(); j++) {
                        swap(s, i, j);
                    }
                }
            }
            K--;
        }
    }

    private static void swap(String s, int i, int j) {
        char[] cArr = s.toCharArray();
        char temp = cArr[i];
        cArr[i] = cArr[j];
        cArr[j] = temp;
        int num = Integer.parseInt(String.valueOf(cArr));
        if (cArr[0] != '0' && visited[num] != K) {
            deque.offer(String.valueOf(num));
            visited[num] = K;
        }
    }
}