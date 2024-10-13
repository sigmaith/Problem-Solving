// boj 2688 줄어들지 않아
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long[][] dp = new long[64 + 2][10];
        for (int j = 0; j < 10; j++) {
            dp[1][j] = 1;
        }
        for (int i = 2; i <= 64; i++) {
            long sum = 0;
            for (int k = 0; k < 10; k++) {
                sum += dp[i - 1][k];
            }
            for (int j = 0; j < 10; j++) {
                if (j != 0) {
                    sum -= dp[i - 1][j - 1];
                }
                dp[i][j] = sum;
            }
        }
        for (int j = 0; j < 10; j++) {
            dp[65][0] += dp[64][j];
        }

        //System.out.println(Arrays.deepToString(dp));
        
        int t = Integer.parseInt(br.readLine());
        for (int i = 0;  i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n + 1][0]);
        }
    }
}
