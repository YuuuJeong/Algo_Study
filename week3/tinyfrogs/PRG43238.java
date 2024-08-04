package tinyfrogs;

import java.util.*;

public class PRG43238 {

    class Solution {
        public long solution(int n, int[] times) {
            long answer = Long.MAX_VALUE;
            Arrays.sort(times);

            int length = times.length;
            long min = 1;
            long max = times[length - 1] * ((long) n / length);

            while (min <= max) {
                long mid = (min + max) / 2;
                long count = 0;

                for (int i = 0; i < length; i++) {
                    count += (mid / times[i]);
                }

                if (count < n) {
                    min = mid + 1;
                } else {
                    max = mid - 1;
                    answer = Math.min(answer, mid);
                }
            }

            return answer;
        }
    }
}
