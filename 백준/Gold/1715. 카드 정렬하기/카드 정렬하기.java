// 1715번: 카드 정렬하기
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.valueOf(br.readLine()); // 1 ~ 100_000

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a, b);
        });

        int num = -1;
        for (int i = 0; i < n; i++) {
            num = Integer.valueOf(br.readLine()); // 1000이하 자연수
            pq.offer(num);
        }

        if (n == 1) {
            bw.write(0 + "\n"); // 0! 95% -> 100%
            bw.flush();
            return;
        }

        long sum = 0;
        while(true) {
            int a = pq.poll();
            int b = pq.poll();
            sum += (a + b);
            if (pq.isEmpty()) {
                break;
            }
            else {
                pq.offer(a + b);
            }
        }
        bw.write(sum + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
