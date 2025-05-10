package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ_32247_이상한나라의끈끈이주걱 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // c
            arr[i][1] = Integer.parseInt(st.nextToken()); // x
            arr[i][2] = Integer.parseInt(st.nextToken()); // h
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> a[1]));

        int curY = 0;
        int curX = 0;
        boolean flag = true;

        for (int[] t : arr) {
            int c = t[0], x = t[1], h = t[2];
            int dist = x - curX;
            curY -= dist;
            if (c == 1) {
                if (curY >= h) {
                    flag = false;
                    break;
                }
            } else {
                int need =h + 1;
                if (curY < need) curY = need;
            }
            curX = x;
        }

        if (flag) {
            int dist = N - curX;
            curY -= dist;
            if (curY > 0) flag = false;
        }

        System.out.println(flag ? "stay" : "adios");
    }

}
