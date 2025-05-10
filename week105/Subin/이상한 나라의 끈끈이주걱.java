import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static class Info implements Comparable<Info> {
        int c;
        int x;
        int h;

        public Info(int c, int x, int h) {
            this.c = c;
            this.x = x;
            this.h = h;
        }

        @Override
        public int compareTo(Info o) {
            return x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Info[] infos = new Info[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            infos[i] = new Info(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(infos);


        int[] fly = new int[]{0, 0};
        for (Info info : infos) {
            if (info.c == 0) { // 아래에서 위로 올라오는 끈끈이
                fly[1] = Math.max(fly[1] - (info.x - fly[0]), info.h + 1); // 가장 낮은 위치로 피한다 (내려가기 힘듦)
                fly[0] = info.x;
            } else if (info.c == 1) { // 위에서 아래로 내려오는 끈끈이
                // info.x, info.h - 1을 지나갈 수 있는지 확인
                if (info.x - fly[0] < fly[1] - (info.h - 1)) {
                    System.out.println("adios");
                    return;
                }
                fly[1] = fly[1] - (info.x - fly[0]); // 내려갈 수 있는 만큼 내려가기
                fly[0] = info.x;
            }
        }

        if (n - fly[0] < fly[1]) {
            System.out.println("adios");
        } else {
            System.out.println("stay");
        }

    }
}
