package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_20044_ProjectTeams {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[2 * N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            int sum = arr[i] + arr[arr.length - 1 - i];
            min = Math.min(min, sum);
        }
        System.out.println(min);
    }

}
