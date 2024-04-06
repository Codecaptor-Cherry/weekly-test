import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static class Word {
        private int[] alpha;

        public Word(String word) {
            alpha = new int[26];
            for (int i = 0; i < word.length(); i++) {
                ++alpha[word.charAt(i) - 'A'];
            }
        }

        public int getAlphabetCount(int i) {
            return alpha[i];
        }

        public boolean isSimilarTo(Word word) {
            int diff = 0;
            int diffAbs = 0;
            for (int i = 0; i < 26; i++) {
                diff += alpha[i] - word.getAlphabetCount(i);
                diffAbs += Math.abs(alpha[i] - word.getAlphabetCount(i));
                if (diffAbs > 2) return false;
            }
            return Math.abs(diff) < 2;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Word[] words = new Word[n];
        for (int i = 0; i < n; i++) {
            words[i] = new Word(br.readLine());
        }

        int similarCount = 0;
        for (int i = 1; i < n; i++) {
            if (words[0].isSimilarTo(words[i])) ++similarCount;
        }
        System.out.println(similarCount);
    }

}
