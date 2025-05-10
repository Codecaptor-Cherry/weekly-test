import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] sum = new long[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            sum[i] = Integer.parseInt(st.nextToken());
            if (i > 0) sum[i] += sum[i - 1];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            long b = Long.parseLong(br.readLine());
            int find = binarySearch(sum, b);

            if (find == m) sb.append("Go away!\n");
            else sb.append(find + 1).append("\n");
        }
        
        System.out.print(sb);
    }

    private static int binarySearch(long[] arr, long find) {
        int s = 0, e = arr.length;

        while (s < e) {
            int m = (s + e) / 2;
            if (arr[m] < find) {
                s = m + 1;
            } else {
                e = m;
            }
        }

        return e;
    }
}
