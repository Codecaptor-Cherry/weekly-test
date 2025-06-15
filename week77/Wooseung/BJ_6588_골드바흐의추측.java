import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static final int MAX = 1000000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] isPrime = new boolean[MAX + 1];

        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for(int i = 2; i <= MAX; i++) {
            for(int j = i * 2; j <= MAX; j += i) {
                if (!isPrime[j]) continue;
                isPrime[j] = false;
            }
        }

        while(true) {
            int N = Integer.parseInt(br.readLine());
            boolean flag = false;
            if(N == 0) break;
            for(int i = 2; i <= N / 2; i++) {
                if(isPrime[i] && isPrime[N - i]) {
                    System.out.println(N + " = " + i + " + " + (N - i));
                    flag = true;
                    break;
                }
            }
            if(!flag) System.out.println("Goldbach's conjecture is wrong.");
        }

    }

}
