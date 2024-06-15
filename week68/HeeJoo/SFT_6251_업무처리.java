/*
 * 완전이진트리
 * 부서장 - 직원 - 말단
 * 조직도 트리 높이 H
 * 업무는 R일 동안 진행
 * 처음에는 말단만 각각 K개의 "순서"가 정해진 업무
 * 각 날짜에 남은 업무가 있는 경우, 말단은 하나의 업무를 처리해서 상사에게 제출
 * 다른 직원들도, 대기업무가 있는 경우 올라온 순서대로 하나 처리해서 상사에게 제출
 * 홀수날에는 왼쪽, 짝수날에는 오른쪽 부하가 올린 업무 처리
 * 제출한 업무는 다음날에 처리 가능
 * 완료된 업무 번호들의 합 구하기

 * 말단의 n번째 임무가 완수되는데 필요한 진행일 수 ~ 조직도 높이에 따라 다름
 * 오른쪽/왼쪽 나눠서 ...
 * 왼쪽 1 : 3 5 7
 * 오른쪽 1 : 2 4 6
 * 왼쪽 2 : 5 7 9
 * 오른쪽 2 : 4 6 8
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken()); // 조직도 높이
        int K = Integer.parseInt(st.nextToken()); // 대기 업무 개수
        int R = Integer.parseInt(st.nextToken()); // 진행 날짜 수

        // 대기 업무
        int N = (1 << (H + 1)) - 1; // 전체 노드 수
        int leaf = (1 << H) - 1; // 가장 왼쪽 말단 직원의 번호 ... 부서장(0)

        Queue<Integer>[][] task = new Queue[N][2]; // 각 노드의 왼쪽(0), 오른쪽(1) 업무
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < 2; j++) {
                task[i][j] = new ArrayDeque<>();
            }
        }

        for(int i = leaf; i <= leaf * 2; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < K; j++) {
                task[i][0].offer(Integer.parseInt(st.nextToken())); // 말단은 왼쪽에 몰아넣기
            }
        }

        // 구현
        int ans = 0;
        for(int day = 1; day <= R; day++) {
            int bit = (day + 1)% 2; // 왼쪽(0), 오른쪽(1)

            // 1. 부서장
            if(!task[0][bit].isEmpty()) { // 현재 처리할 수 있는 업무가 있는 경우
                ans += task[0][bit].poll();
            }
            
            // 2. 중간 직원 : 1 ~ leaf - 1
            for(int i = 1; i < leaf; i++) {
                int parent = (i - 1) / 2;

                if(!task[i][bit].isEmpty()) { // 현재 처리할 수 있는 업무가 있는 경우
                    // 왼쪽 부하직원(홀수)는 상사의 왼쪽 큐(0)에 들어가야 함
                    task[parent][(i + 1) % 2].offer(task[i][bit].poll());
                }
            }

            // 3. 말단 직원 : leaf ~ leaf * 2
            for(int i = leaf; i <= leaf * 2; i++) {
                int parent = (i - 1) / 2;

                if(!task[i][0].isEmpty()) { // 말단은 왼쪽 업무만 존재
                    // 왼쪽 부하직원(홀수)는 상사의 왼쪽 큐(0)에 들어가야 함
                    task[parent][(i + 1) % 2].offer(task[i][0].poll());
                }
            }
        }

        System.out.println(ans);
    }
}
