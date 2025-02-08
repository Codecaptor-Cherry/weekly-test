package saturday.year25.sat250208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * 거울냥이는 상하좌우 빔 발사
 * 해당 빔은 다른 거울냥이들을 관통하며, 어디서 발사하든 격자판 끝까지 도달
 * 거울냥이의 하단 칸에 항상 거울 위치
 * 거울은 빔이 통과되지 않으며, 빔에 의해 녹지 않음
 * 또한, 거울은 빔을 반사하지 않음
 * 거울냥이가 죽어도 거울은 있던 자리에 남는다.
 * 살아 있는 거울냥이들이 모두 빔을 한 번씩 순서대로 쐈을 때, 마지막까지 살아남은 거울냥이 마릿수 구하기
 *
 * 상하빔은 거울때문에 무효
 * 좌우빔을 쏘면 거울 사이의 고양이는 최종적으로 한마리만 남음 ~ 빔을 발사하는 순서는 중요하지 않음
 * 동일 행 고냥이가 존재하는 거울 사이 개수 구하기
 */

class Inner {
    int y, type;

    Inner(int y, int type) {
        this.y = y;
        this.type = type;
    }
}
public class BJ_16226_거울냥이 {
    static final int INF = (int)1e5;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = stoi(br.readLine()); // 거울냥이 마릿수

        int ans = 0;
        int length = 0;
        int[][] cats = new int[N][2];
        for(int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            int x = stoi(inputs[0]);
            int y = stoi(inputs[1]);

            cats[i][0] = x - 1;
            cats[i][1] = y - 1;

            length = Math.max(length, x + 1);
        }

        Arrays.sort(cats, new Comparator<int[]>() { // x좌표 오름차순, y좌표 오름차순
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }

                return o1[0] - o2[0];
            }
        });

        List<ArrayList<Inner>> list = new ArrayList<>();
        for(int i = 0; i < length; i++) {
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < N; i++) {
            list.get(cats[i][0]).add(new Inner(cats[i][1], 1)); // 냥이
            list.get(cats[i][0] + 1).add(new Inner(cats[i][1], -1)); // 거울
        }

        for(int i = 0; i < length; i++) {
            list.get(i).sort((o1, o2) -> o1.y - o2.y);
        }

        for(int i = 0; i < length; i++) {
            boolean flag = false;
            for(Inner inner : list.get(i)) {
                if(!flag && inner.type == 1) {
                    ans++;
                    flag = true;
                } else if(flag && inner.type == -1) {
                    flag = false;
                }
            }
        }

        System.out.println(ans);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
