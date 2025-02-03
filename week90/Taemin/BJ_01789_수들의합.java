import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_01789_수들의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long input = Long.parseLong(reader.readLine());
        long count = 0;
        long total = 0;

        while (true) {
            count++;
            total += count;
            if (total > input) {
                count--;
                break;
            }
        }

        System.out.print(count);
    }
}
