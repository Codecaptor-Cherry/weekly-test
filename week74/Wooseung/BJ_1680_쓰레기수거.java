
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            int W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int cnt = 0;
            int total = 0;
            int prev = 0;

            int[][] arr = new int[N][2];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int dist = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                arr[i][0] = dist;
                arr[i][1] = weight;
            }

            for(int i = 0; i < N; i++) {
                if(cnt + arr[i][1] < W) {
                    cnt += arr[i][1];
                    total += arr[i][0] - prev ;
                }else if(cnt + arr[i][1] == W){
                    cnt = 0;
                    total += (arr[i][0] - prev) + arr[i][0] * 2;
                    if(i == N - 1) total -= arr[i][0] * 2;
                }else {
                    cnt = arr[i][1];
                    total += (arr[i][0] - prev) + arr[i][0] * 2;
                }
                prev = arr[i][0];
            }
            if(cnt <= W) total += arr[N - 1][0];

            System.out.println(total);
        }

    }

}
