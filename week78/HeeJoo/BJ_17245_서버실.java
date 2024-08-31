package saturday.sat240831;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N x N칸의 서버실. 각 칸마다 여러 대의 컴퓨터를 쌓을 수 있음
 * 서버실의 절반만 정상관리하도록 함
 * 냉방기의 공기는 서버실의 아래쪽부터 서서히 차오름 -> 1분마다 컴퓨터 한 대의 높이만큼 방을 채움
 * 서버실의 컴퓨터 중 절반 이상이 켜지려면 필요한 시간 구하기
 *
 * 방법1. DFS ... 시간을 늘려가며 처음으로 절반 이상 켜지면 stop -> 시간초과 발생
 * 방법2. 쌓인 컴퓨터의 숫자의 개수 합산
 *
 * 1. 한 칸에 최대 10,000,000대까지 쌓일 수 있으니 sum은 long타입이여야 함
 * 2. 10,000,000대가 쌓이는 경우 1분씩 늘려가며 구하는 경우 시간초과할 가능성 존재 -> 최대 높이를 기준으로 이분탐색 적용
 * 3. 참고 풀이 : 이분탐색보다 현재 시간에 정상화할 수 있는 컴퓨터의 개수를 이용하여 풀이하면 메모리와 시간을 더 감소시킬 수 있음
 */
public class BJ_17245_서버실 {
    static final int LIMIT = 10000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = stoi(br.readLine());

        // ----- original -----
        int[] count = new int[LIMIT + 1];
        long sum = 0;
        int max = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int input = stoi(st.nextToken());

                count[input]++;
                sum += input;

                max = Math.max(max, input);
            }
        }

        // ----- input finish -----
        long target = (sum + 1) / 2; // 절반 목표치

        for(int i = max - 1; i > 0; i--) {
            count[i] = count[i] + count[i + 1];
        }

        long[] dp = new long[LIMIT + 1];
        for(int i = 1; i <= max; i++) {
            dp[i] = count[i] + dp[i - 1];
        }

        int left = 0, right = max;
        while(left < right) {
            int mid = (left + right) / 2;

            if(dp[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        System.out.println(left);

        // ----- another -----
//        int com = 0; // n분에 정상화 가능한 컴퓨터의 개수
//        long sum = 0; // 전체 컴퓨터 수
//        int[] count = new int[LIMIT + 1]; // index의 개수
//
//        for(int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            for(int j = 0; j < N; j++) {
//                int input = stoi(st.nextToken());
//
//                if(input != 0) {
//                    count[input]++; // input의 개수
//                    com++; // 1 이상이면 컴퓨터의 개수 증가 ... 즉 1분일 때 킬 수 있는 컴퓨터의 개수를 세고 있음
//                    sum += input;
//                }
//            }
//        }
//
//        long target = (sum + 1) / 2; // 목표치 : 전체 컴퓨터 수의 절반 이상
//        long csum = 0; // n분까지 정상화 된 컴퓨터의 수
//        int ans = 0;
//
//        while(csum < target) { // 목표치를 달성할 때까지 반복
//            csum += com; // n분에 정상화 가능한 컴퓨터의 수를 합산
//            com -= count[++ans]; // n보다 낮은 높이의 컴퓨터는 정상화할 수 없으므로 제외
//        }
//
//        System.out.println(ans);

    }


    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
