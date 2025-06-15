
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long[] num;
    static long[] sum;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            num = new long[N + 1];
            sum = new long[N + 1];

            for(int i = 1; i <= N; i++) {
                num[i] = Long.parseLong(st.nextToken());
            }

            Arrays.sort(num);
            sum[1] = num[1];
            for(int i = 2; i <= N; i++) {
                sum[i] = num[i] + sum[i - 1];
            }

            long ans = 0;
            for(int i = 2; i <= N; i++) {
                long temp = Long.MAX_VALUE;
                for(int j = i; j <= N; j++) {
                    temp = Math.min(temp, num[j] * i - sum[j] + sum[j - i]);
                }
                ans += temp;
            }
            sb.append(ans + "\n");
        }
        System.out.println(sb.toString());
    }

}
