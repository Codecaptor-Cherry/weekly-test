
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        int[] dp1 = new int[N / 2 + 1];
        int[] dp2 = new int[N / 2 + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N / 2; i++) {
            dp1[i] = dp1[i - 1] + Integer.parseInt(st.nextToken());
            dp2[i] = dp2[i - 1] + Integer.parseInt(st.nextToken());
        }

        ans = Math.max(dp1[N / 2], dp2[N / 2]);
        if(N == 2) {
            System.out.println(ans);
            return;
        }

        int temp = 0;
        for(int i = 0; i < N; i++) {
        
            if(i % 2 == 1) {
                temp = dp1[i /2] + (dp2[N / 2] - dp2[i / 2]);
            }
      
            else {
                temp = dp1[i / 2 + 1] + (dp2[N / 2 - 1] - dp2[i / 2]);
            }
            ans = Math.max(ans, temp);
        }
        System.out.println(ans);
    }

}
