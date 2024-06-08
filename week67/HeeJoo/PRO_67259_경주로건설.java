/*
 * NxN 도면
 * 빈 칸(0), 벽(1)
 * 시작점 : 좌측 상단(0, 0)
 * 도착점 : 우측 하단(N-1, N-1)
 * 상하좌우로 인접한 두 빈 칸을 연결하여 끊기지 않는 경주로 건설
 * 코너 : 두 직선 도로가 서로 직각으로 만나는 지점
 * 비용 = 직선 * 100 + 코너 * 500
 * 경주로 건설에 필요한 최소 비용 구하기
 
 * BFS로 도착까지 루트 구하기
 * 이전 방향과 이동 방향 비교하여 금액 결정
 
 * DFS로 했더니 시간초과남 ㅠ
 * 최종 ans와 nowCost를 비교해서 가지치기를 했지만 결국 많은 루트에서 깊이 들어가서 그런듯?
 * 현재 풀이와 비슷하게 visited를 4방향으로 나눠서 돌리기 + 방향별 비용으로 가지치기하면 초과안날 듯 ㅎ
 * 중간에 같은 방향으로 이동할 때까지 비용이 저장된 비용보다 높으면 굳이 그 루트로 갈 필요?없?우?
 */

import java.util.*;

class Node {
    int x, y, cost, dir;
    
    Node(int x, int y, int cost, int dir) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.dir = dir;
    }
}

class Solution {
    static int ans = Integer.MAX_VALUE;
    // 우하좌상 ... 상하좌우보다 시간초과가 안남 ㄷㄷ
    static int[] dirX = new int[]{1, 0, -1, 0};
    static int[] dirY = new int[]{0, 1, 0, -1};
    public int solution(int[][] board) {
        int N = board.length;
        boolean[][][] visited = new boolean[N][N][4];
        getRoute(0, 0, board, N, visited);
        
        return ans;
    }
    
    public void getRoute(int x, int y, int[][] board, int N, boolean[][][] visited) {
        Queue<Node> q = new ArrayDeque<>();
        
        Node start = new Node(0, 0, 0, -1);
        Arrays.fill(visited[0][0], true);
        q.offer(start);
        
        int dx, dy;
        while(!q.isEmpty()) {
            Node now = q.poll();
            
            if(now.x == N - 1 && now.y == N - 1) {
                ans = Math.min(ans, now.cost);
                continue;
            }
            
            for(int i = 0; i < 4; i++) {
                dx = now.x + dirX[i];
                dy = now.y + dirY[i];
                
                if(!checkRange(dx, dy, N)) { // 좌표 범위
                    continue;
                }
                
                if(board[dx][dy] == 1) { // 빈 칸 여부
                    continue;
                }
                
                int nextCost = now.cost;
                if(now.dir == -1 || now.dir == i) { // 시작(-1)
                    nextCost += 100; // 직선
                } else {
                    nextCost += 600; // 직선 + 코너
                }
                
                if(!visited[dx][dy][i] || board[dx][dy] >= nextCost) { // 미방문 or 금액이 더 적은 경우
                    q.offer(new Node(dx, dy, nextCost, i));
                    board[dx][dy] = nextCost;
                    visited[dx][dy][i] = true;
                }
            }
        }
        
    }
    
    public boolean checkRange(int x, int y, int N) {
        if(x < 0 || x >= N) {
            return false;
        }
        
        if(y < 0 || y >= N) {
            return false;
        }
        
        return true;
    }
}
