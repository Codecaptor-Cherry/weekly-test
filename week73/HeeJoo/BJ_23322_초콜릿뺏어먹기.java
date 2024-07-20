package saturday.sat240720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 초콜릿 개수가 오름차순이 되도록 N개의 통을 일렬로 배열
 * 1. K < i인 i를 골라, i - K번째 통에 있는 초콜릿의 개수와 같아질 때까지 i번째 통에서 초콜릿 꺼내먹기
 * 2. 초콜릿 통 재정렬
 * 최대 섭취 초콜릿 개수 구하기
 */
public class BJ_23322_초콜릿뺏어먹기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = stoi(st.nextToken());
        int K = stoi(st.nextToken());

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = stoi(st.nextToken());
        }

        int maxCnt = 0, minDay = 0;
        int eatIdx = K + 1;
        // i 골라서 먹기
        while(eatIdx <= N) {
            // 1. arr[i] == arr[i - K]인 경우, 다음 통으로
            if(arr[eatIdx] == arr[eatIdx - K]) {
                eatIdx++;
//                System.out.println("case 1");
            }
            // 2. arr[i] > arr[i - K]인 경우, 초콜릿 섭취
            else if(arr[eatIdx] > arr[eatIdx - K]) {
                maxCnt += arr[eatIdx] - arr[eatIdx - K];
                minDay++;

                arr[eatIdx] = arr[eatIdx - K];

                // 통 재정렬
                Arrays.sort(arr);

//                System.out.println("case 2");
            }
        }

        System.out.printf("%d %d\n", maxCnt, minDay);

    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
