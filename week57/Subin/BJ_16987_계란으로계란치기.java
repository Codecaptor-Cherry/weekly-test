import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static class Egg {
        int durability;
        int weight;
        boolean breaks;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
            this.breaks = false;
        }

        public void hit(Egg egg) {
            egg.durability -= this.weight;
            if (egg.durability <= 0) egg.breaks = true;
            this.durability -= egg.weight;
            if (this.durability <= 0) this.breaks = true;
        }

        public void recover(Egg egg) {
            egg.durability += this.weight;
            if (egg.durability > 0) egg.breaks = false;
            this.durability += egg.weight;
            if (this.durability > 0) this.breaks = false;
        }

        public boolean isBreaks() {
            return breaks;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Egg[] eggs = new Egg[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i] = new Egg(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        solve(eggs, n, 0, 0);
        System.out.println(ans);
    }

    private static int ans = 0;

    private static void solve(Egg[] eggs, int n, int idx, int cnt) {
        // 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우 계란을 치는 과정을 종료한다.
        if (idx == n) {
            ans = Math.max(ans, cnt);
            return;
        }

        // 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 치지 않고 넘어간다.
        if (eggs[idx].isBreaks() || cnt == n - 1) {
            solve(eggs, n, idx + 1, cnt);
            return;
        }

        // 손에 들고 있는 계란으로 깨지지 않은 다른 계란 중에서 하나를 친다.
        for (int i = 0; i < n; i++) {
            int breaksCnt = 0;
            if (i != idx && !eggs[i].isBreaks()) {
                eggs[i].hit(eggs[idx]);

                if (eggs[i].isBreaks()) ++breaksCnt;
                if (eggs[idx].isBreaks()) ++breaksCnt;

                // 가장 최근에 든 계란의 한 칸 오른쪽 계란을 손에 들고 2번 과정을 다시 진행한다.
                solve(eggs, n, idx + 1, cnt + breaksCnt);

                eggs[i].recover(eggs[idx]);
            }
        }
    }

}
