// boj 14938 서강그라운드
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 지역 개수 (정점)
        int m = Integer.parseInt(st.nextToken()); // 수색 범위
        int r = Integer.parseInt(st.nextToken()); // 길의 개수 (엣지)

        int[] regionItems = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int region = 1; region <= n; region++) {
            regionItems[region] = Integer.parseInt(st.nextToken());
        }

        ArrayList<ArrayList<int[]>> adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < r; i++) { // 간선 입력
            st = new StringTokenizer(br.readLine());
            int regA = Integer.parseInt(st.nextToken());
            int regB = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj.get(regA).add(new int[]{regB, cost});
            adj.get(regB).add(new int[]{regA, cost}); // 양방향
        }

        int maxItems = -1;
        for (int src = 1; src <= n; src++) {
            int[] dist = new int[n + 1];
            for (int region = 1; region <= n; region++) {
                dist[region] = Integer.MAX_VALUE;
            } // 지역마다 거리 초기화 필요

            dist[src] = 0;
            int items = 0;
            boolean[] visited = new boolean[n + 1];

            PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> {return Integer.compare(o1[1], o2[1]);})); // cost 비교
            pq.offer(new int[]{src, 0});
            while (!pq.isEmpty()) {
                boolean allvisited = true;
                for (int i = 1; i <= n; i++) {
                    if (!visited[i]) {
                        allvisited = false;
                    }
                }
                if (allvisited) break;

                int[] now = pq.poll();
                int nowReg = now[0];
                int nowCost = now[1];
                if (dist[nowReg] < nowCost) {
                    continue;
                }
                if (!visited[nowReg] && nowCost <= m) { // 방문 안했고, 도달가능한 거리
                    items += regionItems[nowReg];
                    visited[nowReg] = true;
                }
                for (int[] info: adj.get(nowReg)) {
                    int nextReg = info[0];
                    int cost = info[1];
                    if (dist[nextReg] > nowCost + cost) {
                        dist[nextReg] = nowCost + cost; // 업데이트
                        pq.offer(new int[]{nextReg, dist[nextReg]});
                    }
                }
            }
            if (maxItems < items) {
                maxItems = items;
            }
        }
        System.out.println(maxItems);
    }
}
