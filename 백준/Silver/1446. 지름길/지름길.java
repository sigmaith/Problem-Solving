// boj 1446 지름길
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 지름길의 개수
        int D = Integer.parseInt(st.nextToken()); // 고속도로의 길이

        int[] d = new int[10_001];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {return Integer.compare(o1[1], o2[1]);});

        ArrayList<ArrayList<int[]>> adj = new ArrayList<>(D+1);
        for (int i = 0; i <= D; i++) {
            adj.add(new ArrayList<>());
            d[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (start > D) {
                continue;
            }
            adj.get(start).add(new int[]{end, cost});
            if (start != 0) {
                pq.offer(new int[]{start, start});
            } else if (end <= D) {
                pq.offer(new int[]{end, cost});
            }
            if (d[start] > start) {
                d[start] = start;
            }
        }
        pq.offer(new int[]{D, D});

        int result = Integer.MAX_VALUE;
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            int nowId = now[0];
            int nowCost = now[1];
            if (d[nowId] < nowCost) {
                continue;
            }
            for (int[] info: adj.get(nowId)) {
                int nextId = info[0];
                int nextCost = nowCost + info[1];
                if (nextId <= D && d[nextId] > nextCost) {
                    d[nextId] = nextCost;
                    pq.offer(new int[]{nextId, nextCost});
                }
            }
            for (int i = nowId + 1; i <= D; i++) {
                if (!adj.get(i).isEmpty()) {
                    for (int[] info: adj.get(i)) {
                        if (d[i] > nowCost + (i - nowId)) {
                            d[i] = nowCost + (i - nowId);
                            pq.offer(new int[]{i, d[i]});
                        }
                    }
                }
            }
            if (d[D] > nowCost + D-nowId) {
                d[D] = nowCost + D-nowId;
            }
        }
        System.out.println(d[D]);
    }
}
