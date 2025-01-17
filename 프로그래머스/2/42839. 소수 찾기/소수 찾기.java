import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    static boolean[] visited;
    static int[] numbers;
    static int makedNumber;
    static Set<Integer> set;
    public int solution(String input) { // numbers는 길이 1 이상 7 이하인 문자열
        set = new HashSet<>();
        
        numbers = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            numbers[i] = input.charAt(i) - '0';
        } // 초기화
        
        visited = new boolean[input.length()]; // 방문 배열
        for (int i = 1; i <= input.length(); i++) {
            makedNumber = 0;   
            bruteforce(i);    
        }
        
        return set.size();
    }
    
    private void bruteforce(int len) {
        if (makedNumber != 0 && String.valueOf(makedNumber).length() == len) {
            if (isPrime(makedNumber)) {
                set.add(makedNumber);
            }
            return;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                makedNumber = makedNumber * 10 + numbers[i];
                bruteforce(len);
                makedNumber /= 10;
                visited[i] = false;
            }
        }
    }
    
    private boolean isPrime(int num) { // 소수 판별 함수
        if (num < 2) {
            return false;
        } else {
            int target = (int)Math.sqrt(num);
            for (int i = 2; i <= target; i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}