import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Map<String, Integer> gemIndexMap = createGemIndexMap(gems);
        int totalGemTypes = gemIndexMap.size();
        int[] gemIndexes = convertToIndexes(gems, gemIndexMap);
        
        int gemTypeCount = 0;
        int left = 0, right = 0;
        int[] gemCounts = new int[gems.length];
        
        int[] answer = {1, gems.length}; // 최종 결과 배열
        
        // 투 포인터를 이용하여 최소 구간 탐색
        while (left <= right && left < gems.length) {
            if (right < gems.length && gemTypeCount < totalGemTypes) {
                if (gemCounts[gemIndexes[right]] == 0) gemTypeCount++;
                gemCounts[gemIndexes[right++]]++;
            } else {
                if (gemCounts[gemIndexes[left]] == 1) gemTypeCount--;
                gemCounts[gemIndexes[left++]]--;
            }
            
            if (gemTypeCount == totalGemTypes && answer[1] - answer[0] > right - (left + 1)) {
                answer[0] = left + 1;
                answer[1] = right;
            }
        }
        return answer;
    }
    
    private Map<String, Integer> createGemIndexMap(String[] gems) {
        Map<String, Integer> gemIndexMap = new HashMap<>();
        int index = 0;
        for (String gem : gems) {
            gemIndexMap.putIfAbsent(gem, index++);
        }
        return gemIndexMap;
    }
    
    private int[] convertToIndexes(String[] gems, Map<String, Integer> gemIndexMap) {
        int[] indexes = new int[gems.length];
        for (int i = 0; i < gems.length; i++) {
            indexes[i] = gemIndexMap.get(gems[i]);
        }
        return indexes;
    }
}
