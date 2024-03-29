class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
        
        // 물에 잠긴 지역 기록
        for (int[] puddle: puddles) dp[puddle[1] - 1][puddle[0] - 1] = -1;
        
        // dp 초기화
        for (int i = 0; i < n; i++) {
            if (dp[i][0] == -1) break;
            dp[i][0] = 1;
        }
        
        for (int i = 0; i < m; i++) {
            if (dp[0][i] == -1) break;
            dp[0][i] = 1;
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i][j] == -1) continue;
                
                dp[i][j] += dp[i - 1][j] == -1 ? 0 : dp[i - 1][j];
                dp[i][j] += dp[i][j - 1] == -1 ? 0 : dp[i][j - 1];
                dp[i][j] %= 1000000007;
            }
        }
        
        return dp[n - 1][m - 1];
    }
}
