import java.util.*;

/*
 * 정수 쌍 (s, e) : x축에 평행한 직선 형태의 개구간 ~ s 초과 e 미만
 * return 모든 폭격 미사일을 요격하기 위해 필요한 요격 미사일의 수 최솟값
 * 
 * 1. e가 작은 순서대로 정수 쌍 오름차순 정렬
 * 2. 해당 구간에 요격 가능한 최대 범위 지정
 * 3. 다음 구간의 s가 e보다 크면 요격 범위 재지정(2번 과정) 
 */

class Interval implements Comparable<Interval>{
    int a, b;
    
    Interval(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Interval o) {
        if(this.b == o.b) {
            return this.a - o.a;
        } else {
            return this.b - o.b;
        }
    }
}

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        PriorityQueue<Interval> pq = new PriorityQueue<>();
        for(int[] target : targets) {
            pq.offer(new Interval(target[0], target[1]));
        }
        
        int end = -1;
        while(!pq.isEmpty()) {
            if(pq.peek().a > end) {
                end = pq.peek().b - 1;
                answer++;
            }
            pq.poll();
        }
        
        return answer;
    }
}
