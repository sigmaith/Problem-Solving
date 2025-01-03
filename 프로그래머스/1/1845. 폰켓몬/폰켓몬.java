import java.io.*;
import java.util.*;
// 당신은 최대한 다양한 종류의 폰켓몬을 가지길 원하기 때문에

class Solution {
    public int solution(int[] nums) {
        Set<Integer> type = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            type.add(nums[i]);
        }
        int cnt = 0;
        for (Integer i : type) {
            cnt++;
        }
        
        if (cnt >= nums.length / 2) {
            return nums.length / 2;
        } else {
            return cnt;
        }
    }
}