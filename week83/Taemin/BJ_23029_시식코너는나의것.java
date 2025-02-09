import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_23029_시식코너는나의것 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfCorner = Integer.parseInt(br.readLine());
        int[] numOfFoods = new int[numOfCorner + 1];

        for (int i = 1; i <= numOfCorner; i++) {
            numOfFoods[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[3][numOfCorner + 1];
        dp[0][1] = numOfFoods[1];

        for (int i = 2; i <= numOfCorner; i++) {
            int numOfFood = numOfFoods[i];

            dp[0][i] = dp[2][i - 1] + numOfFood;
            dp[1][i] = dp[0][i - 1] + numOfFood / 2;
            dp[2][i] = Math.max(Math.max(dp[0][i - 1], dp[1][i - 1]), dp[2][i - 1]);
        }

        int result = Math.max(Math.max(dp[0][numOfCorner], dp[1][numOfCorner]), dp[2][numOfCorner]);
        System.out.print(result);
    }
}
