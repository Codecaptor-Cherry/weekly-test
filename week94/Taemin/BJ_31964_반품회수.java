import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_31964_반품회수 {

    public static int N;
    public static int[] Distance;
    public static int[] Time;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        Distance = Arrays.stream(br.readLine().split(" "))
                         .mapToInt(Integer::parseInt)
                         .toArray();

        Time = Arrays.stream(br.readLine().split(" "))
                     .mapToInt(Integer::parseInt)
                     .toArray();

        int curPoint = N - 1;
        long curTime = Distance[curPoint];

        while (true) {
            // 현 위치에서의 택배 반품 시간
            int time = Time[curPoint];
            int curDistance = Distance[curPoint];

            // 현재 시간과 반품 시간을 비교
            if (time > curTime) {
                long wait = time - curTime;
                curTime += wait;
            }

            // 이동
            curPoint--;
            if (curPoint == -1) {
                curTime += Distance[0];
                break;
            } else {
                curTime += (curDistance - Distance[curPoint]);
            }
        }

        System.out.println(curTime);
    }
}
