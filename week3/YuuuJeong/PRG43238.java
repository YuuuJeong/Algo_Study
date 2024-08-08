import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long lower = 0;
        long upper = (long)times[times.length-1] * n;

        long count;
        long answer = 0;
        while(lower <= upper){
            long middle = (lower + upper) / 2;
            count = 0 ;
            
            for(int i = 0 ; i < times.length ; i++){
                count += middle / times[i];
            }
            
            if(count >= n ){
                answer = middle;
                upper = middle-1;
            } else{
                lower = middle+1;
            }
            
        }
        
        return answer;
    }
}

