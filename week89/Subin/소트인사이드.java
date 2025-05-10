import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();

        int[] count = new int[10];

        for (char c : input.toCharArray()) {
            ++count[c - '0'];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            while (count[i]-- > 0) {
                sb.append(i);
            }
        }

        System.out.println(sb);
    }
}

