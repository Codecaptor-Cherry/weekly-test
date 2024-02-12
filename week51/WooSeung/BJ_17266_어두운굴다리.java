package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_어두운굴다리 {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[M];
        int ans = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 1;
        int right = N;

        while(left <= right) {
            int mid = (left + right) / 2;
            boolean flag = true;

            if(check(mid, arr)) {
                ans = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        System.out.println(ans);

    }

    static boolean check(int mid, int[] arr) {

        int prev = 0;

        for(int i = 0; i < M; i++) {
            if(arr[i] - mid <= prev) {
                prev = arr[i] + mid;
            }else return false;
        }

        if(N - prev > 0) return false;

        return true;
    }

}
