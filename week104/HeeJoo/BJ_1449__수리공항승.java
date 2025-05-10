package saturday.year25.sat250503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 가장 왼쪽에서 정수만큼 떨어진 거리만 물이 샘
 * 길이가 L인 테이프를 무한개 소유 중
 * 물을 막을 때, 적어도 그 위치의 좌우 0.5만큼 간격을 줘야 막을 수 있음
 * 파이프를 보수하는 데 필요한 테이프 최소 개수 구하기
 *
 * 1. 물이 새는 위치 오름차순 정렬
 * 2. 테이프로 커버할 수 있는 영역 체크
 */
public class BJ_1449_수리공항승 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = stoi(st.nextToken()); // 물이 새는 곳의 개수
        int L = stoi(st.nextToken()); // 테이프의 길이

        int[] pipe = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pipe[i] = stoi(st.nextToken());
        }

        Arrays.sort(pipe);

        int ans = 0;
        int end = 0;
        for (int i = 0; i < N; i++) {
            if(pipe[i] > end) {
                ans++;
                end = pipe[i] + L - 1;
            }
        }

        System.out.println(ans);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
