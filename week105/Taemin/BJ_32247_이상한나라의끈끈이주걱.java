import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_32247_이상한나라의끈끈이주걱 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
        PriorityQueue<Plant> plantPriorityQueue = new PriorityQueue<>();
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        boolean flag = true;

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int c = Integer.parseInt(stringTokenizer.nextToken());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int h = Integer.parseInt(stringTokenizer.nextToken());

            plantPriorityQueue.add(new Plant(c, x, h));
        }

        int[] fly = new int[]{0, 0};
        while(!plantPriorityQueue.isEmpty()) {
            Plant plant = plantPriorityQueue.poll();
            int curC = plant.c;
            int curX = plant.x;
            int curH = plant.h;
            int distance = curX - fly[0];

            if (curC == 0 && fly[1] <= curH) {
                fly[0] = curX;
                fly[1] = curH + 1;
            } else {
                fly[0] = curX;
                fly[1] -= distance;
                if (curC == 0 && fly[1] <= curH) {
                    fly[1] = curH + 1;
                }
                else if (curC == 1 && fly[1] >= curH) {
                    flag = false;
                    break;
                }
            }
        }

        if (flag) {
            int distance = N - fly[0];
            if (fly[1] - distance > 0) {
                flag = false;
            }
        }

        String result = (flag) ? "stay" : "adios";
        System.out.print(result);
    }

    static class Plant implements Comparable<Plant> {
        public int c;
        public int x;
        public int h;

        public Plant(int c, int x, int h) {
            this.c = c;
            this.x = x;
            this.h = h;
        }

        @Override
        public int compareTo(Plant other) {
            return Integer.compare(this.x, other.x);
        }
    }
}
