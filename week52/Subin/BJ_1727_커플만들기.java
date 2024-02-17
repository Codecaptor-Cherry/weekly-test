import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://velog.io/@qwerty1434/%EB%B0%B1%EC%A4%80-1727%EB%B2%88-%EC%BB%A4%ED%94%8C-%EB%A7%8C%EB%93%A4%EA%B8%B0
public class BJ_1727_커플만들기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] men = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            men[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(men);
        
        int[] women = new int[m + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            women[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(women);
        
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == j) {
                    dp[i][j] = dp[i - 1][j - 1] + getDiff(men[i], women[j]);
                } else if (i > j) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + getDiff(men[i], women[j]), dp[i - 1][j]);
                } else if (i < j) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + getDiff(men[i], women[j]), dp[i][j - 1]);
                }

            }
        }

        System.out.println(dp[n][m]);
    }

    private static int getDiff(int a, int b) {
        return Math.abs(a - b);
    }

}
