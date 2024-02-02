
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
        int[] arr = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[Integer.parseInt(st.nextToken())]++;
        }

        int min = Math.min(arr[0], arr[3]);
        arr[0] -= min;
        arr[3] -= min;
        ans += min * 3;

        min = Math.min(arr[1], arr[2]);
        arr[1] -= min;
        arr[2] -= min;
        ans += min * 3;

        int a = -1, b = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                if (a == -1) a = i;
                else b = i;
            }
        }

        if (b == -1) System.out.println(ans);
        else {
            ans += Math.min(arr[a], arr[b]) * Math.abs(a - b);
            System.out.println(ans);
        }
    }
}
