
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int idx = K + 1;
        int day = 0;
        int cnt = 0;
        while(idx <= N) {
            if(arr[idx] == arr[idx - K]) {
                idx++;
            }else if(arr[idx] > arr[idx - K]) {
                cnt += arr[idx] - arr[idx - K];
                arr[idx] = arr[idx - K];
                day++;
                Arrays.sort(arr);
            }
        }
        System.out.println(cnt + " " + day);
    }

}
