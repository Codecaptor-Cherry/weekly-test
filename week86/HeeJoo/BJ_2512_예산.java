package saturday.sat241102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 정해진 총액 이하에서 가능한 한 최대의 총 예산을 다음과 같은 방법으로 배정
 * 1. 모든 요청이 배정될 수 있는 경우에는 요청한 금액을 그대로 배정
 * 2. 모든 요청이 배정될 수 없는 경우네는 특정한 정수 상한액을 계산하여 그 이상인 예산요청에는 모두 상한액 배정
 * 위 조건을 모두 만족하도록 배정된 예산들 중 최댓값인 정수 구하기
 *
 * 1. 이분탐색으로 상한액 설정
 * 2. 상한액으로 예산 분배가 가능한지 확인
 */
public class BJ_2512_예산 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine()); // 지방의 수

        int[] arr = new int[N]; // 요청 예산
        int ans = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = stoi(st.nextToken());
        }

        int M = stoi(br.readLine()); // 예산 총액

        int left = 0, right = M, limit = 0;
        while(left < right) {
            int mid = (left + right) / 2; // 임시 상한액
            if(checkAvail(N, M, mid, arr)) { // 배정 가능 ~ 상한액 감소
                left = mid + 1;
                limit = mid;
            } else {
                right = mid;
            }
        }

        for(int k : arr) {
            if(k <= limit) {
                ans = Math.max(ans, k);
            } else {
                ans = limit;
            }
        }
        System.out.println(ans);
    }

    public static boolean checkAvail(int n, int m, int limit, int[] arr) {
        int sum = 0;

        for(int i = 0; i < n; i++) {
            if(arr[i] > limit) { // 상한액 초과
                sum += limit;
            } else { // 상한액 이하
                sum += arr[i];
            }
        }

        if(sum > m) { // 배정 불가능
            return false;
        } else { // 배정 가능
            return true;
        }
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
