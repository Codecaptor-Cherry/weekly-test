package saturday.year24.sat241214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 2 x N 배열에 숨겨진 지뢰 찾기
 * 지뢰 주위에 쓰여 있는 숫자들로 지뢰 찾기 가능
 * 한 블록에 쓰여진 숫자는 그 블록 주위에 몇 개의 지뢰가 존재하는지를 나타냄
 * * : 지뢰가 확실한 위치
 * # : 숨겨진 블록
 * 주어진 배열에 있는 모든 지뢰의 개수 찾기
 */
public class BJ_9082_지뢰찾기 {
    static int N, ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = stoi(br.readLine()); // 테스트 케이스 개수

        while(T-- > 0) {
            ans = 0;
            N = stoi(br.readLine()); // 배열의 크기

            int[] first = new int[N];
            char[] second = new char[N];

            String finput = br.readLine();
            String sinput = br.readLine();
            for(int i = 0; i < N; i++) {
                first[i] = finput.charAt(i) - '0';
                second[i] = sinput.charAt(i);
            }

            // --------------------- 입력 종료
            // 주변에 지뢰가 0개인 곳이 존재하면 해당 위치에 지뢰 존재 불가능
            // 주변에 지뢰가 0개인 곳이 존재하지 않으면 해당 위치에 지뢰 존재 가능
            // 지뢰가 존재 가능한 경우, 지뢰 설치를 가정하고 ans++

            // 첫 부분
            if(first[0] != 0 && first[1] != 0) {
                first[0]--;
                first[1]--;
                ans++;
            }

            // 중간 부분
            for(int i = 1; i < N - 1; i++) {
                if(first[i - 1] != 0 && first[i] != 0 && first[i + 1] != 0) {
                    first[i - 1]--;
                    first[i]--;
                    first[i + 1]--;
                    ans++;
                }
            }

            // 마지막 부분
            if(first[N - 2] != 0 && first[N - 1] != 0) {
                first[N - 2]--;
                first[N - 1]--;
                ans++;
            }

            sb.append(ans + "\n");
        }

        System.out.println(sb);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
