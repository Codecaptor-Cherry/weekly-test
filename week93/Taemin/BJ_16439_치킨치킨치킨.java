import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_16439_치킨치킨치킨 {

    static int maximumScore, numOfMember, numOfChickenType;
    static int[][] preferenceTable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        maximumScore = 0;
        numOfMember = Integer.parseInt(st.nextToken());
        numOfChickenType = Integer.parseInt(st.nextToken());
        preferenceTable = new int[numOfMember][numOfChickenType];

        for (int i = 0; i < numOfMember; i++) {
            preferenceTable[i] = Arrays.stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
        }

        int maxNumOfPickedChicken = 3;
        combination(0
                        , maxNumOfPickedChicken
                        , new int[maxNumOfPickedChicken]);

        System.out.println(maximumScore);
    }

    private static void combination(int index, int end, int[] visited) {
        if (index == end) {
            int localScore = calculateScore(end, visited);
            maximumScore = Math.max(maximumScore, localScore);
            return;
        }

        for (int i = 0; i < numOfChickenType; i++) {
            visited[index] = i;
            combination(index + 1, end, visited);
        }
    }

    private static int calculateScore(int end, int[] visited) {
        int totalScore = 0;
        for (int i = 0; i < numOfMember; i++) {
            int highestScore = 0;
            for (int j = 0; j < end; j++) {
                highestScore = Math.max(highestScore, preferenceTable[i][visited[j]]);
            }

            totalScore += highestScore;
        }

        return totalScore;
    }
}
