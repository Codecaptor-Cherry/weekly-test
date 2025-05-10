import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

       int[] arr = new int[n];
       Arrays.fill(arr, k);

       int idx = 0;
       int ans = 1;

       while (true) {
           // 물 뿌리기
           for (int i = idx; i < idx + a && i < n; i++) arr[i] += b;
           // 수분 감소
           for (int i = 0; i < n; i++) {
               if (--arr[i] == 0) {
                   System.out.println(ans);
                   return;
               }
           }

           ++ans;
           idx += a;
           idx %= n;
       }
    }
}
