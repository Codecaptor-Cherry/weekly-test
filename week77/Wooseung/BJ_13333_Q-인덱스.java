
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

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        int maxQIndex = 0; // 최대 q-index를 저장할 변수
        for (int k = 1; k <= N; k++) {
            if (arr[N - k] >= k) {
                maxQIndex = k; // k번 이상 인용된 논문이 k편 이상이면 maxQIndex 갱신
            }
        }

        System.out.println(maxQIndex); // 계산된 최대 q-index 출력
    }

}
