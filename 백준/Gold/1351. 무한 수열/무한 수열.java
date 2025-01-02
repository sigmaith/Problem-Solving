// boj 1351 무한 수열

import java.io.*;
import java.util.*;

public class Main {
    static long N;
    static int P, Q;
    static Map<Long, Long> dp = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken()); // (0 ≤ N ≤ 10^12)
        P = Integer.parseInt(st.nextToken()); // (2 ≤ P, Q ≤ 10^9)
        Q = Integer.parseInt(st.nextToken());
        br.close();

        // A0 = 1
        dp.put(0L, 1L);

        long answer = getValue(N);
        System.out.println(answer);
    }

    private static long getValue(long x) {
        if (dp.containsKey(x)) {
            return dp.get(x);
        } else {
            long value = getValue(x / P) + getValue(x / Q);
            dp.put(x, value);
            return dp.get(x);
        }
    }
}

