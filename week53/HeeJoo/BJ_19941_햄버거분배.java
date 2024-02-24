package saturday.sat240224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * 자신의 위치에서 K 이하인 햄버거만 섭취가능
 * 식탁의 길이 N, 선택 거리 K, 사람과 햄버거의 위치가 주어졌을 때, 햄버거를 먹을 수 있는 최대 인원 수 구하기
 */
public class BJ_19941_햄버거분배 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        int[] arr = new int[N];
        String table = br.readLine();
        for(int i = 0; i < N; i++) {
            if(table.charAt(i) == 'H') { // 햄버거
                arr[i] = 1;
            } else { // 사람
                arr[i] = 0;
            }
        }

        int ans = 0;

        // 사람(0), 햄버거(1), 섭취된 햄버거(2)
        for(int i = 0; i < N; i++) {
            if(arr[i] == 0) { // 사람
                for(int j = i - K; j <= i + K; j++) { // 사거리 내에서 햄버거 먹기 ^.^
                    if(j < 0 || j >= N ) { // 배열 범위를 벗어나는 경우
                        continue;
                    }

                    if(arr[j] == 1) { // 햄버거인 경우
                        ans++;
                        arr[j] = 2; // 이미 섭취한 햄버거임을 표시
                        break;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
