import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] quot = new int[n];
        for (int i = 0; i < n; i++) {
            quot[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(quot);

        for (int i = n; i >= 1; i--) {
            if (quot[n - i] >= i) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(0);
    }
}
