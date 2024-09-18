// boj 29773 2023년은 검은 토끼의 해 (Hard)
import java.io.*;
import java.util.*;

public class Main {
    static String target = "2023";
    static String N;
    static long[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine(); // 주어진 N의 범위

        int nLength = N.length();
        dp = new long[nLength][5][2]; // [자릿수][매칭상태][작은지 여부]

        for (long[][] arr2 : dp) {
            for (long[] arr1 : arr2) {
                Arrays.fill(arr1, -1); // DP 테이블 초기화
            }
        }

        long result = solve(0, 0, 0);
        System.out.println(result);
    }

    static long solve (int pos, int matchState, int smaller) {
        // 기저 조건: 마지막 자릿수를 다 봤을 때
        if (pos == N.length()) {
            return (matchState == 4)? 1 : 0; // 2023을 완성했으면 1, 아니면 0
        }

        // 이미 계산한 상태면 그 값을 반환
        if (dp[pos][matchState][smaller] != -1) {
            return dp[pos][matchState][smaller];
        }

        int limit = smaller == 1 ? 9 : N.charAt(pos) - '0';
        long count = 0;

        for (int digit = 0; digit <= limit; digit++) {
            int nextMatchState = matchState;
            int nextSmaller = smaller;

            // 현재 자릿수가 N보다 작을 경우 표시
            if (digit < N.charAt(pos) - '0') {
                nextSmaller = 1;
            }

            // 2023의 몇 자리를 맞췄는지에 따라 상태 업데이트
            if (nextMatchState < 4 && digit == target.charAt(nextMatchState) - '0'){
                nextMatchState++;
            }

            // DP 전이
            count += solve(pos + 1, nextMatchState, nextSmaller);
        }

        // 결과 저장
        dp[pos][matchState][smaller] = count;
        return count;
    }
}
