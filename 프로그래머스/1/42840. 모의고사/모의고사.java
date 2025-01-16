import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int n = answers.length;
        int[] solution1 = new int[n];
        int[] solution2 = new int[n];
        int[] solution3 = new int[n];
        
        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;
        for (int i = 0; i < n; i++) {
            solution1[i] = i % 5 + 1;
            
            if (i % 2 == 0) {
                solution2[i] = 2;
            } else if (i % 8 == 1) {
                solution2[i] = 1;
            } else if (i % 8 == 3) {
                solution2[i] = 3;
            } else if (i % 8 == 5) {
                solution2[i] = 4;
            } else if (i % 8 == 7) {
                solution2[i] = 5;
            }
            
            if (i % 10 == 0 || i % 10 == 1) {
                solution3[i] = 3;
            } else if (i % 10 == 2 || i % 10 == 3) {
                solution3[i] = 1;
            } else if (i % 10 == 4 || i % 10 == 5) {
                solution3[i] = 2;
            } else if (i % 10 == 6 || i % 10 == 7) {
                solution3[i] = 4;
            } else if (i % 10 == 8 || i % 10 == 9) {
                solution3[i] = 5;
            }
            
            int answer = answers[i];
            if (answer == solution1[i]) {
                cnt1++;
            }
            if (answer == solution2[i]) {
                cnt2++;
            }
            if (answer == solution3[i]) {
                cnt3++;
            }
        }
        
        int[] students = new int[]{cnt1, cnt2, cnt3};
        int max = cnt1;
        for (int i = 0; i < 3; i++) {
            if (students[i] > max) {
                max = students[i];
            }
        }
        
        List<Integer> great = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (students[i] == max) {
                great.add(i + 1);
            }
        }
        int[] answer = new int[great.size()];
        for (int i = 0; i < great.size(); i++) {
            answer[i] = great.get(i);
        }
        return answer;
    }
}