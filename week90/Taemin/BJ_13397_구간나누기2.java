import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_13397_구간나누기2 {

    static int numOfNumber, sizeOfGroup, subMin, subMax;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        numOfNumber = Integer.parseInt(tokenizer.nextToken());
        sizeOfGroup = Integer.parseInt(tokenizer.nextToken());

        numbers = Arrays.stream(reader.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        int left = 0;
        int right = Integer.MIN_VALUE;
        for (int number : numbers) {
            right = Math.max(right, number);
        }

        while (left < right) {
            int mid = (left + right) / 2;
            if (isGroupMadeLessThanLimit(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.print(right);
    }

    private static boolean isGroupMadeLessThanLimit(int mid) {
        int count = 1;
        initSubminAndSubmax();

        for (int i = 0; i < numOfNumber; i++) {
            subMin = Math.min(subMin, numbers[i]);
            subMax = Math.max(subMax, numbers[i]);

            if (subMax - subMin > mid) {
                initSubminAndSubmax();
                count++;
                i--;
            }
        }

        return count <= sizeOfGroup;
    }

    private static void initSubminAndSubmax() {
        subMin = Integer.MAX_VALUE;
        subMax = Integer.MIN_VALUE;
    }
}
