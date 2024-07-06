/*
 * x, y축으로 이루어진 2차원 직교 좌표계
 * 중심이 원점인 서로 다른 크기의 원 2개
 * 반지름 r1, r2가 주어질 때, 두 원 사이 공간에 x좌표와 y좌표가 모두 정수인 점의 개수 구하기
 * 경계선 포함
 
 * r1 외부, r2 내부 : r1 <= 원점과 임의의 점 사이의 거리 <= r2
 * r^2 = x^2 + y^2 ~ y^2 = r^2 - x^2
 * 1사분면만 구해서x4 - 경계선겹x4
 */

import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        double r1Pow = Math.pow(r1, 2);
        double r2Pow = Math.pow(r2, 2);
        int dup = r2 - r1 + 1; // 경계선 겹
        
        // 1. x좌표를 0부터 1씩 증가시키면서
        for(int i = 0; i <= r2; i++) {
            // 2. 큰 원과 작은 원의 y좌표 구하기
            double xPow = Math.pow(i, 2);
            double y1 = 0, y2 = 0;
            if(i < r1) {
                y1 = Math.sqrt(r1Pow - xPow);
                if(y1 > Math.floor(y1)) { // x.xxx일 때
                    y1 = Math.ceil(y1); // x + 1부터 시작
                }
            }
            
            y2 = Math.sqrt(r2Pow - xPow);
            if(y2 > Math.floor(y2)) { // x.xxx일 때
                y2 = Math.floor(y2); // x까지 가능
            }
            
            // System.out.println(y1 + ", " + y2);
            answer += y2 - y1 + 1;
        }

        return (answer - dup) * 4;
    }
}
