import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        long ans = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        for(int i = 0; i < N; i++) {
            if(arr[i] > 0) break;
            int start = i + 1;
            int end = N - 1;

            while(start < end) {
                int sCnt = 1;
                int eCnt = 1;
                int sum = arr[i] + arr[start] + arr[end];
                if(sum == 0) {
                    if(arr[start] == arr[end]) {
                        int n = end - start + 1;
                        ans += n * (n - 1) / 2;
                        break;
                    }
                    while(start + 1 < end && arr[start] == arr[start + 1]) {
                        sCnt++;
                        start++;
                    }
                    while(start < end - 1 && arr[end] == arr[end - 1]) {
                        eCnt++;
                        end--;
                    }
                    ans += eCnt * sCnt;
                }
                if(sum > 0) end--;
                else start++;
            }
        }
        System.out.println(ans);
    }
}
