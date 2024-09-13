// boj 27851 Watching Mooloo
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long[] days = new long[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            days[i] = Long.parseLong(st.nextToken());
        }

        long ans = 1 + k;
        for (int i = 1; i < n; i++) {
            ans += Math.min(1 + k, days[i] - days[i-1]);
        }
        
        System.out.println(ans);
    }
}
