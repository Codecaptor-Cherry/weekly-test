package saturday.year24.sat241214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 일직선으로 놓인 N개의 캣닢
 * 각 화분은 초기에 K만큼의 수분을 머금고 있음
 * 매일 다음과 같은 일이 순서대로 일어남
 * 1. 연속된 A개의 화분에 물을 준다. 이 때 물을 준 화분의 수분은 B만큼씩 증가
 * 2. 모든 화분의 수분이 1씩 감소
 * 3. 수분이 0이 된 화분에 있는 캣닢은 사망
 * 모든 캣닢이 살아 있는 기간이 최대한 길어지도록 물을 줄 때, 첫 캣닢이 죽는 날짜 구하기
 */
public class BJ_23351_물주기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = stoi(st.nextToken()); // 화분 개수
        int K = stoi(st.nextToken()); // 초기 수분
        int A = stoi(st.nextToken()); // 물 주는 화분
        int B = stoi(st.nextToken()); // 수분량

        int[] arr = new int[N];
        Arrays.fill(arr, K);

        int ans = 1;
        int min = K;
        int idx = 0;
        while(min > 0) {
            // 1. 물주기
            for(int i = idx; i < idx + A; i++) {
                arr[i] += B;
            }

            idx = (idx + A) % N; // A는 N의 약수

            // 2. 1씩 감소
            min = dayPass(arr);

            // 3. 사망
            if(min == 0) {
                break;
            }

            ans++;
        }

        System.out.println(ans);

    }

    public static int dayPass(int[] arr) {
        int min = arr[0];
        for(int i = 0; i < arr.length; i++) {
            arr[i]--;

            min = Math.min(min, arr[i]);
        }

        return min;
    }


    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
