package saturday.year25.sat250329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * (x, y) = 병사 수
 * c_i가 (x, y)에 미치는 영향력 = s_i / (x, y)까지의 거리 제곱
 * c_i 병사 수 < c_j의 영향력인 경우 c_i는 c_j의 속국이므로 c_j의 지배국을 따름
 * c_i 병사 수 > 모든 c_j의 영향력인 경우 c_i는 왕국
 * c_i에 "동일"한 영향력을 행사하는 나라가 2개 이상인 경우 민주주의
 *
 * 출력 : N개의 도시 타입
 * 1. 왕국 : K
 * 2. 민주주의 : D
 * 3. 속국 : 지배국가의 번호
 *
 * 1. c_i가 각 나라에 받는 영향력 계산
 * 2. 영향력을 미치는 국가가 0개라면 왕국 -> K
 * 3. 영향력을 미치는 국가가 1개라면 번호 저장
 * 4. 동일한 영향력을 미치는 국가가 2개 이상이면 민주주의 -> D
 */

class City {
    int x, y, s;

    City(int x, int y, int s) {
        this.x = x;
        this.y = y;
        this.s = s;
    }
}
public class BJ_3369_Countries {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = stoi(br.readLine()); // 도시의 수

        City[] cities = new City[n]; // 도시 정보
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = stoi(st.nextToken());
            int y = stoi(st.nextToken());
            int s = stoi(st.nextToken());
            cities[i] = new City(x, y, s);
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            int idx = -1; // 지배국의 번호
            int cnt = 0; // 지배국 개수
            double power = 0;
            for (int j = 0; j < n; j++) {
                if(i == j) { // 본인과 비교 제외
                    continue;
                }

                double influence = getInfluence(i, j, cities); // c_j가 c_i에 미치는 영향력
                if(cities[i].s < influence) { // 영향력이 큰 경우만 갱신
                    if(power == influence) { // 동일한 영향력인 경우 개수+
                        cnt++;
                    } else if(power < influence) { // 기존 지배국보다 영향력이 더 큰 나라를 따라감
                        idx = j;
                        cnt = 1;
                        power = influence;
                    }
                }
            }

            if(cnt == 1) { //  지배국 1 ~ 속국
                parent[i] = idx;
            } else if(cnt > 1) { // 지배국 다수 ~ 민주주의
                parent[i] = -1;
            }
            // 그 외는 왕국이라 그대로 ...
        }

        for (int i = 0; i < n; i++) {
            if(parent[i] == i) { // 지배국 x ~ 왕국
                sb.append("K\n");
            } else if(parent[i] == -1) { // 지배국 다수 ~ 민주주의
                sb.append("D\n");
            } else { // 지배국 1 ~ 속국
                sb.append(getParent(parent[i], parent) + 1 + "\n");
            }
        }

        System.out.println(sb);
    }

    public static int getParent(int idx, int[] parent) {
        if(idx != parent[idx]) {
            return parent[idx] = getParent(parent[idx],parent); // 지배국을 찾는 동시에 갱신
        }

        return parent[idx];
    }

    public static double getInfluence(int a, int b, City[] cities) {
        int distX = Math.abs(cities[a].x - cities[b].x);
        int distY = Math.abs(cities[a].y - cities[b].y);
        return (double)cities[b].s / (distX * distX + distY * distY);
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
