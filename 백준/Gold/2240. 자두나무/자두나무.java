// boj 2240 자두나무

import java.io.*;
import java.util.*;

public class Main {
    static int[] plums;
    static int T, W;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken()); // 시간: T(1≤T≤1,000)
        W = Integer.parseInt(st.nextToken()); // 이동횟수: W(1≤W≤30)

        plums = new int[T];
        for (int i = 0; i < T; i++) {
            plums[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[T + 1][W + 1];
        for (int i = 0; i <= T; i++) {
            Arrays.fill(dp[i], 0);
        }
        System.out.println(dp(0, 0));
    }

    private static int dp(int idx, int move) {
        if (idx == T) {
            return 0;
        }
        if (dp[idx][move] == 0) {
            int result1 = -1, result2 = -1, result3 = -1, result4 = -1;
            if (move % 2 + 1 == plums[idx]) {
                result1 = 1 + dp(idx + 1, move);
                if (move < W) {
                    result2 = 0 + dp(idx + 1, move + 1);
                }
            } else {
                result3 = 0 + dp(idx + 1, move);
                if (move < W) {
                    result4 = 1 + dp(idx + 1, move + 1);
                }
            }
            dp[idx][move] = Math.max(Math.max(result1, result2), Math.max(result3, result4));
        }
        return dp[idx][move];
    }
}