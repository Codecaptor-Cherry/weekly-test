import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        final long MOD = 1000000007;

        for(int t = 0; t < T; t++) {
            long ans = 1;
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) pq.add(Long.parseLong(st.nextToken()));

            while(pq.size() > 1) {
                long s1 = pq.poll();
                long s2 = pq.poll();
                long temp = s1 * s2;

                ans *= temp % MOD;
                ans %= MOD;

                pq.add(temp);
            }
            System.out.println(ans);
        }

    }

}
