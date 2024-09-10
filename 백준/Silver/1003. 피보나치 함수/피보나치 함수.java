// boj 1003 피보나치 함수
import java.io.*;
import java.util.*;

public class Main {
    static int callZero = 0;
    static int callOne = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] dp = new int[2][40 + 1];
        dp[0][0] = 1; dp[0][1] = 0;
        dp[1][0] = 0; dp[1][1] = 1;

        int t = Integer.parseInt(br.readLine());
        for (int i = 2; i <= 40; i++) {
            dp[0][i] = dp[0][i - 2] + dp[0][i - 1];
            dp[1][i] = dp[1][i - 2] + dp[1][i - 1];
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine()); // 0 <= n <= 40
            bw.write(dp[0][n] + " " + dp[1][n] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}