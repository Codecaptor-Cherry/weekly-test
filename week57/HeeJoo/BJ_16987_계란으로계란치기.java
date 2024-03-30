package saturday.sat240330;

/*
 * 각 계란에는 내구도와 무게가 정해져있음
 * 계란으로 계란을 치게 되면 각 계란의 내구도는 상대 계란의 무게만큼 감소
 * 내구도가 0 이하가 되는 순간 계란이 깨짐
 * 일렬로 놓여있는 계란에 대해 왼쪽부터 차례로 들어서 한 번씩만 다른 계란을 쳐 최대한 많은 계란 깨기
 * 1. 가장 왼쪽의 계란을 든다.
 * 2. 다른 계란 중 하나를 친다. 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 치지 않고 넘어간다.
 * 이후 손에 든 계란을 원래 자리에 내려놓고 3번 과정 진행
 * 3. 가장 최근에 든 계란의 한 칸 오른쪽 계란을 들고 2번 과정 재진행
 * 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우 과정 종료
 * 해당 과정을 진행했을 때, 최대 몇 개의 계란을 깰 수 있는지 ?
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16987_계란으로계란치기 {
    static int ans = 0;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] input = new int[N][2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }

        func(input, 0, 0);

        System.out.println(ans);
    }

    public static void func(int[][] input, int idx, int cnt) {
        if(idx == N) {
            ans = Math.max(ans, cnt);
            return;
        }

        // 현재 계란이 깨진 경우 : 한 칸 오른쪽으로 계란 교체
        if(input[idx][0] <= 0) {
            func(input, idx + 1, cnt);
            return;
        }

        // 순차대로 계란 깨보기
        boolean flag = false;
        for(int i = 0; i < N; i++) {
            if(i == idx || input[i][0] <= 0) { // 다른 계란이 아니거나 깰 수 없는 경우 pass
                continue;
            }

            flag = true;
            input[idx][0] -= input[i][1];
            input[i][0] -= input[idx][1];

            if(input[idx][0] > 0) {
                if(input[i][0] > 0) { // 둘 다 안 깨진 경우
                    func(input, idx + 1, cnt);
                } else { // 대상 계란만 깨진 경우
                    func(input, idx + 1, cnt + 1);
                }
            } else {
                if (input[i][0] > 0) { // 현재 계란만 깨진 경우
                    func(input, idx + 1, cnt + 1);
                } else { // 둘 다 깨진 경우
                    func(input, idx + 1, cnt + 2);
                }
            }

            // 원상복구
            input[idx][0] += input[i][1];
            input[i][0] += input[idx][1];
        }

        // 깰 계란이 없는 경우
        if(!flag) {
            func(input, idx + 1, cnt);
        }
    }
}
