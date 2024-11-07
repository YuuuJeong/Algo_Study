
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
 
public class BOJ_전기요금 {
 
    public static void main(String[] args) throws Exception{
    	StringTokenizer st;
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {
        	st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if(a == 0 && b == 0) {
            	System.out.println(sb);
            	break;
            }
            
            int total = calAmount(a);
           
            int left = 0;
            int right = total / 2;

            while(left <= right) {
                int mid = (left + right) / 2;
 
                int s_price = calPrice(mid); 
                int n_price = calPrice(total - mid); 
 
                int diff = Math.abs(s_price - n_price); 
                if(diff < b) {
                    right = mid - 1;
                } else if(diff > b){
                    left = mid + 1;
                } else {
                	sb.append(calPrice(mid) + "\n");
                    break;
                }
            }
        }
    }
 
    public static int calAmount(int price) {
        if(price <= 200) {
            return price / 2;
        } else if(price <= 29900) {
            return (price - 200) / 3 + 100;
        } else if(price <= 4979900) {
            return (price - 29900) / 5 + 10000;
        } else {
            return (price - 4979900) / 7 + 1000000;
        }
    }
 
    public static int calPrice(int watt) {
        if(watt <= 100) {
            return watt * 2;
        } else if(watt <= 10000) {
            return 200 + (watt - 100) * 3;
        } else if(watt <= 1000000) {
            return 200 + 29700 + (watt - 10000) * 5;
        } else {
            return 200 + 29700 + 4950000 + (watt - 1000000) * 7;
        }
    }
}


