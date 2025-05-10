import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int[] coins = new int[n];
            for (int i = 0; i < n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            int m = Integer.parseInt(br.readLine());

            System.out.println(solve(m, coins));
        }
    }

    private static int solve(int m, int[] coins) {
        int[] dp = new int[m + 1];
        dp[0] = 1;

        // DP
        for (int coin : coins) {
            for (int i = coin; i <= m; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[m];
    }
}
