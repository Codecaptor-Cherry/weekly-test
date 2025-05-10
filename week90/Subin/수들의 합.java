import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long s = sc.nextLong();
        sc.close();

        long sum = 0;
        int n = 0;
        for (int i = 1; sum <= s; i++) {
            sum += i;
            ++n;
        }

        System.out.println(n - 1);
    }
}
