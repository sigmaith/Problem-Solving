// boj 1351 무한 수열

import java.io.*;
import java.util.*;

public class Main {
    static long N;
    static int P, Q, X, Y;
    static Map<Long, Long> dp = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken()); // (0 ≤ N ≤ 10^12)
        P = Integer.parseInt(st.nextToken()); // (2 ≤ P, Q ≤ 10^9)
        Q = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        br.close();

        // A0 = 1
        dp.put(0L, 1L);

        long answer = getValue(N);
        System.out.println(answer);
    }

    private static long getValue(long x) {
        if (x < 0) {
            return dp.get(0L);
        }
        if (!dp.containsKey(x)) {
            dp.put(x, getValue(x / P - X) + getValue(x / Q - Y));
        }
        return dp.get(x);
    }
}

