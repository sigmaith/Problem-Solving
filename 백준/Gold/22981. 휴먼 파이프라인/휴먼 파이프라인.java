// boj 22981: 휴먼 파이프라인
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken().trim());
        long K = Long.parseLong(st.nextToken().trim());

        int[] v = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            v[i] = Integer.parseInt(st.nextToken().trim());
        }
        Arrays.sort(v);
        // System.out.println(Arrays.toString(v));

        long result = (long)1e18;
        for (int i = 1; i <= N - 1; i++) {
            long team1 = (long)v[0] * i;
            long team2 = (long)v[i] * (N - i);
            long speed = team1 + team2;
            long tmpResult = 0;
            if (K % speed == 0) tmpResult = K / speed;
            else tmpResult = K / speed + 1;
            // System.out.println("tmpResult = " + tmpResult);
            result = Math.min(result, tmpResult);
        }

        System.out.println(result);
    }
}
