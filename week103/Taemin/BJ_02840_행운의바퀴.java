import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_02840_행운의바퀴 {

    static int numOfSpace, numOfRotate;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        numOfSpace = Integer.parseInt(st.nextToken());
        numOfRotate = Integer.parseInt(st.nextToken());

        char[] wheel = new char[numOfSpace];
        int accumulated = 0;
        int index = 0;
        boolean checkWheel = true;
        boolean[] checkAlpha = new boolean[26];

        for (int i = 0; i < numOfRotate && checkWheel; i++) {
            st = new StringTokenizer(br.readLine());
            int rotate = Integer.parseInt(st.nextToken());
            char alpha = st.nextToken().charAt(0);

            accumulated += rotate;
            index = accumulated % numOfSpace;

            if (wheel[index] == '\u0000') {
                if (checkAlpha[(int)alpha - 65]) {
                    checkWheel = false;
                    break;
                }
                wheel[index] = alpha;
                checkAlpha[(int)alpha - 65] = true;
            } else if (wheel[index] == alpha) {
                continue;
            }
            else {
                checkWheel = false;
            }
        }

        if (!checkWheel) {
            sb.append("!");
        } else {
            for (int i = index; i >= 0; i--) {
                char element = (wheel[i] == '\u0000') ? '?' : wheel[i];
                sb.append(element);
            }

            for (int i = numOfSpace - 1; i > index; i--) {
                char element = (wheel[i] == '\u0000') ? '?' : wheel[i];
                sb.append(element);
            }
        }

        System.out.print(sb.toString());
    }
}
