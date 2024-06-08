/*
 * 희망온도는 사실 상관없음
 * DP[i][j] : i분에 j온도를 만들기위해 필요한 최소 전력
 */

import java.util.*;

class Solution {
    final int INF = Integer.MAX_VALUE;
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = INF;
        
        int N = onboard.length;
        
        // -10 ~ 40
        int[][] dp = new int[N][52]; // 50일 때 51 if 예외처리 안하려고 범위+1
        temperature += 10;
        t1 += 10;
        t2 += 10;
        
        for(int i = 0; i < N; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][temperature] = 0; // 초기온도는 실외온도와 같음
        
        for(int i = 0; i < N - 1; i++) {
            for(int j = 0; j <= 50; j++) {
                if(dp[i][j] == INF) {
                    continue;
                }
                
                // 탑승 중인데 쾌적하지 않으면 pass
                if(onboard[i] == 1 && (j < t1 || j > t2)) {
                    continue;
                }
                
                // 에어컨 on : 증가/감소 모두 a 소모, 유지 b 소모
                // 1. 증가
                dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j] + a);
                
                // 2. 감소
                if(j - 1 >= 0) {
                    dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j] + a);
                }
                
                // 3. 유지
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + b);

              
                // 에어컨 off
                int nextTmp = j;

                // 실외온도따라 증감
                if(j < temperature) {
                    nextTmp++;
                } else if(j > temperature) {
                    nextTmp--;
                }

                dp[i+1][nextTmp] = Math.min(dp[i+1][nextTmp], dp[i][j]);
            }
        }
        
        // N분일 때 최소전력 구하기
        for(int i = 0; i <= 50; i++) {
            // 만약 마지막에 승객 탑승 상태인데, 쾌적 온도를 벗어나는 경우 pass
            if(onboard[N - 1] == 1 && (i < t1 || i > t2)) {
                continue;
            }
            
            answer = Math.min(answer, dp[N - 1][i]);
        }

        return answer;
    }
}
