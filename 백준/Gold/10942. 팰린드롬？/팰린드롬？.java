import java.util.*;
import java.io.*;

enum palStatus {
    notComputed,
    True,
    False
};

public class Main {
    static palStatus[][] dp;
    static int[] A;

    public static void main(String[] args) throws IOException {
        //
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // System.out.println(Arrays.toString(A));

        dp = new palStatus[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = palStatus.notComputed;
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            int s, f;
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            f = Integer.parseInt(st.nextToken());

            int result = isPal(s - 1, f - 1) == true ? 1 : 0;
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }

    public static boolean isPal(int i, int j) {
        // base case : 두 index가 같아진다면 true를 반환
        if (i >= j)
            return true;

        if (dp[i][j] == palStatus.notComputed) {
            if (A[i] == A[j] && isPal(i + 1, j - 1)) {
                dp[i][j] = palStatus.True;
            } else {
                dp[i][j] = palStatus.False;
            }
        }
        // System.out.println(i + " ~ " + j + " :" + dp[i][j]);
        return dp[i][j] == palStatus.True ? true : false;
    }
}