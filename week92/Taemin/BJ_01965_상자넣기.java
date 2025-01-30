import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_01965_상자넣기 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfBoxes = Integer.parseInt(br.readLine());
        int[] sizeOfBoxes = Arrays.stream(br.readLine().split(" "))
                                  .mapToInt(Integer::parseInt)
                                  .toArray();

        int[] dp = new int[numOfBoxes];
        for (int i = 0; i < numOfBoxes; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (sizeOfBoxes[i] > sizeOfBoxes[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        Arrays.sort(dp);
        System.out.print(dp[numOfBoxes - 1]);
    }
}
