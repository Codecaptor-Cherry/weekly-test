package saturday.year25.sat250222;

/*
 * N층의 아파트
 * 1. 게임의 모든 참가자는 두 손을 탑에 쌓음
 * 2. 가장 아래에 있는 손 부터 위로 이동
 * 3. 2번 과정 N번 반복 후 j번째로 쌓은 손이 j층
 * 4. N층을 쌓는 참가자가 패배
 * 누가 패배자인지 구하기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Hand {
    int idx, level;

    Hand(int idx, int level) {
        this.idx = idx;
        this.level = level;
    }
}
public class BJ_31797_아파트아파트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = stoi(st.nextToken()); // 아파트 층수
        int M = stoi(st.nextToken()); // 참가자 수

        PriorityQueue<Hand> pq = new PriorityQueue<>(((o1, o2) -> o1.level - o2.level));
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());


            pq.offer(new Hand(i + 1, a));
            pq.offer(new Hand(i + 1, b));
        }

        Queue<Hand> queue = new ArrayDeque<>();
        while(!pq.isEmpty()) {
            queue.offer(pq.poll());
        }

        for(int i = 0; i < N - 1; i++) {
            Hand h = queue.poll();
//            System.out.printf("%d, %d\n", h.idx, h.level);
            queue.offer(h);
        }

        System.out.println(queue.poll().idx);
    }


    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
