import java.util.*;
import java.io.*;

enum palStatus{
    notComputed,
    True,
    False
};

public class Main{
    static palStatus[][] dp;
    static int[] A;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[n];
        for (int i = 0; i < n; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        //System.out.println(Arrays.toString(A));

        dp = new palStatus[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                dp[i][j] = palStatus.notComputed;
            }
        }
        //System.out.println(Arrays.deepToString(dp));
        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++){
            int s, f;
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            f = Integer.parseInt(st.nextToken());

            bw.write(isPal(s-1, f-1) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static int isPal(int s, int f){
        if (s >= f) {
            return 1;
        }
        if (dp[s][f] == palStatus.notComputed){
            if(A[s] == A[f] && isPal(s+1, f-1) == 1){
                dp[s][f] = palStatus.True;
                return 1;
            }
            else return 0;
        }
        return dp[s][f] == palStatus.True ? 1 : 0;
    }
}