package saturday.sat240824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * Q-인덱스는 가장 많이 인용된 논문들의 개수와 그 논문들의 인용횟수를 이용하여 다음과 같이 정의된다.
 * 한 학생이 발표한 총 n >= 1편의 논문 중에서, k번 이상 인용된 논문이 k편 이상이고
 * 나머지 n - k편의 논문들 인용횟수가 각각 k번 이하라면, 해당 학생의 Q-인덱스는 k이다.
 *
 * k+1점 이상인 논문들은 많아야 k편이라는 거이므로,  q인덱스는 k점이상의 논문이 k편 이상인 k들 중 최댓값이 됩니다.
 */
public class BJ_13333_Q인덱스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = stoi(br.readLine());

        String[] inputs = br.readLine().split(" ");
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = stoi(inputs[i]);
        }

        Arrays.sort(arr);

        for(int k = 0; k <= n; k++) {
            int up = 0, mid = 0, down = 0;

            for(int i : arr) {
                if(i > k) {
                    up++;
                } else if(i < k) {
                    down++;
                } else {
                    mid++;
                }
            }

            if(up >= k) {
                if(n - k == mid + down) {
                    System.out.println(k);
                    break;
                }
            } else {
                int gap = k - up;

                if(gap <= mid) {
                    up += gap;
                    mid -=gap;
                } else {
                    break;
                }

                if(n - k == mid + down) {
                    System.out.println(k);
                    break;
                }
            }
        }

//        String[] inputs = br.readLine().split(" ");
//        int[] arr = new int[n + 1];
//        for(int i = 1; i <= n; i++) {
//            arr[i] = stoi(inputs[i - 1]);
//        }
//
//        Arrays.sort(arr);

//        int k = n;
//        for(k = n; k > 0; k--) {
//        // arr[n - k + 1] >= k : (n - k + 1)번 ~ n번 = k편
//        // arr[n - k] <= k : 1번 ~ (n - k)번 = n - k편. 오름차순이므로 n - k편의 논문들은 모두 인용 횟수 k번 이하
//            if(arr[n - k + 1] >= k && arr[n - k] <= k) {
//                break; // 여기서 출력을 하면 k = 0인 경우가 출력 xxx
//            }
//        }
//
//        System.out.println(k);

    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
