import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        
        int answer = 0;
        int idx = 0;
        for (int h = 0; h <= citations[n-1]; h++) {
            if (n-1-idx+1 >= h && answer < h) {
                answer = h;
            }
            while (idx < n-1 && citations[idx] <= h) {
                idx++;
            } // citations[idx]가 h보다 큰 상태    
        }
    
        return answer;
    }
}