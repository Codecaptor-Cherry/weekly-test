class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int s = 1, e = 300_001;
        
        while (s < e) {
            int m = (s + e) / 2;
            if (calculateTotalTime(diffs, times, m) > limit) s = m + 1;  
            else e = m;
        }

        return s;
    }
    
    
    private long calculateTotalTime(int[] diffs, int[] times, int level) {
        long totalTime = 0;
        long prevTime = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                totalTime += times[i];
            } else {
                long extraTries = diffs[i] - level;
                totalTime += extraTries * (times[i] + prevTime) + times[i];
            }
            prevTime = times[i];
        }
        
        return totalTime;
    }
}
