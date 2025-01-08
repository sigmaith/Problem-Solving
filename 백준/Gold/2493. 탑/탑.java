// boj 2493 탑

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] towers = new int[N + 1]; // 탑들

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            towers[i] = Integer.parseInt(st.nextToken());
        }

        Deque<int[]> dq = new ArrayDeque<>();
        int[] record = new int[N]; // 기록
        Arrays.fill(record, 0);
        for (int i = N; i >= 1; i--) {
            while (!dq.isEmpty() && dq.peekFirst()[1] < towers[i]) { // 값이 작을 때까지
                int[] info = dq.pollFirst();
                record[info[0] - 1] = i;
            }
            dq.offerFirst(new int[]{i, towers[i]});
        }

        System.out.println(Arrays.stream(record).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }
}

