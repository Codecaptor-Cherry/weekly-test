import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        char[] answer = new char[n];
        boolean[] chk = new boolean[26]; // 알파벳 등장 체크
        Arrays.fill(answer, '?');

        int idx = 0;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            idx = (idx + n - (s % n)) % n;

            if ((answer[idx] != '?' && answer[idx] != c) ||
                    (answer[idx] == '?' && chk[c - 'A'])) {
                System.out.println("!");
                return;
            }

            answer[idx] = c;
            chk[c - 'A'] = true;
        }

        System.out.print(String.valueOf(Arrays.copyOfRange(answer, idx, n)));
        System.out.println(String.valueOf(Arrays.copyOfRange(answer, 0, idx)));
    }
}
