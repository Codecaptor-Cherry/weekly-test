package saturday.sat240413;

/*
 * N번의 돈 빌리기 ~ 각각 A(1) ~ A(N)
 * M번의 변제 요청이 들어오면 N번 중 랜덤 M번 고른 후, 고른 것 중 가장 많은 돈을 빌렸을 때 빌린 돈 x M만큼 변제
 * 갚아야 하는 돈 = 빌린 돈 + 추가 빚 ~ 변제 금액 + (변제 금액 - 선택된 금액합)
 * S(M) : M번을 선택하여 추가적으로 갚아야 하는 돈의 최솟값
 * S 배열의 합 구하기
 *
 * S(i) += max - 추가된 날의 금액
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_10427_빚 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T

        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().split(" ");

            int N = Integer.parseInt(input[0]); // 주의 ! 배열 길이 N과 배열이 한 줄로 입력됨

            long[] debt = new long[N + 1]; 
            for (int i = 1; i <= N; i++) {
                debt[i] = Long.parseLong(input[i]);
            }
            Arrays.sort(debt); // 빚 오름차순 정렬

            // 누적합
            long[] sum = new long[N + 1];
            sum[1] = debt[1];
            for(int i = 2; i <= N; i++) {
                sum[i] = debt[i] + sum[i - 1];
            }

            long[] minArr = new long[N + 1]; // index번 변제 요청 시 최소 변제 금액을 저장할 배열
            Arrays.fill(minArr, Long.MAX_VALUE); // 최대치로 초기화
            // minArr[0]은 제외, minArr[1]은 자기자신이므로 0
            minArr[0] = 0;
            minArr[1] = 0;

            // 슬라이딩 윈도우 + 누적합
            for(int i = 2; i <= N; i++) { // 슬라이딩 윈도우 크기 ~ M
                for(int j = i; j <= N; j++) { // 시작점 ~ 맨 오른쪽 인덱스
                    // debt를 오름차순으로 정렬했기때문에 맨 오른쪽 값이 가장 큼
                    // 추가 변제 금액 = 선택된 금액 중 가장 큰 값(맨 오른쪽) * M - 선택된 금액의 합
                    // 누적합을 이용해서 선택된 금액의 합 구하기
                    minArr[i] = Math.min(minArr[i], debt[j] * i - sum[j] + sum[j - i]);
                }
            }

            printAnswer(minArr, sb); // S(1) + ... S(N);
        }

        System.out.println(sb);
    }

    public static void printAnswer(long[] arr, StringBuilder sb) {
        long sum = 0;

        for(int i = 2; i < arr.length; i++) {
            sum += arr[i];
        }

        sb.append(sum + "\n");
    }
}
