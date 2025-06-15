
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] up = new int[H + 2];
        int[] down = new int[H + 2];

        for(int i = 0; i < N; i++) {
            int idx = Integer.parseInt(br.readLine());

            if(i % 2 == 0) {
                up[idx]++;
            }else {
                down[H + 1 - idx]++;
            }
        }
        for(int i = H; i >= 1; i--) {
            up[i] += up[i + 1];
        }
        for(int i = 1; i <= H; i++) {
            down[i] += down[i - 1];
        }

        int min = Integer.MAX_VALUE;
        int cnt = 0;

        for(int i = 1; i <= H; i++) {
            int sum = up[i] + down[i];

            if(min == sum) {
                cnt++;
            }else if (min > sum){
                min = sum;
                cnt = 1;
            }
        }
        System.out.println(min + " " + cnt);
    }
}
