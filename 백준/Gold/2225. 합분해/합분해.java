// #2225 합분해
import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        dp = new int[200+1][200+1];
        for (int i = 1; i <= 200; i++) {
            dp[1][i] = i;
        }
        for (int i = 1; i<= 200; i++) {
            dp[i][1] = 1;
        }

        int result = DP(n, k) % 1_000_000_000;
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }   
    static int DP(int n, int k) {
        if (n < 1 || k < 1) return 0;
        if (dp[n][k] == 0) {
            dp[n][k] = DP(n-1, k) + DP(n, k-1);
            dp[n][k] %= 1_000_000_000; // 이게 중요
        }
        return dp[n][k];
    }
}
