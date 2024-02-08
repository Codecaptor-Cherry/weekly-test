package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_19644_좀비떼가기관총진지에도오다니 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int L = Integer.parseInt(br.readLine());
        int[] map = new int[L];
        boolean Bomber[] = new boolean[L];
        boolean flag = true;


        st = new StringTokenizer(br.readLine());
        int ML = Integer.parseInt(st.nextToken());
        int MK = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(br.readLine());
        int start = -ML + 1; // 기관총의 현재 사정거리의 시작 인덱스.
        int end = 0; // 현재 사정거리의 끝 인덱스.
        int Bomb = ML - 1; // 폭탄을 사용할 수 있는 수.
        int shootDam = 0; // 기관총의 총 데미지.

        while (true) {
            map[end] = Integer.parseInt(br.readLine());
            shootDam = MK * ML - Bomb * MK; // 기관총으로 쏠 수 있는 총 데미지 계산.
            if (shootDam < map[end]) { // 만약 총 데미지가 좀비의 체력보다 낮다면
                if (--C < 0) { // 폭탄을 사용하는데 만약 폭탄이 없다면
                    flag = false; // 좀비를 죽일 수 없기 때문에 종료
                    break;
                }
                Bomb++; // 폭탄 사용 카운트를 증가
                Bomber[end] = true; // 현재 좀비에 폭탄을 사용
            }
            if (start < 0 || Bomber[start]) { // 기관총의 사정거리를 벗어나거나 폭탄이 사용된 좀비라면
                Bomb--; // 사용 가능한 폭탄 수를 감소
            }
            start++; // 사정거리 시작점을 이동
            if (++end >= L) // 모든 좀비를 확인했다면 종료
                break;
        }
        System.out.println(flag ? "YES" : "NO"); // 최종 결과 출력

    }

}
