import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_13022_늑대와올바른단어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] letters = br.readLine().toCharArray();
        int length = letters.length;
        int start = 0;
        int result = 1;


        while (true) {
            if (start >= length) break;

            if (!isFirstLetterOfWolf(letters, start)) {
                result = 0;
                break;
            }

            int end = findEndIndexOfWolfFrom(letters, start);
            if (end == -1) {
                result = 0;
                break;
            }

            if (!isCorrectWolfWord(letters, start, end)) {
                result = 0;
                break;
            }

            start = end + 1;
        }

        if (letters.length == 0) result = 0;
        System.out.print(result);
    }

    private static boolean isFirstLetterOfWolf(char[] letters, int start) {
        return letters[start] == 'w';
    }

    private static boolean isCorrectWolfWord(char[] letters, int start, int end) {
        int[] countLetters = new int[4];
        boolean result = true;
        int indexOfLetter = -1;

        for (int i = start; i <= end; i++) {
            char letter = letters[i];

            if (letter == 'w') {
                if (indexOfLetter == -1 || indexOfLetter == 0) {
                    indexOfLetter = 0;
                    countLetters[indexOfLetter]++;
                } else {
                    result = false;
                    break;
                }
            }
            else if (letter == 'o') {
                if (indexOfLetter == 0 || indexOfLetter == 1) {
                    indexOfLetter = 1;
                    countLetters[indexOfLetter]++;
                } else {
                    result = false;
                    break;
                }
            }
            else if (letter == 'l') {
                if (indexOfLetter == 1 || indexOfLetter == 2) {
                    indexOfLetter = 2;
                    countLetters[indexOfLetter]++;
                } else {
                    result = false;
                    break;
                }
            }
            else if (letter == 'f') {
                if (indexOfLetter == 2 || indexOfLetter == 3) {
                    indexOfLetter = 3;
                    countLetters[indexOfLetter]++;
                } else {
                    result = false;
                    break;
                }
            }
            else {
                result = false;
                break;
            }
        }

        if (result) {
            int count = countLetters[0];
            for (int i = 0; i < countLetters.length; i++) {
                if (count != countLetters[i]) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    private static int findEndIndexOfWolfFrom(char[] letters, int start) {
        boolean isF = false;
        int index = -1;
        for (int i = start; i < letters.length; i++) {
            if (letters[i] == 'f') {
                isF = true;
                index = i;
            }

            if (letters[i] != 'f' && isF) {
                break;
            }
        }

        return index;
    }
}
