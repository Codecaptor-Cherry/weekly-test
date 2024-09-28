/*
 * n개의 포인트. 각 포인트는 1 ~ n까지의 서로 다른 번호
 * m개의 정해진 운송 경로. 첫 포인트에서 시작, 할당된 포인트를 순서대로 방문
 * 전체 로봇 x대. 모든 로봇은 0초에 동시 출발. 1초마다 r 또는 c 좌표 중 하나가 1+-인 좌표로 이동
 * 항상 최단 경로로 이동. 최단 경로가 여러 가지일 경우, r좌표 이동 우선
 * 마지막 포인트에 도착한 로봇은 물류센터를 벗어남
 * 이동 중 같은 좌표에 로봇이 2대 이상 모이면 충돌 위험 상황
 * 설정대로 움직일 때, 위험 상황이 총 몇 번 일어나는지 구하기
 * points : 각 포인트의 좌표 정보
 * routes : 로봇이 이동하는 포인트 경로. ex) [4, 2] : 4번 포인트에서 2번 포인트로 이동
 
 * 각 로봇을 최단 경로로 이동
 * 시점마다 충돌 여부 확인
 
 * Queue를 이용해서 시간 처리 따로 안해도 됨 ... 입력된 순서가 시간
 * 목표 좌표는 2개로 제한되지 않고, 여러 개일 수 있음 ... ex) 1번 -> 4번 -> 3번
 */

import java.util.*;

class Solution {
    static int answer;
    static int size;
    public int solution(int[][] points, int[][] routes) {
        answer = 0;
        size = routes.length;
        
        Queue<int[]>[] queue = new ArrayDeque[size]; // 각 로봇의 이동 경로
        for(int i = 0; i < size; i++) {
            queue[i] = new ArrayDeque<>();
        }
        
        for(int i = 0; i < size; i++) { // i번 로봇의 이동
            move(queue, i, points, routes[i]);
        }

        simul(queue); // 동일 시간대에 동선이 겹치는지 확인

        return answer;
    }
    
    public void simul(Queue<int[]>[] queue) {
        int count = 0; // 운송 완료한 로봇의 개수
        
        while(size != count) { // 완료 로봇의 개수가 routes 크기와 같아지면 모든 로봇 이동 완료
            int[][] map = new int[101][101];
            count = 0; // 현재 시간에 완료된 로봇의 개수
            
            for(int i = 0; i < size; i++) { // 1번 로봇부터 n번 로봇까지의 경로 확인
                if(queue[i].isEmpty()) { // i번 로봇의 이동 경로가 없는 경우 완료된 로봇
                    count++;
                    continue;
                }
                
                int[] robot = queue[i].poll(); // 현재 시간대 i번 로봇의 경로
                map[robot[0]][robot[1]]++; // map에 저장
            }
            
            check(map); // map에 2 이상인 경우 충돌
            // print(map);
        }
    }
    
    public void check(int[][] map) {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map.length; j++) {
                if(map[i][j] > 1) {
                    answer++; // 충돌
                }
            }
        }
    }
    
    public void move(Queue<int[]>[] queue, int idx, int[][] points, int[] route) {
        // idx번 로봇의 시작 좌표 x, y
        int x = points[route[0] - 1][0];
        int y = points[route[0] - 1][1];
        queue[idx].offer(new int[]{x, y});
        
        for(int i = 1; i < route.length; i++) { // 순서대로 목표 좌표로 이동 ... 목표 좌표는 여러 개일 수 있음
            // 이번 목표 좌표
            int ax = points[route[i] - 1][0];
            int ay = points[route[i] - 1][1];
            
            while(x != ax || y != ay) { // 도착할 때까지 이동
                // r좌표 우선 이동
                if(x < ax) {
                    x++;            
                } else if(x > ax) {
                    x--;
                } else {
                    // 이후 c좌표 이동
                    if(y < ay) {
                        y++;
                    } else if(y > ay) {
                        y--;
                    }
                }
                
                queue[idx].offer(new int[]{x, y}); // 이동 경로 저장
            }
            
            // 현재 목표에 도착한 경우 다음 이동을 위해 시작 좌표 갱신
            x = ax;
            y = ay;
        }
    }
    
    public void print(int[][] map) {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
