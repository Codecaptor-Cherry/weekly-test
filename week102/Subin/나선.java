import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        int r = sc.nextInt();

        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int dir = 0;

        int totalR = r, totalC = c + 1;
        int curR = 0, curC = -1;

        int total = r * c;
        int steps = 0;

        while (steps < total) {
            int move = (dir % 2) == 0 ? totalC - 1 : totalR - 1;

            curR += dr[dir] * move;
            curC += dc[dir] * move;
            steps += move;

            if (dir % 2 == 0) totalC--;
            else totalR--;

            dir = (dir + 1) % 4;
        }

        System.out.println(curC + " " + curR);
    }
}
