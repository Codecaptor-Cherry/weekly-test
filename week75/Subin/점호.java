import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int cnt1 = 0; // K명 중 1학년 수
        int cnt2 = 0; // K명 중 2학년 수
        for (int i = 0; i < k; i++) {
            if (a[i] == 1) ++cnt1;
            else if (a[i] == 2) ++cnt2;
        }

        int ans = 0;
        int aIdx = k;
        while (cnt1 > 0 || cnt2 > 0) {
            // K명 중 1학년/2학년 빼주기
            if (cnt1 > 0) --cnt1;
            if (cnt2 > 0) --cnt2;

            // K명 채워주기
            for (int i = 0; i <= k - (cnt1 + cnt2) && aIdx < n; i++) {
                if (a[aIdx] == 1) ++cnt1;
                else if (a[aIdx] == 2) ++cnt2;
                ++aIdx;
            }
            
            ++ans; // 시간 증가
        }

        System.out.println(ans);
    }

}
