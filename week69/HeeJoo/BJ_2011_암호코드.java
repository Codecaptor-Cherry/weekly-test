package saturday.sat240622;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * A = 1, B = 2, ... Z = 26
 * BEAN = 25114
 * 하지만 25114는 BEAAD, YAAD, YAN, YKD, BEKD, BEAN ~ 총 6가지 가능
 * 어떤 암호가 주어졌을 때, 암호 해석 경우의 수 구하기
 * 암호 해석이 불가능한 경우 0 출력
 *
 * 해석 불가능 ? 0으로 시작하거나 0이 연속으로 있는 경우
 */
public class BJ_2011_암호코드 {
    static final int MOD = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int length = input.length();
        int[] dp = new int[length + 1];
        dp[0] = 1; // 첫번째, 두번째 숫자가 해석 가능한 경우를 위해 dp[0] = 1
        dp[1] = 1;

        if(input.charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        for(int i = 1; i < length; i++) {
            // 1. 현재 숫자만
            int now = ctoi(input.charAt(i));

            if(now != 0) {
                dp[i + 1] += dp[i];
            }

            // 2. 앞의 숫자 포함
            int pre = stoi(input.substring(i - 1, i + 1));
            if(pre == 0) { // 00인 경우
                System.out.println(0);
                return;
            }
            else if(pre >= 10 && pre <= 26) { // 10 ~ 26 ... 01 ~ 09는 불가능
                dp[i + 1] += dp[i - 1];
            }

            dp[i + 1] %= MOD;
        }

        System.out.println(dp[length]);
    }

    public static int ctoi(char ch) {
        return (int)(ch - '0');
    }
    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
