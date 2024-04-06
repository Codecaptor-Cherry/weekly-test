import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // n이 홀수라면 타일을 덮을 수 있는 경우가 없다
        if (n % 2 == 1) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[2] = 3;
        for (int i = 4; i <= n; i += 2) {
            dp[i] += dp[i - 2] * 3; // 3*2로 채우기 (ㅗ, ㅜ, =)
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += dp[j] * 2; // 3*4로 채우기
            }
        }
        System.out.println(dp[n]);
    }

}
