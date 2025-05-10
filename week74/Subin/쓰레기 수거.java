import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());

            int W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int[] xi = new int[N];
            int[] wi = new int[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                xi[i] = Integer.parseInt(st.nextToken());
                wi[i] = Integer.parseInt(st.nextToken());
            }

            int dist = 0;
            int w = 0; // 현재 용량
            int x = 0; // 현재 위치

            for (int i = 0; i < N; i++) {
                dist += xi[i] - x; // 이동 거리

                w += wi[i];
                x = xi[i];

                // 1. 쓰레기의 양이 용량에 도달했을 때
                if (w == W) {
                    dist += xi[i]; // 이동 거리 (쓰레기장으로 돌아가야함)
                    x = 0;
                    w = 0;
                }

                // 2. 그 지점의 쓰레기를 실었을 때 쓰레기차의 용량을 넘게 될 때
                if (w > W) {
                    dist += xi[i] * 2; // 이동 거리 (쓰레기장 갔다가 현재 위치로 돌아와야 함)
                    w = wi[i];
                }
            }

            // 3. 더 이상 쓰레기를 실을 지점이 없을 때
            dist += x;

            System.out.println(dist);
        }
    }
}
