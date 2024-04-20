package saturday.sat240420;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N개의 강의
 * i번 강의와 j번 강의를 녹화하려면 i와 j 사이의 모든 강의를 순서대로 녹화해야 함
 * M개의 블루레이에 모든 동영상 녹화하기
 * 녹화 가능한 길이 최소로 하기 but M개의 블루레이는 모두 같은 크기
 * 가능한 블루레이의 크기 중 최소 구하기
 */
public class BJ_2343_기타레슨 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] input = new int[N];
        st = new StringTokenizer(br.readLine());
        int left = 0; // 최소 크기 = 가장 긴 영상의 길이 ~ M = N인 경우
        int right = 0; // 최대 크기 = 모든 영상 길이의 합 ~ M = 1인 경우
        for(int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left, input[i]);
            right += input[i];
        }

        // M개 이하면 right = mid - 1 ... 즉 M개여도 -1을 한다. 이러다가 M-1개가 되는 right가 되므로 답은 right + 1
        // left > right가 되는 순간은 right + 1이므로 최솟값
        while(left <= right) {
            int mid = (left + right) / 2; // 블루레이 크기

            int cnt = getCount(N, mid, input); // 해당 크기로 몇 개의 블루레이가 나오는지

            if(cnt > M) { // M개를 초과하면 크기를 늘려야 함
                left = mid + 1;
            } else { // M개 이하면 크기를 줄여야 함
                right = mid - 1;
            }
        }

        System.out.println(left);
    }

    public static int getCount(int N, int mid, int[] input) {
        int cnt = 0;

        int sum = 0;
        for(int i = 0; i < N; i++) {
            if(sum + input[i] > mid) { // 블루레이 크기를 넘어가면 새로운 블루레이 추가
                sum = 0;
                cnt++;
            }
            sum += input[i];
        }

        if(sum != 0) { // 마지막 영상이 남아있는 경우 블루레이 추가
            cnt++;
        }

        return cnt;
    }
}
