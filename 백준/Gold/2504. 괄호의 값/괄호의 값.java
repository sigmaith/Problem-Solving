// boj 2504 괄호의 값

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        int result = 0;
        int openX = 0;
        int openY = 0;
        for (int i = 0; i < str.length(); i++) {
            if (stack.isEmpty() && (str.charAt(i) == ')' || str.charAt(i) == ']')) {
                System.out.println(0);
                return;
            }
            if (str.charAt(i) == '(') {
                openX++;
            } else if (str.charAt(i) == '[') {
                openY++;
            } else if (str.charAt(i) == ')') {
                if (stack.peekLast() == '(') {
                    result += Math.pow(2, openX) * Math.pow(3, openY); // 가장 안쪽
                }
                openX--;
            } else if (str.charAt(i) == ']') {
                if (stack.peekLast() == '[') {
                    result += Math.pow(2, openX) * Math.pow(3, openY); // 가장 안쪽
                }
                openY--;
            }
            stack.offerLast(str.charAt(i));
        }

        stack = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            if (stack.isEmpty() && (
                    str.charAt(i) == ')' || str.charAt(i) == ']')) {
                System.out.println(0);
                return;
            }
            if (str.charAt(i) == '(') {
                stack.offerLast('(');
            } else if (str.charAt(i) == '[') {
                stack.offerLast('[');
            } else if (str.charAt(i) == ')') {
                if (!stack.isEmpty() && stack.peekLast() == '(') {
                    stack.pollLast();
                }
            } else if (str.charAt(i) == ']') {
                if (!stack.isEmpty() && stack.peekLast() == '[') {
                    stack.pollLast();
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println(result);
        } else {
            System.out.println(0);
        }
    }
}