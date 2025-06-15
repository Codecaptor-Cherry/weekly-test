import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] cnt;
    static int[] endTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });

        cnt = new int[N + 1]; 
        endTime = new int[N + 1];
        int top = 0;

        for (int i = 0; i < N; i++) {
            int idx = -1;
            for (int j = 1; j <= top; j++) {
                if (endTime[j] <= arr[i][0]) {
                    idx = j;
                    break;
                }
            }

            if (idx == -1) {
                top++;
                idx = top;
            }
            cnt[idx]++;
            endTime[idx] = arr[i][1];
        }

        System.out.println(top);
        for (int i = 1; i <= top; i++) {
            System.out.print(cnt[i] + " ");
        }
    }
}
