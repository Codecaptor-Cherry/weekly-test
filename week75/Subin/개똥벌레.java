import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[] d = new int[n / 2]; // 석순
        int[] u = new int[n / 2]; // 종유석
        for (int i = 0; i < n / 2; i++) {
            d[i] = Integer.parseInt(br.readLine());
            u[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(d);
        Arrays.sort(u);

        int min = (int) 1e9;
        int cnt = 0;
        for (int i = 1; i <= h; i++) { // 1부터 h까지 탐색
            int blocks = getBlockCnt(i, h, d, u);

            if (blocks == min) {
                ++cnt;
            } else if (blocks < min) {
                min = blocks;
                cnt = 1;
            }
        }

        System.out.println(min + " " + cnt);
    }

    private static int getBlockCnt(int i, int h, int[] d, int[] u) {
        // i 높이에서 부셔야 하는 석순의 개수
        int s1 = d.length - lowerBound(d, i);
        // i 높이에서 부셔야 하는 종유석의 개수
        int s2 = u.length - lowerBound(u, h - i + 1);

        return s1 + s2;
    }

    private static int lowerBound(int[] arr, int i) {
        int s = 0, e = arr.length;
        while (s < e) {
            int m = (s + e) / 2;
            if (arr[m] < i) s = m + 1;
            else e = m;
        }
        return s;
    }
}
