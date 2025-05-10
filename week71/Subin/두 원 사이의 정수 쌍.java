// https://jjuniyo.tistory.com/6
class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        for (int x = 1; x <  r2; x++) {
            double inner = Math.sqrt(Math.pow(r1, 2) - Math.pow(x, 2));
            double outer = Math.sqrt(Math.pow(r2, 2) - Math.pow(x, 2));
            long cnt = (long) outer - (long) inner;
            if (isInteger(inner)) ++cnt;
            answer += cnt * 4;
        }
        return answer + (r2 - r1) * 4;
    }
    
    private boolean isInteger(double val) {
        return val == (long) val;
    }
}
