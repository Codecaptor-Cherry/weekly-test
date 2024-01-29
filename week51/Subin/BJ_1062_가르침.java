import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N, K, answer;
    private static String[] words;
    private static boolean[] knows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        // anta~tica -> a, c, i, n, t
        if (K < 5) {
            System.out.println(0);
            return;
        }

        initKnows();
        comb(0, 5);
        System.out.println(answer);
    }

    private static void initKnows() {
        knows = new boolean[26];
        final String init = "acint";
        for (char c : init.toCharArray()) {
            knows[c - 'a'] = true;
        }
    }

    private static void comb(int idx, int k) {
        if (k == K) {
            answer = Math.max(answer, getAnswer());
            return;
        }

        for (int i = idx; i < 26; i++) {
            if (knows[i]) continue;
            knows[i] = true;
            comb(i, k + 1);
            knows[i] = false;
        }
    }

    private static int getAnswer() {
        int count = 0;
        for (String word : words) {
            if (checkWord(word)) ++count;
        }
        return count;
    }

    private static boolean checkWord(String word) {
        for (char c : word.toCharArray()) {
            if (!knows[c - 'a']) return false;
        }
        return true;
    }

}
