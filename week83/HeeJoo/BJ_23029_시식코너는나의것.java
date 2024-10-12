package saturday.sat241012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * N개의 시식코너
 * 최대한 많은 양의 음식 ... 가능한 남아있는 모든 음식 먹기
 * 연속으로 3개의 코너를 방문하지 않기 ~ 2번까지만 가능
 * 연속해서 시식할 경우, 2번째 코너에서는 1/2개밖에 못 먹음
 * 음식이 홀수개인 경우 2로 나눈 몫만큼 섭취 가능
 * 음식이 없는 코너가 존재함
 * 섭취 가능한 음식의 최대 개수 구하기
 *
 * DP
 * n번째 코너에서 가능한 3가지 경우
 * 1. _xo : 연속방문 x
 * 2. xoo : 연속방문 o
 * 3. __x : 방문 x
 */
public class BJ_23029_시식코너는나의것 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());

        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = stoi(br.readLine());
        }

        int[]dp = new int[N + 1];

        dp[1] = arr[0];
        if(N != 1) {
            dp[2] = Math.max(arr[1], arr[0] + arr[1] / 2);
        }

        for(int i = 3; i <= N; i++) {
            // _xo vs xoo
            dp[i] = Math.max(dp[i - 2] + arr[i - 1], dp[i - 3] + arr[i - 2] + arr[i - 1] / 2);

            // vs __x
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }

        System.out.println(dp[N]);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
