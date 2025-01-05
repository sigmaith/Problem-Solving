// boj 2015 수들의 합 4

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 200,000
        long K = Long.parseLong(st.nextToken()); // |K| ≤ 2,000,000,000 -> long

        st = new StringTokenizer(br.readLine());
        Map<Long, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0L, 1); // 초기의 상태는 0. 이전 numbers 정수배열의 초기 상태

        long cnt = 0; // 20만개의 수가 모두 0이고, K도 0인 경우 엄청나게 많아짐
        long prefixSum = 0; // 20억은 거의 int 아슬아슬.. 최대 20억

        for (int i = 1; i <= N; i++) {
            prefixSum += Long.parseLong(st.nextToken());
            cnt += prefixSumCount.getOrDefault(prefixSum - K, 0); // 현재까지의 합에 -K 한 값이 이전까지 몇번 출현했는가?
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }

        System.out.println(cnt);
    }
}

