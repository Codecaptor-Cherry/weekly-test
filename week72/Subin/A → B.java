import java.io.*;
import java.util.*;

public class Main {

    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        
        int a = sc.nextInt();
        int b = sc.nextInt();
        
        solve(a, b, 0);

        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer + 1);

        sc.close();
    }

    private static void solve(long a, long b, int i) {
        if (a > b) return;

        if (a == b) {
            answer = Math.min(answer, i);
            return;
        }

        solve(a * 2, b, i + 1);
        solve(a * 10 + 1, b, i + 1);
    }
}
