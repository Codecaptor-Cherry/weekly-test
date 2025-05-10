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

        int cnt = 0, day = 0;
        for (int i = 1; i < n; i++) {
            if (a[i - 1] <= a[i] && a[0] < a[i]) {
                cnt += (a[i] - a[0]);
                a[i] = a[0];
                ++day;
            }
            Arrays.sort(a);
        }

        System.out.println(cnt + " " + day);
    }
}
