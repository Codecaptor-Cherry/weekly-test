package saturday.sat240810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1 ~ N번의 사람 존재
 * 매분 루머를 믿는 사람은 모든 주변인에게 루머를 동시에 퍼뜨리며,
 * 군중 속 사람은 주변인의 절반 이상이 루머를 믿을 때 본인도 루머를 믿음
 * 사람들이 루머를 처음 믿기 시작하는 시간 구하기
 * 0분부터 시작되며 충분히 많은 시간이 지나도 루머를 믿지 않으면 return -1
 *
 * 그래프 이론
 * 풀이 1 : 구현 ~ 시간 초과
 * 1. 주변의 절반 이상이 루머를 믿는지 확인
 * 2. 해당 경우 선택된 사람도 루머 신봉자로 바뀔 예정 ~ 해당 시간 저장 -> 0 이상이면 루머 신봉자
 * 3. 언제까지 ? 상태 변화가 없으면 종료 ... flag 필요
 *
 * 풀이 2 : BFS ~ 초과는 안나지만 오래 걸림
 * 직전에 주변인 상태 변화가 없으면 굳이 따질 필요 없음 -> BFS 적용
 *
 * 풀이 3 : BFS + 논리 구현
 * 루머를 전파시킬 이웃에게 현재 사람이 루머를 믿고 있음을 알려주기
 * neighbor이라는 배열에 나와 연결된 사람에게 +1
 * neighbor을 통해 현재 시점 본인 주위에 루머를 믿고 있는 사람의 수를 바로 알 수 있음
 */
public class BJ_19538_루머 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = stoi(br.readLine()); // 사람의 수 N

        // 그래프 입력
        ArrayList<Integer>[] graph = new ArrayList[N];
        String[] inputs;
        for(int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();

            inputs = br.readLine().split(" ");
            for(String input : inputs) {
                if(input.equals("0")) { // 입력의 마지막 표시(0)
                    break;
                }

                graph[i].add(stoi(input) - 1);
            }
        }

        int M = stoi(br.readLine()); // 최초 유포자 수 M
        int[] table = new int[N]; // 루머를 믿기 시작한 시간 테이블
        Arrays.fill(table, -1); // 초기 상태는 모두 믿지 않음
        inputs = br.readLine().split(" ");
        
        Queue<Integer> queue = new ArrayDeque<>();
        for(String input : inputs) {
            int idx = stoi(input) - 1;
            table[idx] = 0;
            
            queue.offer(idx); // 최초 유포자
        }

        // ---------- 입력 끝

//        rumor(graph, table); // 풀이 1, 2

        // 풀이 3
        int[] neighbor = new int[N];
        rumor(queue, graph, table, neighbor);

        StringBuilder sb = new StringBuilder();
        for(int k : table) {
            sb.append(k + " ");
        }

        System.out.println(sb);
    }

    public static void rumor(Queue<Integer> queue, ArrayList<Integer>[] graph, int[] table, int[] neighbor) {
        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(int next : graph[now]) { // 주변인 idx
                neighbor[next]++; // 해당 주변인의 이웃 중 루머를 믿고 있는 이웃 수 +1

                // 1. 현재 시점 불신자일때만 갱신 가능
                // 2. 주변 이웃 중 루머 신봉자가 과반수 이상이여야 갱신 가능
                if(table[next] == -1 && (graph[next].size() + 1) / 2 <= neighbor[next]) {
                    queue.offer(next); // 다음 탐색
                    table[next] = table[now] + 1; // 시간 갱신
                }

            }

        }
    }

//    public static void rumor(ArrayList<Integer>[] graph, int[] table) {
//        Queue<int[]> queue = new ArrayDeque<>();
//
//        for(int idx = 0; idx < N; idx++) {
//            if(table[idx] == 0) { // 최초 유포자
//                queue.offer(new int[]{idx, 1});
//            }
//        }
//
//        while(!queue.isEmpty()) {
//            int[] now = queue.poll();
//            int idx = now[0];
//            int time = now[1];
//
//            for(int next : graph[idx]) { // 주변인 번호
//                if(table[next] >= 0) { // 이미 신뢰자인 경우 pass
//                    continue;
//                }
//
//                int cnt = 0;
//                for(int around : graph[next]) { // 선택된 번호의 주변인을 다시 살펴봄
//                    if(table[around] == -1) { // 불신자
//                        continue;
//                    }
//
//                    if(table[around] < time) { // 주변인이 최초로 루머를 믿게 된 시점이 현재보다 먼저인 경우
//                        cnt++;
//                    }
//                }
//
//                // 과반수 이상이면 시간 기록
//                if(checkCount(cnt, graph[next].size())) {
//                    table[next] = time;
//                    queue.offer(new int[]{next, time + 1}); // 다음 탐색 번호로 저장
//                }
//            }
//
////            System.out.println(time + " : " + Arrays.toString(table));
//        }
//    }

    public static boolean checkCount(int cnt, int size) {
        return size - cnt <= cnt ? true : false;
    }

    // 구현 풀이 ~ 시간 초과
//    public static boolean rumor(int time, ArrayList<Integer>[] graph, int[] table) {
//        boolean flag = false;
//
//        for(int idx = 0; idx < N; idx++) {
//            if(table[idx] < 0) { // 아직 불신자인 경우에만 확인
//                // 주변인의 상태 확인
//                int cnt = 0;
//                for(int around : graph[idx]) { // 주변인 번호
//                    if(table[around] == -1) {
//                        continue;
//                    }
//
//                    if(table[around] < time) { // 주변인이 최초로 루머를 믿게 된 시점이 현재보다 먼저인 경우
//                        cnt++;
//                    }
//                }
//
//                // 과반수 이상이면 시간 기록
//                if(cnt == 0) {
//                    continue;
//                }
//
//                if(checkCount(cnt, graph[idx].size())) {
//                    table[idx] = time;
//                    flag = true; // 플래그 변경
//                }
//            }
//        }
//
//        return flag;
//    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }

}
