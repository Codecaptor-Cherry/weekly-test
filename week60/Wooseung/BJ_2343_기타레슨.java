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

        int[] lessons = new int[N];

        st = new StringTokenizer(br.readLine());
        int start = 0;
        int end = 0;
        for(int i = 0; i < N; i++) {
            lessons[i] = Integer.parseInt(st.nextToken());
            start = Math.max(start, lessons[i]);
            end += lessons[i];
        }

        while(start <= end) {
            int middle = (start + end) / 2;
            int sum = 0;
            int cnt = 0;
            for(int i = 0; i < N; i++) {
                if(sum + lessons[i] > middle) {
                    cnt++;
                    sum = 0;
                }
                sum += lessons[i];
            }
            if(sum != 0) cnt++;
            if(cnt > M) start = middle + 1;
            else end = middle - 1;
        }
        System.out.println(start);
    }

}
