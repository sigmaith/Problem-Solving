// boj 14501 퇴사
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] schedule;
    static int[] reward;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        reward = new int[N + 1]; // 1-base index

        schedule = new int[N + 1][2]; // 1-base index
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken()); // 상담 소요 일수
            schedule[i][1] = Integer.parseInt(st.nextToken()); // 수익
        }

        dfs(1, 0);

        int result = -1;
        for (int i = 1; i <= N; i++) {
            result = Math.max(reward[i], result);
        }
        System.out.println(result);
    }

    private static void dfs(int idx, int nowReward) {
        if (idx > N) {
            return;
        }
        int days = schedule[idx][0];
        int cash = schedule[idx][1];
        if (idx + days - 1 <= N && reward[idx + days - 1] < nowReward + cash) {
            reward[idx + days - 1] = nowReward + cash;
            dfs(idx + days, nowReward + cash);
        }
        dfs(idx + 1, nowReward);
    }
}
