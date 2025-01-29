import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ_26650_그램팬 {

    static class Alphabet {
        char alphabet;
        int count;

        public Alphabet(char alphabet, int count) {
            this.alphabet = alphabet;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        char[] inputCharArray = br.readLine().toCharArray();
        long numOfGrampan = 0;

        // Compress Input
        List<Alphabet> compressedInput = new ArrayList<Alphabet>();
        for (int i = 0; i < inputCharArray.length; i++) {
            char alphabet = inputCharArray[i];
            int count = 0;

            for (int j = i; j < inputCharArray.length; j++) {
                if (alphabet == inputCharArray[j]) count++;
                else break;
            }

            compressedInput.add(new Alphabet(alphabet, count));
            i += (count - 1);
        }

        // Find Grampan
        if (compressedInput.size() >= 26) {
            int startIndex = 0;
            int size = compressedInput.size();
            while (startIndex < size) {
                // Find A
                while (startIndex < size &&
                       compressedInput.get(startIndex).alphabet != 'A') {
                    startIndex++;
                }

                if (startIndex >= size) break;
                long countOfA = compressedInput.get(startIndex).count;

                // Find B ~ Y
                int target = 66;    // 'B(66)' ~ 'Y(89)'
                int end = 0;
                for (int i = startIndex + 1; i < size; i++) {
                    if (compressedInput.get(i).alphabet == (char)target) {
                        target++;

                        if (target == 90) {
                            end = i;
                            break;
                        }
                    } else break;
                }

                // Find Z
                if (target == 90 && 
                    end + 1 < size && 
                    compressedInput.get(end + 1).alphabet == 'Z') 
                {
                    long countOfZ = compressedInput.get(end + 1).count;
                    numOfGrampan += (countOfA * countOfZ);
                }

                // Move start index
                startIndex++;
            }
        }

        builder.append(numOfGrampan);
        System.out.print(builder.toString());
    }
}
