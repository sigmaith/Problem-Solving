// boj 14719 빗물

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken()), W = Integer.parseInt(st.nextToken()); // 높이, 너비
        int[] heights = new int[W];

        st = new StringTokenizer(br.readLine());
        int rains = 0;
        int left = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < W; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
            if (stack.isEmpty()) {
                stack.offerLast(heights[i]);
                left = heights[i]; // 기준! (큰거)
            } else {
                if (stack.peekLast() >= heights[i]) { // 작다 -> 추가
                    stack.offerLast(heights[i]);
                } else if (stack.peekLast() < heights[i]) { // 바로 앞거보다 크다
                    int height = Math.min(left, heights[i]);
                    int cnt = 0;
                    while (!stack.isEmpty() && stack.peekLast() < height) {
                        rains += height - stack.pollLast();
                        cnt++;
                    }

                    for (int j = 0; j < cnt; j++) {
                        stack.offerLast(height);
                    }
                    stack.offerLast(heights[i]);
                    if (heights[i] > left) {
                        left = heights[i];
                    }
                }
            }
        }

        System.out.println(rains);
    }
}