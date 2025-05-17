import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a.add(Integer.parseInt(st1.nextToken()));
            b.add(Integer.parseInt(st2.nextToken()));
        }

        int aMax = 0;
        int bMax = 0;
        for (int i = 0; i < n; i++) {
            aMax = Math.max(aMax, lis(a));
            bMax = Math.max(bMax, lis(b));
            a.add(a.remove(0));
            b.add(b.remove(0));
        }

        if (aMax > bMax) System.out.println("YJ Win!");
        else if (aMax < bMax) System.out.println("HG Win!");
        else System.out.println("Both Win!");
    }

    private static int lis(List<Integer> arr) {
        int n = arr.size();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr.get(i) > arr.get(j)) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int max = 0;
        for (int len : dp) max = Math.max(max, len);
        return max;
    }
}
