import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_09082_지뢰찾기 {

    static int numOfTestCase, sizeOfArray, maxNumOfMines;
    static int[] numOfMines;
    static boolean[] mines;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        numOfTestCase = Integer.parseInt(reader.readLine());

        for (int t = 0; t < numOfTestCase; t++) {
            sizeOfArray = Integer.parseInt(reader.readLine());
            numOfMines = Arrays.stream(reader.readLine().split(""))
                               .mapToInt(Integer::parseInt)
                               .toArray();

            char[] blocks = reader.readLine().toCharArray();

            findMinesFrom(blocks);
            checkDefaultMines();
            findAllPossibleMines(-1);

            builder.append(maxNumOfMines).append("\n");
        }

        System.out.print(builder.toString());
    }

    private static int countMines() {
        int count = 0;
        for (boolean mine : mines) {
            if (mine) count++;
        }

        return count;
    }

    private static void findAllPossibleMines(int index) {
        if (index == sizeOfArray) {
            maxNumOfMines = Math.max(maxNumOfMines, countMines());
            return;
        }

        if (validateBlock(index - 1) && validateBlock(index)) {
            findAllPossibleMines(index + 1);
        }

        if (index < sizeOfArray - 1) {
            mines[index + 1] = true;
            if (validateBlock(index - 1) && validateBlock(index)) {
                findAllPossibleMines(index + 1);
            }
            mines[index + 1] = false;
        }
    }

    private static boolean validateBlock(int index) {
        if (index <= 0 || index >= sizeOfArray) return true;

        int numOfMine = numOfMines[index];
        int count = 0;

        if (index - 1 >= 0 && mines[index - 1]) count++;
        if (mines[index]) count++;
        if (index + 1 < sizeOfArray && mines[index + 1]) count++;

        return numOfMine == count;
    }

    private static void findMinesFrom(char[] blocks) {
        mines = new boolean[sizeOfArray];
        char mine = '*';
        int index = 0;
        int count = 0;

        for (char block : blocks) {
            if (block == mine) {
                mines[index] = true;
                count++;
            }
            index++;
        }

        maxNumOfMines = count;
    }

    private static void checkDefaultMines() {

        if (numOfMines[0] == 2) {
            mines[0] = mines[1] = true;
        }

        for (int i = 1; i < sizeOfArray - 1; i++) {
            int numOfMine = numOfMines[i];
            if (numOfMine == 3) {
                mines[i - 1] = mines[i] = mines[i + 1] = true;
            }
        }

        if (numOfMines[sizeOfArray - 1] == 2) {
            mines[sizeOfArray - 1] = mines[sizeOfArray - 2] = true;
        }
    }
}
