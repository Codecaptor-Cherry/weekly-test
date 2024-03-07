package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_12970_AB {

    static int N, K;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        char[] arr = new char[N];
        Arrays.fill(arr,'B');
        int a = checkA();
        int b = N - a;
        if(a == -1) {
            System.out.println(-1);
        }else {

            for(int i  = 0; i < a - 1; i++) {
                arr[i] = 'A';
            }
            int idx = K - ((a - 1) * b);
            arr[(N - 1) - idx] = 'A';

            for(int i = 0; i < N; i++) {
                sb.append(arr[i]);
            }
            System.out.println(sb.toString());
        }
    }

    static int checkA() {
        int a = 1;
        int b = N - 1;

        while(a * b < K) {
            if(b < 0) return -1;
            a++;
            b--;
        }
        return a;
    }
}
