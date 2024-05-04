package saturday.sat240504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N개의 먹이 일렬로 나열,,, 오른쪽으로 이동
 * 시작하는 순간의 애벌레 위치는 0
 * i번째 먹이는 오른쪽으로 i초 기어가야 도달 가능
 * 매초 1만큼 오른쪽으로 무조건 진행 !!!
 * i번째 먹이가 맛있을수록 높은 만족도
 * 한 번 먹기 시작하면 연속적으로 계속 먹어야 함
 * 누적 만족도가 최소 만족도 K 이상이 되거나 더 이상 먹을 먹이가 없을 때 중지
 * 최소 만족도 K를 초과한 만족도만큼 탈피 에너지 축적... 이후 다시 0으로 리셋 후 먹이 먹기 가능
 *
 * input : 먹이 개수, 최소 만족도, 먹이 만족도
 * output : 최대 축적 탈피 에너지. 탈피가 불가능 한 경우 0
 *
 * 누적합 + 투포인터
 * 참고 : https://white-board.tistory.com/192
 */
public class BJ_20181_꿈틀꿈틀호석애벌레_효율성 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] feed = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            feed[i] = Integer.parseInt(st.nextToken());
        }

        long[] dp = new long[N + 1];  // dp[idx] : idx - 1까지 왔을 때 얻을 수 있는 최대 에너지 ... 누적합때문에 feed와 범위가 다름 ... dp[0] 사용 xxx

        int left = 0, right = 1; // left부터 right 이전까지
        long sum = feed[left];

        while(right <= N) { // 끝에 도달할 때까지 ~ 다먹어야 탈피하는 경우 생각 ...
            if(sum >= K) { // 만족
                while(sum >= K) { // 최소 만족도를 처음으로 넘을 때까지 범위 축소
                    dp[right] = Math.max(dp[right], dp[left] + sum - K); // 현재 범위의 탈피 에너지 + 이전 까지의 축적 탈피 에너지와 대소 비교
                    sum -= feed[left++]; // right이 기준이니 left를 증가시켜서 범위 축소
                }
            } { // 불만족
                dp[right] = Math.max(dp[right], dp[right - 1]); // right - 1번째 먹이를 먹을 때 얻을 수 있는 최대 축적 탈피 에너지
                
                if(right == N) { // 끝에 도달한 경우 아래 코드 범위 벗어남
                    break;
                }
                
                sum += feed[right++]; // 만족도 더하기
            }
        }

        System.out.println(dp[N]);
    }

    public static void print(long[] arr) {
        for(long k : arr) {
            System.out.print(k + " ");
        }
        System.out.println();
    }
}
