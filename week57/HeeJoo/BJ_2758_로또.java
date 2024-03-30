package saturday.sat240330;

/*
 * 1부터 m까지 숫자 중에 n개의 수를 고르는 로또
 * 각 숫자는 이전에 고른 수보다 최소 2배가 되도록 고르기
 * 수를 고르는 경우의 수 만큼 로또 구매 ... 구매하는 로또 개수 구하기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_2758_로또 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        int[][] testcase = new int[T][2];
        int max = 0;
        for(int t = 0; t < T; t++) {
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);

            testcase[t][0] = n;
            testcase[t][1] = m;

            max = Math.max(max, m);
        }

        // long으로 안하면 오류남
        long[][] dp = new long[11][max + 1];

        for(int i = 1; i <= max; i++) {
            dp[1][i] = i;
        }

        for(int i = 2; i <= 10; i++) {
            for(int j = 2; j <= max; j++) {
                int range = j / 2;

                // 지금까지 가능한 경우의 수 + j를 만들기 위해 가능한 경우의 수
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j / 2];
            }
        }

        for(int[] arr : testcase) {
            sb.append(dp[arr[0]][arr[1]] + "\n");
        }

        print(dp);
        System.out.println(sb);

    }

    public static void print(long[][] map) {
        for(long[] arr : map) {
            for(long k : arr) {
                System.out.printf("%d ", k);
            }
            System.out.println();
        }
        System.out.println();
    }
}
