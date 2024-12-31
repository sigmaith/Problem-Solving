import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


class Solution {
    public int[] solution(int[] array, int[][] commands) {
        // System.out.println(commands.length);
        int length = commands.length;
        int[] answer = new int[length];
        for (int idx = 0; idx < length; idx++) {
            int[] cmd = commands[idx];
            int i = cmd[0] - 1;
            int j = cmd[1] - 1;
            int k = cmd[2] - 1;
            List<Integer> list = new ArrayList<>();
            for (int idx1 = i; idx1 <= j; idx1++) {
                list.add(array[idx1]);
            }
            List<Integer> sorted = list.stream().sorted().collect(Collectors.toList());
            answer[idx] = sorted.get(k);
        }
        
        
        return answer;
    }
}