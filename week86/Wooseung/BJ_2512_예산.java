import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int sum = 0;
        int right = 0;
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            right = Math.max(right, arr[i]);
        }
        int money = Integer.parseInt(br.readLine());
        if(money >= sum) {
            System.out.println(right);
            return;
        }
        int left = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            long budget = 0;
            for(int i = 0; i < N; i++) {
                if(arr[i] > mid) budget += mid;
                else budget += arr[i];
            }
            if(budget <= money) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        System.out.println(right);
    }

}
