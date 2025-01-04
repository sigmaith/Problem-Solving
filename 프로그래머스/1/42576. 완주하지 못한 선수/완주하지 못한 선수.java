import java.io.*;
import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, int[]> record = new HashMap<>();
        for (String p : participant) {
            if (!record.containsKey(p)) {
                record.put(p, new int[]{1, 0});
            } else {
                int[] info = record.get(p);
                record.put(p, new int[]{info[0] + 1, 0});
            }
        }
        for (String c : completion) {
            int[] info = record.get(c);
            record.put(c, new int[]{info[0], info[1] + 1});
        }
        String answer = "";
        for (String name : record.keySet()) {
            int[] info = record.get(name);
            if (info[0] != info[1]) {
                answer = name;
            }
        }
        return answer;
    }
}













