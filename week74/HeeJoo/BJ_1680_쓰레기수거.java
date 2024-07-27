package saturday.sat240727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 가까운 지점부터 방문하며, 다음과 같은 경우 쓰레기장으로 돌아가 쓰레기를 비움
 * 1. 쓰레기의 양이 용량에 도달했을 때
 * 2. 해당 지점의 쓰레기를 실었을 때 용량을 초과할 때
 * 3. 더 이상 쓰레기를 실을 지점이 없을 때
 * 모든 지점의 쓰레기를 수거하여 쓰레기장에 도달했을 때 끝
 * 특정 지점에서 쓰레기를 실을 때 한 번에 모두 실어야 함(일부만 싣기 불가능)
 * 모든 쓰레기들을 쓰레기장으로 수거했을 때의 총 이동 거리 구하기
 */
public class BJ_1680_쓰레기수거 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = stoi(br.readLine());

        StringTokenizer st;
        for(int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            int W = stoi(st.nextToken()); // 쓰레기차 용량
            int N = stoi(st.nextToken()); // 지점의 개수

            int[][] arr = new int[N][2];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                arr[i][0] = stoi(st.nextToken()); // 거리 ~ 오름차순 입력
                arr[i][1] = stoi(st.nextToken()); // 쓰레기 양
            }

            int ans = getWaste(W, N, arr);

            sb.append(ans + "\n");
        }

        System.out.println(sb);
    }

    public static int getWaste(int W, int N, int[][] arr) {
        int result = 0;
        int sum = 0;

        for(int i = 0; i < N; i++) {
            // 쓰레기의 양이 용량에 도달하는 경우 ~ 해당 지점까지 수거
            if(sum + arr[i][1] == W) {
                result += arr[i][0] * 2;
                sum = 0;
            }
            // 해당 지점의 쓰레기를 실으면 용량을 초과하는 경우 ~ 이전 지점까지만 수거
            else if(sum + arr[i][1] > W) {
                result += arr[i][0] * 2; // 쓰레기양 확인을 위해 이동거리는 arr[i][0]
                sum = arr[i][1]; // 해당 지점 재방문
            } else { // 쓰레기를 실어도 용량 이하인 경우 ~ 다음 지점으로 이동
                sum += arr[i][1];
            }
        }

        if(sum != 0) { // 마지막 지점까지 수거완료가 안된 경우
            result += arr[N - 1][0] * 2;
        }

        return result;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
