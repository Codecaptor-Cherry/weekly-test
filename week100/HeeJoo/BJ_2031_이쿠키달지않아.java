package saturday.year25.sat250315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * T분동안 먹을 N종류의 음식
 * 각각의 음식을 먹을 시각을 분 단위로 표시한 길이 N의 목록
 * 차 한 잔을 마시면 D분동안 다이어트 효과 유지 ex) D = 5일때, 3분에 차를 마시면 7분까지 유지
 * 총 K잔의 차를 적당한 타이밍에 마셔서 효과가 유지되는 동안 음식을 먹는 횟수 최대로 .....
 *
 * 1. 배열 오름차순 정렬
 * 2. 각 시간마다 최대 횟수 구하기 -> 이분탐색
 * 3. DP
 */

public class BJ_2031_이쿠키달지않아 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = stoi(st.nextToken()); // T 분
        int N = stoi(st.nextToken()); // N 종류
        int D = stoi(st.nextToken()); // D 효과
        int K = stoi(st.nextToken()); // K 잔
        K = K > N ? N : K;

        int[] foods = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < foods.length; i++) {
            foods[i] = stoi(st.nextToken());
        }

        // -------------------------------------------------------
        Arrays.sort(foods);

        int[] eats = new int[N]; // eats[i] : i번째 음식에서 효과가 끝날 때 먹을 수 있는 음식의 개수
        // 3 7 9 9 12 14 15
        // 1 2 2 3 3  2  3
        for (int i = 0; i < N; i++) {
            int left = 0, right = i + 1; // 0 ~ i번 음식 고려
            int t = foods[right - 1] - D + 1; // 차를 마시는 시간 ~ foods[i]에 효과가 끝나야 함 -> foods[i] - D + 1에 차를 마심
            while(left < right) {
                int mid = (left + right) / 2;
                if(foods[mid] >= t) { // foods[mid]를 D분 내 섭취 불가능한 경우
                    right = mid;
                } else { // // foods[mid]를 D분 내 섭취 가능한 경우
                    left = mid + 1;
                }
            }

            eats[i] = i - left + 1; // foods[i] - D + 1부터 foods[i]까지 섭취 가능
        }

         int[][] dp = new int[N + 1][K + 1]; // dp[i][j] : j번째 차를 마셨을 때, 1번부터 i번까지 먹을 수 있는 음식의 최대 개수
        for(int k = 1; k <= K; k++) {
            for(int n = 1; n <= N; n++) {
                int cnt = eats[n - 1];
                // 1. i번 음식을 먹지 않는 경우
                // 2. i번 음식을 먹는 경우
                dp[n][k] = Math.max(dp[n-1][k], dp[n-cnt][k-1] + cnt); // n - cnt를 이용하여 겹치지않는 음식의 idx 구하기
                // eats[2] = 2 ~ 2번째와 1번째를 섭취해서 2개 ... 0번째 음식을 먹을 수 있음 -> n - cnt
            }
        }

        System.out.println(dp[N][K]);
    }

    public static void print(int[][] dp, int n, int k) {
        for(int a = 0; a <= n; a++) {
            for(int b = 0; b <= k; b++) {
                System.out.printf("%d ", dp[a][b]);
            }
            System.out.println();
        }

        System.out.println();
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
