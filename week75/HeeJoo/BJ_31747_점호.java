package saturday.sat240803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * N명의 1학년과 2학년 학생
 * p명이 점호 줄에 서 있을 때, 점호의 규칙은 다음과 같음
 * 줄의 가장 앞에 있는 min(K, p)명 중 1학년이 있으면 가장 앞에 있는 1학년이 점호를 하고 줄에서 빠지고,
 * 2학년이 있으면 가장 앞에 있는 2학년이 점호를 하고 줄에서 빠짐
 * 1학년과 2학년이 둘 다 있으면, 동시에 줄에서 빠짐
 * N명이 모두 점호를 끝내기까지 걸리는 시간 구하기
 * 
 * 앞부분 K명에 대해서만 판단
 * 입력순서 상관없이 앞부분 K명에 대해 1학년, 2학년의 유무만 알면 됨
 * Deque를 이용하여 1학년은 좌측, 2학년은 우측에 삽입해서 유무 판단 + 제거
 */
public class BJ_31747_점호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = stoi(st.nextToken());
        int K = stoi(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Queue<Integer> queue = new ArrayDeque<>();
        while(N-- > 0) {
            queue.offer(stoi(st.nextToken()));
        }

        Deque<Integer> deque = new ArrayDeque<>(); // K명 확인용 deque
        int ans = 0;

        while(!queue.isEmpty()) {
            while(deque.size() < K) { // K명 채우기
                if(queue.size() == 0) { // 더 이상 학생 x
                    break;
                }

                if(queue.peek() == 1) { // 1학년이면 좌측
                    deque.addFirst(queue.poll());
                } else { // 2학년이면 우측
                    deque.addLast(queue.poll());
                }
            }

            if(deque.peekFirst() == deque.peekLast()) { // 좌측과 우측값이 같으면 같은 학년 ~ 1명만 점호
                deque.poll();
            } else { // 서로 다른 학년이 존재하면 둘 다 점호
                deque.pollFirst();
                deque.pollLast();
            }

            ans++;
        }

        while(!deque.isEmpty()) { // 아직 점호를 하지 못한 학생이 남아있는 경우 동일하게 동작
            if(deque.peekFirst() == deque.peekLast()) { // 좌측과 우측값이 같으면 같은 학년 ~ 1명만 점호
                deque.poll();
            } else { // 서로 다른 학년이 존재하면 둘 다 점호
                deque.pollFirst();
                deque.pollLast();
            }

            ans++;
        }

        System.out.println(ans);

    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
