import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_19941_햄버거분배 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        char[] input = br.readLine().toCharArray();

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (input[i] == 'P') {
                int idx = findHamburgerIndex(input, i, k);
                if (idx != -1) {
                    input[idx] = 'X';
                    ++answer;
                }
            }
        }

        System.out.println(answer);
    }

    private static int findHamburgerIndex(char[] input, int p, int k) {
        int idx = -1;
        // 먹을 수 있는 햄버거 중 가장 왼쪽의 햄버거 선택하기
        for (int i = p - k; i <= p + k; i++) {
            if (i < 0 || i >= input.length) continue;
            if (input[i] == 'H') {
                idx = i;
                break;
            }
        }
        return idx;
    }


}
