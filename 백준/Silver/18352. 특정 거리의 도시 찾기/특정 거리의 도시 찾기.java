// boj 18352 특정 거리의 도시 찾기
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정점 개수
        int M = Integer.parseInt(st.nextToken()); // 간선 개수
        int K = Integer.parseInt(st.nextToken()); // 찾고자 하는 최단거리
        int X = Integer.parseInt(st.nextToken()); // 출발 도시

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
        }

        // 정점 id, cost
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {return Integer.compare(o1[1], o2[1]);});
        int[] d = new int[N + 1];
        Arrays.fill(d, (int)1e9); // 초기화
        d[X] = 0;

        pq.clear();
        pq.offer(new int[]{X, 0});
        while (!pq.isEmpty()) { // 다익스트라
            int[] now = pq.poll();
            int nowId = now[0];
            int nowCost = now[1];
            if (d[nowId]  < nowCost) { // 방문 검사
                continue;
            }
            for (int next: adj.get(nowId)) {
                if (d[next] > nowCost + 1) {
                    d[next] = nowCost + 1;
                    pq.offer(new int[]{next, d[next]});
                }
            }
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (d[i] == K) {
                cnt++;
                System.out.println(i);
            }
        }
        if (cnt == 0) {
            System.out.println(-1);
        }
    }
}
