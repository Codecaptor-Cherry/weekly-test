import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_02785_체인 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] chains = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt).toArray();

        Arrays.sort(chains);

        int numOfChain = n;
        int numOfRing = 0;
        int result = 0;
        for (int chain : chains) {
            if (chain >= numOfChain - 1) {
                // 만들어진 고리 + 필요한 고리 수
                result = numOfRing + numOfChain - 1;
                break;
            } else {
                // 필요한 고리 수가 줄어든다
                numOfChain -= (chain + 1);
                // 만들어진 고리 수 추가
                numOfRing += chain;
            }
        }

        System.out.print(result);
    }
}
