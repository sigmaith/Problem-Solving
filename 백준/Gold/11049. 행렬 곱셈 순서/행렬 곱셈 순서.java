// #11049 행렬 곱셈 순서
import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int[][] dp;

    public static int[][] minmult(int[] d, int n){
        for (int diagonal=1; diagonal<n; diagonal++){
            for (int i=1; i<n-diagonal+1; i++){
                int j = i + diagonal;
                dp[i][j] = minimum(dp, d, i, j);
            }
        }
        return dp;
    }
    public static int minimum(int[][] dp, int[] d, int i, int j){
        int minValue = Integer.MAX_VALUE;
        for (int k=i; k<j; k++){
            int value = dp[i][k] + dp[k+1][j] + d[i - 1] * d[k] * d[j];
            if (minValue > value){
                minValue = value;
            }
        }
        return minValue;
    }
    public static void main(String[] args)throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] d = new int[N+1];
        dp = new int[N + 1][N + 1];
        

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (i == 0) {d[i] = m;}
            d[i + 1] = n; // 
        }   

        // for (int i = 0; i < N; i++) {
        //     bw.write(d[i] + " ");
        // }
        // bw.write("\n");
        // bw.flush();

        minmult(d, N);

        // for(int i=0; i<=N; i++){
        //     for(int j=0; j<=N; j++){
        //         bw.write(dp[i][j] + " ");
        //     }
        //     bw.write("\n");
        // }
        // bw.flush();

        bw.write(dp[1][N] + "\n");
        bw.flush();
        br.close();
        bw.close();
   }
}
