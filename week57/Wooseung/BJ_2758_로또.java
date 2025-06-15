
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        long[][] dp = new long[11][2001];
        Arrays.fill(dp[1], 1);

        for(int i = 2; i <= 10; i++) {
            for(int j = 1; j <= 2000; j++) {
                for(int k = 1; k <= j / 2; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }

        for(int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            long ans = 0;
            for(int i = 1; i <= m; i++) {
                ans += dp[n][i];
            }
            sb.append(ans + "\n");
        }
        System.out.println(sb.toString());
    }

}
