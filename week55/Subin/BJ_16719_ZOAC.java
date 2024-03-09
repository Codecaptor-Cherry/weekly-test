import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_16719_ZOAC {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        solve(s, 0, s.length(), new boolean[s.length()]);
    }

    private static void solve(String s, int start, int end, boolean[] chk) {
        if (start >= end) return;
        int idx = findMinAlphaIdx(s, start, end);
        chk[idx] = true;
        print(s, chk);

        solve(s, idx + 1, end, chk);
        solve(s, start, idx, chk);
    }

    private static void print(String s, boolean[] chk) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (chk[i]) sb.append(s.charAt(i));
        }
        System.out.println(sb);
    }

    private static int findMinAlphaIdx(String s, int start, int end) {
        int idx = -1;
        char min = Character.MAX_VALUE;
        for (int i = start; i < end; i++) {
            if (s.charAt(i) < min) {
                min = s.charAt(i);
                idx = i;
            }
        }
        return idx;
    }

}
