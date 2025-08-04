
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {

            int cnt = 0;
            double tmp = 1000000001.0;

            for (int j = i - 1; j >= 0; j--) {
                double slope = (double) (arr[i] - arr[j]) / (i - j);
                if (tmp > slope) {
                    cnt++;
                    tmp = slope;
                }
            }
            tmp = -1000000001.0;
            for (int j = i + 1; j < N; j++) {
                double slope = (double) (arr[i] - arr[j]) / (i - j);
                if (tmp < slope) {
                    cnt++;
                    tmp = slope;
                }
            }
            ans = Math.max(ans, cnt);
        }

        System.out.println(ans);
    }

}
