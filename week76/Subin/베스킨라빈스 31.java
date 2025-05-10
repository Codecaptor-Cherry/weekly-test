import java.io.*;
import java.util.*;

public class Main {

    /*
        베스킨라빈스31 게임에서 승리하려면 30을 불러야 함.
        30을 부르기 위해서는 26을 불러야 함.
        26을 부르기 위해서는 22를 불러야 함.
        이렇게 거슬러 올라가면 무조건 2를 불러야 승리할 수 있음.

        즉, 30, 30 - (3 + 1), 30 - 2 * (3 + 1), ..., 30 % (3 + 1) 를 내가 불러야 함.

        최대로 부를 수 있는 숫자를 N으로 둘 때,
        30 % (N + 1) = 0 이라면 선공이 부를 수 없는 숫자임 (선공이 숫자를 0개 불러야 함)
        30 % (N + 1) != 0 이라면 선공이 부를 수 있는 숫자임.
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();

        for (int i = 1; i <= a; i++) {
            if (30 % (i + 1) == 0) System.out.println(i);
        }
    }
}
