import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();

        System.out.println(findNextPalindrome(N));
    }

    private static String findNextPalindrome(String N) {
        int length = N.length();
        String leftHalf = N.substring(0, (length + 1) / 2);
        BigInteger leftNum = new BigInteger(leftHalf);
        String nextPalindrome;

        // 모든 숫자가 9인 경우 처리
        if (N.replaceAll("9", "").isEmpty()) {
            return new BigInteger("1" + "0".repeat(length - 1) + "1").toString();
        }

        while (true) {
            nextPalindrome = buildPalindrome(leftNum.toString(), length % 2 == 0);
            if (new BigInteger(nextPalindrome).compareTo(new BigInteger(N)) > 0) {
                return nextPalindrome;
            }
            leftNum = leftNum.add(BigInteger.ONE); // 왼쪽 절반 증가
        }
    }

    private static String buildPalindrome(String left, boolean evenLength) {
        String right = new StringBuilder(left).reverse().toString();
        if (evenLength) {
            return left + right;
        } else {
            return left.substring(0, left.length() - 1) + right;
        }
    }
}
