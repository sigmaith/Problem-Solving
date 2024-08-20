// boj 5875: 오타
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String arr = br.readLine();
        int N = arr.length();

        int leftBracket = 0;
        int rightBracket = 0;
        int totalSum = 0;
        
        ArrayDeque<Integer> leftStack = new ArrayDeque<>();
        ArrayDeque<Integer> rightStack = new ArrayDeque<>();

        int[] leftList = new int[N];
        int[] rightList = new int[N];

        for (int i = 0; i < N; i++) {
            if (arr.charAt(i) == '(') {
                leftBracket += 1;
                totalSum += 1;
                leftStack.offerLast(i);
            } else {
                rightBracket += 1;
                totalSum -= 1;
                if (!leftStack.isEmpty()) {
                    leftStack.pollLast();
                } else {
                    rightStack.offerLast(i);
                }
            }

            leftList[i] = leftBracket; // 해당 괄호 존재 인덱스에 누적 괄호합 기록
            rightList[i] = rightBracket;
        }

        if (totalSum > 0) {
            System.out.println(leftList[N-1] -leftList[leftStack.pollLast()] + 1);
        } else if (totalSum < 0) {
            System.out.println(rightList[rightStack.pollFirst()]);
        } else {
            System.out.println(0);
        }

    }
}
