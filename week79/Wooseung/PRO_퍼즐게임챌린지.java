import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int left = 1;
        int right = 100000;
        
        while(left <= right) {
            
            int mid = (left + right) / 2;
            long sum = 0;
            
            for(int i = 0; i < diffs.length; i++) {
                int diff = diffs[i] - mid;
                
                if(diff <= 0) {
                    sum += times[i];
                }else {
                    if(i == 0) {
                        sum += (long) times[i] * diff + times[i];
                    }else {
                        sum += (long) (times[i] + times[i - 1]) * diff + times[i];
                    }
                }
            }
            if(sum > limit) left = mid + 1;
            else {
                right = mid - 1;
                answer = mid;
            }
        }
        
        return answer;
    }
}
