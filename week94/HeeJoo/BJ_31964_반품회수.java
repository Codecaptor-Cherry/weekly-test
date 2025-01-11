package saturday.sat250111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1 ~ N번이 붙어있는 N개의 집
 * 한 대의 트럭을 이용해 N개의 집을 방문하면서 반품되는 물건 회수
 * i번 집은 T_i에 반품할 물건을 내놓음
 * 트럭은 d만큼의 거리를 이동하는데 d시간 소요... 제자리에 멈춰서 대기 가능
 * 모든 물건을 회수해서 다시 회사로 돌아오는 데 걸리는 시간의 최솟값 구하기
 */
public class BJ_31964_반품회수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine());

        int[] houses = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            houses[i] = stoi(st.nextToken());
        }

        int[] times = new int[N];
        st= new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            times[i] = stoi(st.nextToken());
        }

        // -------------- 입력 종료 --------------
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int min = houses[N - 1] * 2 - houses[i];
            if(times[i] > min) {
                ans = Math.max(ans, times[i] - min);
            }
        }

        System.out.println(ans + houses[N - 1] * 2);

    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
