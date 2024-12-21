package saturday.sat241221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 상자 마트료시카의 최대 개수
 */
public class BJ_1965_상자넣기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = stoi(br.readLine());
        int[] boxes = new int[n];

        String[] inputs = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            boxes[i] = stoi(inputs[i]);
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(boxes[i] > boxes[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int ans = 0;
        for(int k : dp) {
            ans = Math.max(ans, k);
        }

        System.out.println(ans);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
