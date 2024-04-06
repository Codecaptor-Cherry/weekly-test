package saturday.sat240406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2133_타일채우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if(N % 2 == 1) { // 홀수는 만들 수 없음
            System.out.println(0);
            return;
        }

        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[2] = 3; // 3가지

        for(int i = 4; i <= N; i += 2) {
            dp[i] = dp[i - 2] * 3; // i - 2칸에 2칸 짜리 덧붙이기

            for(int j = i - 4; j >= 0; j -= 2) { // i - j칸에 새로운 모양 덧붙이기 ~ 2개씩밖에 안나옴
                dp[i] += dp[j] * 2;
            }
        }

        System.out.println(dp[N]);
    }
}
