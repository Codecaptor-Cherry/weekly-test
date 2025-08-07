package saturday.year24.sat240127;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 모든 길 0 ~ N을 밝히기 위한 가로등 설치
 * 설치 가로등 개수는 M개, 위치는 X들로 결정
 * 모든 가로등은 높이가 같고, 정수 ~ 각 가로등은 높이만큼 주위를 비출 수 있음
 * 최소한의 가로등 예산이 들 높이 구하기
 *
 * 다음 설치 위치까지의 거리 / 2
 */
public class BJ_17266_어두운굴다리 {
    public static void main(String[] args) throws IOException {
//        String input = "5\n" +
//                "2\n" +
//                "2 4"; // 2
//        BufferedReader br = new BufferedReader(new StringReader(input));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 굴다리 길이 N
        int M = Integer.parseInt(br.readLine()); // 총 설치 가로등 개수 M

        String[] sarr = br.readLine().split(" ");
        int[] arr = new int[M];
        for(int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(sarr[i]);
        }

        int ans = arr[0];
        // 홀수짝수 나눠야 제대로 구해짐
        // 예제 : 10 / 2 / 0 9 > 5 ~ 케이스 분리 안할 경우 4와 5 사이에 어두운 길 생김
        for(int i = 0; i < M - 1; i++) {
            if((arr[i+1] - arr[i]) % 2 == 0) {
                ans = Math.max(ans, (arr[i+1] - arr[i]) / 2);
            } else {
                ans = Math.max(ans, (arr[i+1] - arr[i]) / 2 + 1);
            }
        }

        ans = Math.max(ans, N - arr[M-1]);

        System.out.println(ans);
    }
}
