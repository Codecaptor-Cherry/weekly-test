import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_23351_물주기 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(reader.readLine().split(" "))
                             .mapToInt(Integer::parseInt)
                             .toArray();

        int numOfPot = inputs[0];
        int initWater = inputs[1];
        int numOfWateringPot = inputs[2];
        int moisture = inputs[3];
        int requiredTurnForRotate = numOfPot / numOfWateringPot;
        int currentWater = initWater;
        int result = 0;
        int numOfRotate = 0;

        while (true) {
            if (currentWater < requiredTurnForRotate) {
                result = numOfRotate * requiredTurnForRotate + currentWater;
                break;
            }

            currentWater += (moisture - requiredTurnForRotate);
            numOfRotate++;
        }

        System.out.print(result);
    }
}
