import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        // 가로가 세로보다 길다
        int[] answer = new int[2];
        
        for (int i = 1; i <= Math.sqrt(yellow); i++) {
            if (yellow % i == 0 && yellow / i >= i) {
                int vertical = i;
                int horizon = yellow / i;
                
                if ((horizon + 2) * 2 + (vertical + 2) * 2 - 4 == brown) {
                    answer[0] = horizon + 2;
                    answer[1] = vertical + 2;
                }
            }
        }
        
        return answer;
    }
}