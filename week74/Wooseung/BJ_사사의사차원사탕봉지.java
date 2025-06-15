
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] arr = new long[M];

        st = new StringTokenizer(br.readLine());
        arr[0] = Long.parseLong(st.nextToken());

        for (int i = 1; i < M; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            arr[i] += arr[i - 1];
        }

        for (int i = 0; i < N; i++) {
            long target = Long.parseLong(br.readLine());

            if (target > arr[M - 1]) {
                System.out.println("Go away!");
            } else {
                int low = 0;
                int high = M - 1;
                int result = -1;

                while (low <= high) {
                    int mid = (low + high) / 2;
                    if (arr[mid] >= target) {
                        result = mid + 1;
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
                System.out.println(result);
            }
        }
    }

}
