// boj 10282 해킹
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());
        for (int test = 0; test < testCase; test++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken()),
                    src = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
            for (int i = 0; i <= v; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()),
                        cost = Integer.parseInt(st.nextToken());
                adj.get(end).add(new int[]{start, cost});
            }

            int[] dist = new int[v+1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {return Integer.compare(o1[1], o2[1]);});
            pq.offer(new int[]{src, 0});
            while (!pq.isEmpty()) {
                int[] now = pq.poll();
                int nowId = now[0];
                int nowCost = now[1];
                if (dist[nowId] < nowCost) {
                    continue;
                }
                for (int[] info: adj.get(nowId)) {
                    int nextId = info[0];
                    int cost = info[1];
                    if (dist[nextId] > nowCost + cost) {
                        dist[nextId] = nowCost + cost;
                        pq.offer(new int[]{nextId, dist[nextId]});
                    }
                }
            }

            int hackedCnt = 0;
            int sec = 0;
            for (int i = 1; i <= v; i++) {
                if (dist[i] != Integer.MAX_VALUE) {
                    hackedCnt++;
                    sec = Math.max(sec, dist[i]);
                }
            }
            System.out.println(hackedCnt + " " + sec);
        }
    }
}