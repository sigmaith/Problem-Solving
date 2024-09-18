// boj 29773 2023년은 검은 토끼의 해 (Hard)
import java.io.*;
import java.util.*;

public class Main {
    static String target = "2023";
    static String N;
    static long[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();

        int nLength = N.length();
        dp = new long[nLength][5][2];

        for (long[][] arr2 : dp) {
            for (long[] arr1 : arr2) {
                Arrays.fill(arr1, -1);
            }
        }

        long result = solve(0, 0, 0);
        System.out.println(result);
    }

    static long solve (int pos, int matchState, int smaller) {
        if (pos == N.length()) {
            return (matchState == 4)? 1 : 0;
        }

        if (dp[pos][matchState][smaller] != -1) {
            return dp[pos][matchState][smaller];
        }

        int limit = smaller == 1 ? 9 : N.charAt(pos) - '0';
        long count = 0;

        for (int digit = 0; digit <= limit; digit++) {
            int nextMatchState = matchState;
            int nextSmaller = smaller;

            if (digit < N.charAt(pos) - '0') {
                nextSmaller = 1;
            }

            if (nextMatchState < 4 && digit == target.charAt(nextMatchState) - '0'){
                nextMatchState++;
            }

            count += solve(pos + 1, nextMatchState, nextSmaller);
        }

        dp[pos][matchState][smaller] = count;
        return count;
    }
}
