import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int ans = 0;
        for (int i = 0; i < 10; i++) {
            int n = sc.nextInt();
            if (Math.abs(ans - 100) >= Math.abs(ans + n - 100)) ans += n;
            else break;
        }

        System.out.println(ans);
    }
}
