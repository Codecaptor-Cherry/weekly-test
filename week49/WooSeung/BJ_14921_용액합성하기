package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14921_용액합성하기 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int right = N - 1;
        while(left < right) {
            int sum = arr[left] + arr[right];
            if(Math.abs(ans) > Math.abs(sum)) ans = sum;
            if(sum < 0) left++;
            else right--;
        }
        System.out.println(ans);
    }

}
