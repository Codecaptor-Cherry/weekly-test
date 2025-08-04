
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        if (N == 1) {
            System.out.println(arr[1]);
            return;
        }

        int[][] dp = new int[N + 1][3];
        dp[2][0] = arr[2];
        dp[2][1] = arr[1] + arr[2] / 2;
        dp[2][2] = arr[1];

        for(int i = 3; i <= N; i++) {
            dp[i][0] = dp[i - 1][2] + arr[i];
            dp[i][1] = dp[i - 1][0] + arr[i] / 2;
            dp[i][2] = Math.max(dp[i - 1][2], Math.max(dp[i - 1][1], dp[i - 1][0]));
        }
        System.out.println(Math.max(dp[N][0], Math.max(dp[N][1], dp[N][2])));
    }

}
