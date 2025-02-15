package saturday.year25.sat250215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 22학점 시간표 만들기
 * 그룹 A ~ H는 각각의 과목 존재
 * 그룹마다 그룹에 속한 강의 중 최대 하나의 강의를 선택해서 시간표 구성 ~ 0 or 1
 * 각 강의에는 진행 요일, 시작 시각, 종료 시각이 있고, 선택한 강의끼리 진행 시간이 겹치면 안됨
 * 하나의 그룹에 학점이 다른 강의가 있을 수 있음
 * 선택한 강의의 학점 합이 정확히 22가 되도록 시간표를 만들 수 있는 경우의 수 구하기
 *
 * 1. 그룹에서 들을 과목 선택
 * 2. 학점 계산
 * 3. 요일 계산
 * 4. 시간 계산
 */

class Lecture implements Comparable<Lecture> {
    int c, d; // 학점, 요일
    int sh, sm; // 시작 시각
    int eh, em; // 종료 시작

    Lecture(int c, int d, int sh, int sm, int eh, int em) {
        this.c = c;
        this.d = d;
        this.sh = sh;
        this.sm = sm;
        this.eh = eh;
        this.em = em;
    }

    @Override
    public int compareTo(Lecture o) {
        // 1. 요일 오름차순
        if(this.d == o.d) {
            // 2. 시작 시각 오름차순
            if(this.sh == o.sh) {
                // 3. 종료 시작 오름차순
                if(this.sm == o.sm) {
                    if(this.eh == o.eh) {
                        return this.em - o.em;
                    } else {
                        return this.eh - o.eh;
                    }
                } else {
                    return this.sm - o.sm;
                }
            } else {
                return this.sh - o.sh;
            }
        } else {
            return this.d - o.d;
        }
    }
}
public class BJ_31837_시간표만들기 {
    static int N, ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = stoi(br.readLine()); // 그룹 개수

        List<Lecture[]> list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            int A = stoi(br.readLine()); // 해당 그룹의 과목 개수

            list.add(new Lecture[A]);
            for (int j = 0; j < A; j++) {
                st = new StringTokenizer(br.readLine());

                int c = stoi(st.nextToken());
                int d = stoi(st.nextToken());
                String[] s = st.nextToken().split(":");
                String[] e = st.nextToken().split(":");

                int sh = stoi(s[0]);
                int sm = stoi(s[1]);
                int eh = stoi(e[0]);
                int em = stoi(e[1]);
                list.get(i)[j] = new Lecture(c, d, sh, sm, eh, em);
            }
        }

        // ------------------------------------------------

        int[] selected = new int[N];
        Arrays.fill(selected, -1);
        perm(0, 0, selected, list);

        System.out.println(ans);
    }

    public static void perm(int idx, int sum, int[] selected, List<Lecture[]> list) {
        // next. 과목 선택 완료
        if(sum == 22) {
            if(check(selected, list)) {
                ans++;
            }
            return;
        }

        // stop 1. 22학점 초과
        if(sum > 22) {
            return;
        }

        // stop 2. 그룹 종료
        if(idx >= N) {
            return;
        }

        // continue. 현재 그룹에서 과목 선택 후 다음 그룹으로 이동
        // 1. 선택 o
        for (int i = 0; i < list.get(idx).length; i++) {
            selected[idx] = i;
            perm(idx + 1, sum + list.get(idx)[i].c, selected, list);
        }

        // 2. 선택 x
        selected[idx] = -1;
        perm(idx + 1, sum, selected, list);
    }

    public static boolean check(int[] selected, List<Lecture[]> list) {
        List<Lecture> tmp = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            int idx = selected[i];

            if(idx != -1) {
                tmp.add(list.get(i)[idx]);
            }
        }

        Collections.sort(tmp);
//        for (int i = 0; i < tmp.size(); i++) {
//            Lecture l = tmp.get(i);
//            System.out.printf("%d %d %d:%d %d:%d\n", l.c, l.d, l.sh, l.sm, l.eh, l.em);
//        }
//        System.out.println();

        for(int i = 0; i < tmp.size() - 1; i++) {
            Lecture now = tmp.get(i);
            Lecture next = tmp.get(i + 1);

            // 1. 요일 확인
            if(now.d == next.d) {
                // 2. 시간 확인
                if (!compareTime(now, next)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean compareTime(Lecture now, Lecture next) {
        if(now.eh > next.sh) { // 현재 강의의 종료 시가 다음 강의의 시작 시보다 큰 경우
//            System.out.printf("시 !! %d:%d | %d:%d\n", now.eh,now.em,next.sh,next.sm);
            return false;
        } else if(now.eh == next.sh) { // 시가 동일한 경우 분 비교
            if(now.em > next.sm) { // 현재 강의의 종료 분이 다음 강의의 시작 분보다 큰 경우
//                System.out.printf("분 !! %d:%d | %d:%d\n", now.eh,now.em,next.sh,next.sm);
                return false;
            }
        }

        return true;
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
