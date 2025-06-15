
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] apple;
    static int[][] banana;
    static int[][] appleSum;
    static int[][] bananaSum;
    static int[][] total;
    static int[][] dp;
    static int R, C;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        apple = new int[R][C];
        banana = new int[R][C];
        appleSum = new int[R][C];
        bananaSum = new int[R][C];
        total = new int[R][C];
        dp = new int[R][C];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(in.readLine());
            for (int c = 0; c < C; c++) {
                String str = st.nextToken();
                int num = Integer.parseInt(str.substring(1));
                if (str.charAt(0) == 'A') apple[r][c] = num;
                else banana[r][c] = num;
            }
        }

        appleSum();
        bananaSum();
        totalSum();
        findMaxSum();
        System.out.println(dp[R - 1][C - 1]);
    }

    static void findMaxSum() {


        for (int r = 0; r < R; r++) dp[r][0] = total[r][0];

        for (int c = 1; c < C; c++) {
            for (int r = 0; r < R; r++) {
                if (r == 0) {
                    dp[r][c] = dp[r][c - 1] + total[r][c];
                } else {
                    dp[r][c] = Math.max(
                            Math.max(
                                    dp[r][c - 1] + total[r][c],
                                    dp[r - 1][c] - apple[r][c]),
                            dp[r - 1][c - 1] + total[r][c]);
                }
            }
        }
    }

    static void totalSum() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                total[r][c] = appleSum[r][c] + bananaSum[r][c];
            }
        }
    }

    static void appleSum() {
        for (int c = 0; c < C; c++) {
            for (int r = R - 1; r > 0; r--) {
                appleSum[r - 1][c] = appleSum[r][c] + apple[r][c];
            }
        }
    }

    static void bananaSum() {
        for (int c = 1; c < C; c++) {
            for (int r = 0; r < R - 1; r++) {
                bananaSum[r + 1][c] = bananaSum[r][c] + banana[r][c];
            }
        }
    }

}
