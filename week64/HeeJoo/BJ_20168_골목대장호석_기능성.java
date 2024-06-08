package saturday.sat240518;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * N개의 교차로(노드), M개의 골목(엣지)
 * 골목은 양방향이며, 두 교차로를 잇는 골목은 최대 한 개만 존재
 * 골목마다 수금 요금 존재
 * 수치심 : 경로의 최대 수금 금액
 * C원 이하로 가능한 경로들 중, 최대 수금 금액의 최솟값 구하기
 * 가능한 경로가 없는 경우 -1
 *
 * 1. 경로 출발
 * 2. 해당 경로의 최대 수금액 return
 * 3. 현재까지 금액과 비교해서 최솟값 선택
 */

class Node {
    int next, cost;

    Node(int next, int cost) {
        this.next = next;
        this.cost = cost;
    }
}
public class BJ_20168_골목대장호석_기능성 {
    static int ans;
    static ArrayList<Node>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 노드
        int M = Integer.parseInt(st.nextToken()); // 엣지
        int A = Integer.parseInt(st.nextToken()); // 출발 노드
        int B = Integer.parseInt(st.nextToken()); // 도착 노드
        int C = Integer.parseInt(st.nextToken()); // 소지금

        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list[x].add(new Node(y, cost));
            list[y].add(new Node(x, cost));
        }

        ans = 1001;
        boolean[] visited = new boolean[N + 1];
        move(A, B, 0, 0, C, visited);

        System.out.println(ans == 1001 ? -1 : ans);
    }

    public static void move(int start, int end, int maxCost, int sumCost, int limit, boolean[] visited) {
        // finish
        if(start == end) {
            ans = Math.min(ans, maxCost);
            return;
        }

        // 1. visited
        visited[start] = true;

        // 2. next route
        for(Node node : list[start]) {
            int next = node.next;
            int cost = node.cost;

            // 3-1. visited check
            if(visited[next]) {
                continue;
            }

            // 3-2. limit check
            if(sumCost + cost > limit) {
                continue;
            }

            // 4. move
            move(next, end, Math.max(maxCost, cost), sumCost + cost, limit, visited);
        }

        // 5. visited return
        visited[start] = false;
    }
}
