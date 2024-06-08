package saturday.sat240525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 동전의 종류가 주어질 때, 주어진 금액을 만드는 모든 방법의 수 구하기
 * DP, Knapsack
 *
 * 1. 각 동전 순서대로 만들 수 있는 경우의 수 구하기 ex) 5원짜리로는 5, 10, 15, ... 5의 배수 만들 수 있음
 * 2. 어떤 금액을 이전에 구한 금액 + 동전의 금액으로 만들 수 있는 지 확인 ex) 5원, 7원이 주어질 때, 17 = 10 + 7로 가능
 * dp[i] += d[i - coin] ex) dp[17] += dp[17 - 7] ~ 17원은 10원을 만드는 경우의 수만큼 만들 수 있음
 */
public class BJ_3067_Coins {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for(int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine()); // 동전의 가지 수

            int[] coins = new int[N];
            String[] inputs = br.readLine().split(" "); // 각 동전의 금액 ... 오름차순 ~!
            for(int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(inputs[i]);
            }

            int target = Integer.parseInt(br.readLine()); // 목표 금액

            int[] dp = new int[target + 1];
            dp[0] = 1; // 자기자신을 넣는 경우를 위해 dp[0] 초기화

            for(int coin : coins) { // 각 동전으로 만들 수 있는 경우의 수 구하기 ~ 순서 반대로 하면 중복되는 결과 나와서 틀림 ... ex) 3 = 111, 12, 21
                for(int i = 1; i <= target; i++) {
                    if(i - coin >= 0) { // 배열의 범위를 벗어나지 않는 경우에만 ...
                        dp[i] += dp[i - coin];
                    }
                }
            }

            sb.append(dp[target] + "\n");
        }

        System.out.println(sb);
    }
}
