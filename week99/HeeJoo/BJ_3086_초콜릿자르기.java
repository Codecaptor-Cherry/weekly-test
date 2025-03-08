package saturday.year25.sat250308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * N x M 크기의 초콜릿
 * 초콜릿을 쪼개서 모두 정사각형 모양으로 만들기
 * 되도록 적은 수의 정사각형이 남도록 초콜릿 초개기
 * 버리는 초콜릿이 있으면 안 됨
 * 초콜릿의 크기가 주어졌을 때, 쪼개서 나올 수 있는 정사각형 개수의 최솟값을 구하기
 */
public class BJ_3086_초콜릿자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        int N = stoi(inputs[0]);
        int M = stoi(inputs[1]);

        int[][] dp = new int[N + 1][M + 1];
        int min = Math.min(N, M);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = i * j;
            }
        }

        for(int i = 1; i <= min; i++) { // 정사각형 모양은 1개
            dp[i][i] = 1;
        }

        // -----------------------------------------------
        // a x b 크기 초콜릿을 2개로 나눈 후 쪼개기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                for (int k = 1; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[k][j] + dp[i - k][j]);
                }

                for (int k = 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[i][j - k]);
                }

            }
        }

        System.out.println(dp[N][M]);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
