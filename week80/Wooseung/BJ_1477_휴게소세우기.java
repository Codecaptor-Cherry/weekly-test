
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
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 2];
        arr[0] = 0;
        arr[1] = L;

        st = new StringTokenizer(br.readLine());
        for(int i = 2; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int left = 1;
        int right = L - 1;

        while(left <= right) {

            int mid = (left + right) / 2;
            int cnt = 0;

            for(int i = 1; i < arr.length; i++) {
                cnt += (arr[i] - arr[i - 1] - 1) / mid;
            }

            if(cnt > M) left = mid + 1;
            else right = mid - 1;

        }
        System.out.println(left);
    }

}
