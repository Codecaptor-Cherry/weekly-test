import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    static HashSet<Character> key = new HashSet<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            System.out.println(setKey(br.readLine()));
        }
    }

    private static String setKey(String option) {
        // 1. 먼저 하나의 옵션에 대해 왼쪽에서부터 오른쪽 순서로 단어의 첫 글자가 이미 단축키로 지정되었는지 살펴본다.
        //    만약 단축키로 아직 지정이 안 되어있다면 그 알파벳을 단축키로 지정한다.
        boolean first = true;
        for (int i = 0; i < option.length(); i++) {
            if (option.charAt(i) == ' ') {
                first = true;
            } else {
                char c = Character.toLowerCase(option.charAt(i));
                if (first && !key.contains(c)) {
                    key.add(c);
                    return newOption(option, i);
                }
                first = false;
            }
        }

        // 2. 만약 모든 단어의 첫 글자가 이미 지정이 되어있다면 왼쪽에서부터 차례대로 알파벳을 보면서 단축키로 지정 안 된 것이 있다면 단축키로 지정한다.
        for (int i = 0; i < option.length(); i++) {
            char c = Character.toLowerCase(option.charAt(i));
            if (c == ' ') continue;

            if (!key.contains(c)) {
                key.add(c);
                return newOption(option, i);
            }
        }

        return option;
    }

    private static String newOption(String option, int i) {
        return option.substring(0, i) + "[" + option.substring(i, i + 1) + "]" + option.substring(i + 1);
    }
}

