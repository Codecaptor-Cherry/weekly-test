package saturday.sat240727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 사탕은 무한개... N명의 아이들에게 순서대로 사탕 주기
 * 여러 번에 걸쳐 사탕 꺼내기
 * 한 명의 아이가 새로 올 때마다 사탕을 최대 M번 꺼내며, A1, A2, ..., Am개씩 차례대로 꺼냄(반드시 하나 이상)
 * i번째 아이는 Bi개의 사탕을 받고 싶어함
 * 도중에 꺼낸 사탕 개수의 총합이 Bi개 이상이 되면 꺼낸 모든 사탕을 받고 run
 * M번 사탕을 꺼냈음에도 Bi개 미만이라면 아이 쫓아내기
 * 각 아이가 사탕을 받고 떠날 때까지 사탕을 꺼내야 하는 횟수 구하기
 */
public class BJ_27968_사사의사차원사탕봉지 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = stoi(st.nextToken()); // 아이의 수
        int M = stoi(st.nextToken()); // 최대 횟수 M

        long[] arr = new long[M];
        long max = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            arr[i] = stol(st.nextToken()); // 범위 10^9로 long 사용
            max += arr[i];
        }

        long[] dp = new long[M]; // k번째까지 꺼낸 사탕의 수
        dp[0] = arr[0];
        for(int i = 1; i < M; i++) {
            dp[i] = arr[i] + dp[i - 1];
        }

        for(int i = 0; i < N; i++) {
            long want = stol(br.readLine()); // 각 아이가 받고 싶어하는 사탕의 개수. 범위 10^12로 long 사용

            if(want > max) {
                sb.append("Go away!\n");
                continue;
            }

            int left = 0, right = M - 1;
            while(left < right) {
                int mid = (left + right) / 2;

                if(dp[mid] >= want) { // 사탕 충분
                    right = mid;
                } else { // 사탕 부족
                    left = mid + 1;
                }
            }

            sb.append((left  + 1) + "\n");
        }

        System.out.println(sb);
    }

    public static long stol(String str) {
        return Long.parseLong(str);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
