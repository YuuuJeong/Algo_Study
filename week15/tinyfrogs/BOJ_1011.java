import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1011 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int diff = end - start;
            int sqrt = (int) Math.sqrt(diff);

            if(diff == sqrt * sqrt)
                System.out.println(2 * sqrt - 1);
            else if(diff <= sqrt * sqrt + sqrt)
                System.out.println(2 * sqrt);
            else
                System.out.println(2 * sqrt + 1);
        }


    }
}
