import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] boxes = new int[N];
        st =  new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (boxes[i] > boxes[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
                max = Math.max(max, lis[i]);
            }
        }

        System.out.println(max);
    }
}

