import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();
        if (s.length() < 4) {
            System.out.println(0);
            return;
        }

        int w = 0, o = 0, l = 0, f = 0;
        int idx = 0;
        while (idx < s.length()) {
            while (idx < s.length() && s.charAt(idx) == 'w') {
                ++w;
                ++idx;
            }

            while (idx < s.length() && s.charAt(idx) == 'o') {
                ++o;
                ++idx;
            }

            while (idx < s.length() && s.charAt(idx) == 'l') {
                ++l;
                ++idx;
            }

            while (idx < s.length() && s.charAt(idx) == 'f') {
                ++f;
                ++idx;
            }

            if (w != o || o != l || l != f) {
                System.out.println(0);
                return;
            }
        }

        System.out.println(1);
    }
}

