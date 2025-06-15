
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        long sum = 0;
        int max = Integer.MIN_VALUE;
        int ans = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = num;
                sum += num;
                max = Math.max(max, num);
            }
        }
        double target = (double) sum / 2;

        if(sum != 0) {
            int start = 0;
            int end = max;
            while(start <= end) {
                int mid = (start + end) / 2;
                long cnt = 0;
                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < N; j++) {
                        if(arr[i][j] <= mid) {
                            cnt += arr[i][j];
                        }else {
                            cnt += mid;
                        }
                    }
                }
                if(target <= (double)cnt) {
                    ans = mid;
                    end = mid - 1;
                }else {
                    start = mid + 1;
                }
            }
        }
        System.out.println(ans);
    }

}
