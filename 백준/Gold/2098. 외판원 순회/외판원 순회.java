// boj 2098 외판원 순회
import java.io.*;
import java.util.*;

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    static int N;
    static long[][] W, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new long[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Long.parseLong(st.nextToken());
            }
        }

        // 비트마스크로 방문 처리 (1 << N) 상태를 초기화
        dp = new long[1 << N][N]; // 방문한 곳 기록, 현재 위치
        for (int i = 0; i < (1<<N); i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(dfs(1, 0));
    }

    static long dfs(int visited, int current) {
        if (visited == (1 << N) - 1) { // 모든 곳 방문
            return W[current][0] != 0 ? W[current][0] : INF;
        }
        if (dp[visited][current] != -1) {
            return dp[visited][current];
        }
        dp[visited][current] = INF;
        for (int next = 0; next < N; next++) {
            if ((visited & (1 << next)) == 0 && W[current][next] != 0) {
                dp[visited][current] = Math.min(dp[visited][current],
                        W[current][next] + dfs(visited |(1 << next), next));
            }
        }
        return dp[visited][current];
    }
}
