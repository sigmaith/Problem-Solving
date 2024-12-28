import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n) {
        int[] answer = new int[101];
        
        int cnt = 1;
        for (int i = 1; i <= 100; i++) {
            while (cnt % 3 == 0 || cnt / 100 == 3 
                   || (cnt / 10) % 10 == 3 || cnt % 10 == 3) {
                cnt ++;
            } 
            answer[i] = cnt;
            cnt++;
        }
        System.out.println(Arrays.toString(answer));
        
        return answer[n];
    }
}