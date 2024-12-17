// boj 1029 그림 교환
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] prices;
    static int dp[][][];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        prices = new char[N][N];
        for (int i = 0; i < N; i++){
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                prices[i][j] = str.charAt(j);
            }
        }

        dp = new int[1 << N][N][10]; // 상인 방문, 현재 상인
//        for (int i = 0; i < (1 << N); i++) {
//            Arrays.fill(dp[i], -1);
//        }
        System.out.println(purchase(1, 0,'0', 0));
    }

    private static int purchase(int visited, int current, char priceRecent, int count) {
        if (dp[visited][current][priceRecent - '0'] != 0) { // 기록
            return dp[visited][current][priceRecent - '0'];
        }
        // base-case: 더이상 아무 상인에게도 팔지 못할때.
        int max = 0;

        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) == 0 && prices[current][i] >= priceRecent) {
                max = Math.max(max, purchase((visited | (1 << i)), i,  prices[current][i], count + 1));
            }
        }

        dp[visited][current][priceRecent - '0'] = 1 + max; // 일단 현재 1 + 다음 상인에게 더 팔수 있는지
        return dp[visited][current][priceRecent - '0'];
    }
}
