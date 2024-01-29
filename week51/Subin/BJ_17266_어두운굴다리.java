import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] lamps = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            lamps[i] = Integer.parseInt(st.nextToken());
        }

        int lo = 1, hi = n;
        int ans = 0;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (avail(n, lamps, mid)) {
                hi = mid - 1;
                ans = mid;
            } else {
                lo = mid + 1;
            }
        }

        System.out.println(ans);
    }

    private static boolean avail(int n, int[] lamps, int mid) {
        int prev = 0; // 이전에 가로등이 비추었던 위치

        for (int lamp : lamps) {
            if (lamp - mid <= prev) {
                prev = lamp + mid; // 가로등은 (현재 가로등의 위치 + 높이)만큼 비출 수 있다.
            } else {
                return false;
            }
        }

        return n - prev <= 0;
    }

}
