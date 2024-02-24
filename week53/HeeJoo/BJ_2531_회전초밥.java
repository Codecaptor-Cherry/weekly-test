package saturday.sat240224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 회전 벨트 위 여러 가지 종류의 초밥(번호 중복 가능)
 * 행사 1. 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공
 * 행사 2. 각 고객에게 초밥의 종류 하나가 쓰인 쿠폰 발행 후, 행사 1에 참가할 경우 쿠폰에 적혀진 종류의 초밥 하나 무료 제공
 * 쿠폰에 적혀진 초밥이 벨트 위에 없을 경우, 새로 만들어 제공
 * 벨트 상태, 메뉴에 있는 초밥의 가짓수, 연속해서 먹는 개수, 쿠폰 번호가 주어질 때, 손님이 먹을 수 있는 초밥 가짓수의 최댓값 구하기
 */
public class BJ_2531_회전초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 벨트 길이 N
        int d = Integer.parseInt(input[1]); // 초밥 가짓수 d
        int k = Integer.parseInt(input[2]); // 연속 접시 개수 k
        int c = Integer.parseInt(input[3]); // 쿠폰 번호 c

        int[] belt = new int[N];
        for(int i = 0; i < N; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        int ans = 0;
        int tmp = 0;
        int[] menu = new int[d + 1]; // 1번부터 d번까지 메뉴 존재
        for(int i = 0; i < k; i++) { // 슬라이딩 윈도우
            if(menu[belt[i]] == 0) { // belt[i]번 초밥 섭취를 안 한 경우
                tmp++; // 종류++
            }
            menu[belt[i]]++; // belt[i]번 초밥 섭취 여부++
        }

        ans = menu[c] == 0 ? tmp + 1 : tmp; // 쿠폰 번호에 해당하는 초밥을 먹지 않은 경우 tmp + 1
        for(int i = 1; i < N; i++) {
            int left = belt[i - 1]; // 제외할 이전 벨트
            int right = belt[i + k - 1 < N ? i + k - 1 : i + k - 1 - N]; // 추가할 이후 벨트 ~ 벨트는 순환이니까 범위를 벗어나는 경우 처리

            if(menu[left] == 1) { // 제외되는 초밥이 슬라이딩 윈도우에서 중복x
                tmp--;
            }
            menu[left]--;

            if(menu[right] == 0) { // 추가되는 초밥이 슬라이딩 윈도우에서 중복x
                tmp++;
            }
            menu[right]++;

            ans = Math.max(ans, menu[c] == 0 ? tmp + 1 : tmp); // 슬라이딩 윈도우 쿠폰 초밥 포함 여부
        }

        System.out.println(ans);
    }
}
