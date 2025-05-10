import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] food = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            food[i] = Integer.parseInt(br.readLine());
        }

        long[][] dp = new long[n + 1][3];

        dp[1][0] = food[1];
        if (n > 1) { // ArrayIndexOutOfBounds
            dp[2][0] = food[2];
            dp[2][1] = food[1] + food[2] / 2;
            dp[2][2] = food[1]; // 이전 라운드 최댓값
        }

        for (int i = 3; i <= n; i++) {
            dp[i][0] = dp[i - 1][2] + food[i]; // 첫번째 방문
            dp[i][1] = dp[i - 1][0] + food[i] / 2; // 두번째 방문
            dp[i][2] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2])); // 세번째 방문 (방문 못함)
        }

        System.out.println(Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2])));
    }

}
