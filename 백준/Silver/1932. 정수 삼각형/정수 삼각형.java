// boj 1932 정수 삼각형
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] pyramid = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                pyramid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // System.out.println(Arrays.deepToString(pyramid));

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    pyramid[i][j] += pyramid[i-1][0];
                } else if (j == i) {
                    pyramid[i][j] += pyramid[i-1][j-1];
                } else {
                    pyramid[i][j] += Math.max(pyramid[i-1][j-1], pyramid[i-1][j]);
                }
            }
        }

        int result = pyramid[n-1][0];
        for (int j = 1; j < n; j++) {
            result = Math.max(result, pyramid[n-1][j]);
        }
        System.out.println(result);
    }
}
