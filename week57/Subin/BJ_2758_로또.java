import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        // dp[n][m]: n번째 자리에 m이 마지막 숫자로 올 때, 가능한 모든 경우의 수
        long [][] dp = new long[11][2001];
        for (int i = 1; i <= 2000; i++) dp[1][i] = 1;

        for (int i = 2; i <= 10; i++) {
            for (int j = 1; j <= 2000; j++) {
                for (int k = 1; k <= j / 2; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            long result = 0;
            for (int i = 1; i <= m; i++) {
                result += dp[n][i];
            }
            System.out.println(result);
        }
    }
}
