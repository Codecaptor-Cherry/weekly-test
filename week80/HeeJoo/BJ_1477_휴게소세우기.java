package saturday.sat240921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * N개의 휴게소 존재
 * M개의 휴게소를 더 건설하려 함
 * 이미 존재하는 곳과 고속도로 끝에는 휴게소 건설 불가
 * 휴게소는 정수 위치에만 건설 가능
 * 휴게소 M개를 더 지어서 휴게소가 없는 구간의 길이 최댓값을 최소화 하기
 *
 * 1. 이분탐색을 위해 휴게소의 거리차를 기준으로 둔다.
 * 2. 현재 휴게소 사이에 mid 기준으로 총 몇 개의 휴게소(sum)를 설치할 수 있나?
 * 3 - 1. sum > m : 설치 개수 초과 ~ 거리차 키우기
 * 3 - 2. sum <= m : 설치 가능 ~ 거리차 좁히기
 */
public class BJ_1477_휴게소세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = stoi(st.nextToken()); // 현재 휴게소 개수
        int M = stoi(st.nextToken()); // 추가 건설 휴게소 개수
        int L = stoi(st.nextToken()); // 고속도로의 길이

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N + 2]; // 현재 존재하는 휴게소들의 위치. 시작과 끝을 추가하기 위해 N + 2로 크기 설정
        arr[0] = 0; // 시작
        arr[N + 1] = L; // 끝
        for(int i = 1; i <= N; i++) {
            arr[i] = stoi(st.nextToken());
        }

        Arrays.sort(arr); // 이분 탐색 전 정렬 필수

        int left = 1, right = L; // left를 0으로 설정하면 devided zero error 발생

        while(left < right) {
            int mid = (left + right) / 2; // 현재 설정된 구간의 중간값

            int sum = 0; // 설치할 수 있는 휴게소 개수

            // 1번 방법
            for(int i = 0; i < arr.length - 1; i++) {
                sum += (arr[i + 1] - arr[i]) / mid; // 휴게소 사이에 mid 기준으로 설치할 수 있는 휴게소의 개수

                if((arr[i + 1] - arr[i]) % mid == 0) { // 나머지가 없는 경우 이미 설치된 휴게소이므로 제외 처리
                    sum--;
                }
            }

            // 2번 방법
//            for(int i = 0; i < arr.length - 1; i++) {
//                sum += (arr[i + 1] - arr[i] - 1) / mid; // 휴게소 사이에 mid 기준으로 설치할 수 있는 휴게소의 개수
//            }

            if(sum > M) { // 설치 개수 초과 ~ 중간값 증가
                left = mid + 1;
            } else { // 설치 개수 충족 ~ 최댓값을 최소화 하기 위해 중간값 감소
                right = mid;
            }
        }

        System.out.println(right);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
