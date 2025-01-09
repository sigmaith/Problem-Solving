// boj 9935 문자열 폭발 

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String boom = br.readLine();

        StringBuilder stack = new StringBuilder();
        int boomLength = boom.length();

        for (int i = 0; i < input.length(); i++) {
            stack.append(input.charAt(i));

            // 스택 끝부분이 폭발 문자열과 같은지 검사
            if (stack.length() >= boomLength && stack.substring(stack.length() - boomLength, stack.length())
                    .equals(boom)) {
                stack.setLength(stack.length() - boomLength);
            }
        }

        // 결과 출력
        if (stack.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(stack.toString());
        }
    }
}

