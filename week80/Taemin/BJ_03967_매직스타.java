import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ_03967_매직스타 {
    static int[] hexagram = new int[12];
    static List<Integer> indices = new ArrayList<>();
    static int[] numbers;
    static int[][] patterns = {
            { 0, 2, 5, 7 },
            { 7, 8, 9, 10 },
            { 0, 3, 6, 10 },
            { 1, 5, 8, 11 },
            { 4, 6, 9, 11 },
            { 1, 2, 3, 4 }
    };
    static boolean find;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] isUsedNumber = new boolean[12];
        int numOfUsedNumber = 0;
        int index = 0;
        for (int i = 0; i < 5; i++) {
            char[] line = br.readLine().toCharArray();
            for (char element : line) {
                if (element != '.') {
                    if (element == 'x') indices.add(index);
                    else {
                        isUsedNumber[element - 65] = true;
                        numOfUsedNumber++;
                    }
                    hexagram[index++] = element - 64;
                }
            }
        }

        numbers = new int[12 - numOfUsedNumber];
        index = 0;
        for (int i = 0; i < 12; i++) {
            if (!isUsedNumber[i]) {
                numbers[index++] = (i + 1);
            }
        }

        find = false;
        backtracking(numbers.length, 0, 0, new int[numbers.length]);
        print();
    }

    private static char convertToChar(int number) {
        return (char)(number + 64);
    }

    private static void print() {
        StringBuilder builder = new StringBuilder();
        builder.append("....").append(convertToChar(hexagram[0])).append("....\n");
        builder.append(".").append(convertToChar(hexagram[1]))
                .append(".").append(convertToChar(hexagram[2]))
                .append(".").append(convertToChar(hexagram[3]))
                .append(".").append(convertToChar(hexagram[4])).append(".\n");
        builder.append("..").append(convertToChar(hexagram[5]))
                .append("...").append(convertToChar(hexagram[6])).append("..\n");
        builder.append(".").append(convertToChar(hexagram[7]))
                .append(".").append(convertToChar(hexagram[8]))
                .append(".").append(convertToChar(hexagram[9]))
                .append(".").append(convertToChar(hexagram[10])).append(".\n");
        builder.append("....").append(convertToChar(hexagram[11])).append("....\n");
        System.out.print(builder.toString());
    }

    private static void backtracking(int size, int depth, int visited, int[] result) {
        if (find) return;

        if (depth == size) {
            find = calculate(result);
            return;
        }

        for (int i = 0; i < size; i++) {
            if ((visited & (1 << i)) == 0) {
                result[depth] = numbers[i];
                backtracking(size, depth + 1, visited | (1 << i), result);
            }
        }
    }

    private static boolean calculate(int[] result) {
        int resultIndex = 0;
        for (int index : indices) {
            hexagram[index] = result[resultIndex++];
        }

        for (int[] pattern : patterns) {
            int total = hexagram[pattern[0]]
                        + hexagram[pattern[1]]
                        + hexagram[pattern[2]]
                        + hexagram[pattern[3]];

            if (total != 26) return false;
        }

        return true;
    }
}
