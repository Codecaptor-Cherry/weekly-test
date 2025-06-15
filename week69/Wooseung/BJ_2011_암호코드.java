
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int MOD = 1000000;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String str = in.readLine();
        int[] dp = new int[str.length() + 1];

        dp[0] = 1;
        dp[1] = 1;
        if (str.charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                dp[i + 1] += dp[i];
            }
            int num = Integer.parseInt(str.substring(i - 1, i + 1));
            if (num == 0) {
                System.out.println(0);
                return;
            }
            else if(num <= 26 && num >= 10) dp[i + 1] += dp[i - 1];
            dp[i + 1] %= MOD;
        }

        System.out.println(dp[str.length()]);
    }

}
