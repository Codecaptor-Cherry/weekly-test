import java.util.*;

class Missile implements Comparable<Missile> {
    public int s, e;
    
    public Missile(int s, int e) {
        this.s = s;
        this.e = e;
    }
    
    @Override
    public int compareTo(Missile o) {
        if (this.e == o.e) return this.s - o.s;
        return this.e - o.e;
    }
}

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        PriorityQueue<Missile> pq = new PriorityQueue<>();
        for (int[] target : targets) {
            pq.add(new Missile(target[0], target[1]));
        }
        
        int e = 0;
        while (!pq.isEmpty()) {
            if (pq.peek().s >= e) {
                e = pq.peek().e;
                ++answer;
            }
            pq.poll();
        }
        
        return answer;
    }
}
