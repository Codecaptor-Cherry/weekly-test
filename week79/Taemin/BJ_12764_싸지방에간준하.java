import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class BJ_12764_싸지방에간준하 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<int[]> users = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            users.add(new int[]{start, end});
        }

        Collections.sort(users, new Comparator<int[]>(){
            @Override
            public int compare(int[] user1, int[] user2) {
                return Integer.compare(user1[0], user2[0]);
            }
        });

        PriorityQueue<Computer> hourlyAscendingPQ = new PriorityQueue<Computer>(new Comparator<Computer>() {
            @Override
            public int compare(Computer c1, Computer c2) {
                return Integer.compare(c1.endHour, c2.endHour);
            }
        });

        PriorityQueue<Computer> numberAscendingPQ = new PriorityQueue<Computer>(new Comparator<Computer>() {
            @Override
            public int compare(Computer c1, Computer c2) {
                return Integer.compare(c1.number, c2.number);
            }
        });


        int[] counts = new int[n];
        int max = 0;
        for (int[] user : users) {
            int userStartHour = user[0];
            int userEndHour = user[1];

            while (!hourlyAscendingPQ.isEmpty() &&
                    hourlyAscendingPQ.peek().endHour <= userStartHour) {
                numberAscendingPQ.add(hourlyAscendingPQ.poll());
            }

            if (numberAscendingPQ.isEmpty()) {
                hourlyAscendingPQ.add(new Computer(max, userEndHour));
                counts[max++]++;
            } else {
                Computer computer = numberAscendingPQ.poll();
                counts[computer.number]++;
                computer.endHour = userEndHour;
                hourlyAscendingPQ.add(computer);
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(max).append("\n");
        for (int i = 0; i < max; i++) {
            builder.append(counts[i]).append(" ");
        }

        System.out.print(builder.toString());
    }

    static class Computer {
        int number;
        int endHour;

        public Computer (int number, int endHour) {
            this.number = number;
            this.endHour = endHour;
        }
    }
}
