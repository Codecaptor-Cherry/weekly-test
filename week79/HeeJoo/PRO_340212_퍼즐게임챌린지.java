/*
 * 각 퍼즐은 난이도와 소요 시간이 정해져 있음
 * 숙련도에 따라 틀리는 횟수가 바뀜
 * diff <= level : time_cur만큼 시간 소요 후 퍼즐 해결
 * diff > lever : diff - level번 틀림
 * 틀릴 때마다 time_cur + time_prev만큼 시간 사용
 * 이전 퍼즐은 난이도 상관없이 틀리지 않음
 * diff - level번 틀린 이후에 다시 퍼즐을 풀면 time_cur만큼의 시간으로 퍼즐 해결
 * 전체 제한 시간 limit이 정해져 있을 때, 모든 퍼즐을 해결하기 위한 숙련도의 최솟값 구하기
 
 * 숙련도에 따른 각 문제의 소요시간
 */

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = (int)1e9;
        
        int length = diffs.length;
        int left = 1, mid = 0, right = 0;

        for(int diff : diffs) {
            right = Math.max(right, diff);
        }
        
        while(left < right) {
            mid = (left + right) / 2;
            
            long sum = 0;
            for(int i = 0; i < length; i++) {
                sum += getTakeTime(mid, i, diffs, times);
            }
            
            if(sum <= limit) { // 제한 시간 내 해결 가능
                right = mid;
                answer = mid;
            } else { // 제한 시간 내 해결 불가능
                left = mid + 1;
                answer = mid + 1;
            }
        }
        
//         // 위의 반복문을 탈출 할 때, left = mid + 1로 탈출하면 최종답안은 mid + 1이기에 현재 mid가 정답 조건을 만족하는지 체크
//         long sum = 0;
//         for(int i = 0; i < length; i++) {
//             sum += getTakeTime(mid, i, diffs, times);
//         }
        
//         if(sum <= limit) {
//             answer = mid;
//         } else {
//             answer = mid + 1;
//         }
        
        return answer;
    }
    
    public long getTakeTime(int level, int no, int[] diffs, int[] times) {
        // 1. level >= diff
        if(level >= diffs[no]) {
            return times[no]; // return time_cur
        }
        
        // 2. level < diff
        else {
            int cnt = diffs[no] - level;
            
            // 2-1. diff - level만큼 반복 : time_cur + time_prev            
            // 2-2. 마지막 : time_cur
            return (times[no] + times[no - 1]) * cnt + times[no];
        }
    }
}
