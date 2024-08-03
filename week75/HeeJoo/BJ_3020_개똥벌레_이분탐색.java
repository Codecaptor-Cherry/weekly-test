package saturday.sat240803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 동굴 길이 N(짝수), 높이 H
 * 첫 번째 장애물은 항상 석순, 그 이후는 종유석과 석숭이 번갈아가며 등장
 * 개똥벌레 한 마리가 장애물을 피하지 않고 자신이 지나갈 구간을 정한 다음 일직선으로 지나가면서 모든 장애물 파괴
 * 개똥벌레가 파괴해야하는 장애물의 최솟값과 그러한 구간의 개수 구하기
 *
 * 참조 : https://loosie.tistory.com/557
 * 이분탐색 OR 누적합
 * 이분탐색 방식으로 풀이
 * */
public class BJ_3020_개똥벌레_이분탐색 {
    static int N, H;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        N = stoi(inputs[0]);
        H = stoi(inputs[1]);

        int[] down = new int[N / 2]; // 석순
        int[] up = new int[N / 2]; // 종유석
        for(int i = 0; i < N / 2; i++) {
            down[i] = stoi(br.readLine());
            up[i] = stoi(br.readLine());
        }

        Arrays.sort(down);
        Arrays.sort(up);
        int min = N, ans = 0;

        for(int i = 1; i <= H; i++) { // 구간 i에 대하여
            // 장애물 개수 구하기
            // 석순(down) : 전체 석순 개수 - 구간 i보다 큰 석순의 개수(= i 이상인 첫 번째 idx)
            // 종유석(up) : 전체 종유석 개수 - 구간 i까지 닿는 종유석의 개수(= (h - i + 1) 이상인 첫 번째 idx)
            int conflict = binarySearch(i, down) + binarySearch(H - i + 1, up);

            if(min == conflict) {
                ans++;
                continue;
            }

            if(min > conflict) {
                min = conflict;
                ans = 1;
            }
        }

        System.out.printf("%d %d", min, ans);
    }

    // 목표높이, 장애물 배열
    public static int binarySearch(int h, int[] arr) {
        int left = 0, right = N / 2;

        while(left < right) {
            int mid = (left + right) / 2;

            if(arr[mid] >= h) { // h 이상인 첫 번째 idx를 찾아야 하므로 arr[mid] == h인 경우 right을 mid로 갱신
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return arr.length - right;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
