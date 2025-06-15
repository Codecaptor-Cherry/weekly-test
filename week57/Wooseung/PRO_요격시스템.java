import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        
        int missile = 0;
        for(int i = 0;i < targets.length; i++){
            if(missile <= targets[i][0]){
                missile = targets[i][1];
                answer++;
            }
        }

        
        return answer;
    }
}
