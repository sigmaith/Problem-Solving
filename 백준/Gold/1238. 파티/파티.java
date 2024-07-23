// #1238 파티
import java.io.*;
import java.util.*;

class Node {
    private int id;
    private int cost;
    public Node (int id, int cost) {
        this.id = id;
        this.cost = cost;
    }
    public int getId() {
        return this.id;
    }
    public int getCost() {
        return this.cost;
    }
}

public class Main {
    static int v;
    private static int inf = (int)1e9;
    static ArrayList<ArrayList<Node>> adj;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken()); // vertice 개수
        int e = Integer.parseInt(st.nextToken()); // edge 개수
        int x = Integer.parseInt(st.nextToken()); // 파티 장소

        adj = new ArrayList<ArrayList<Node>>();
        for (int i = 0; i <= v; i++) {
            adj.add(new ArrayList<Node>()); // adj 배열 초기화
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj.get(start).add(new Node(end, cost)); // 입력에 따른 adj 배열 세팅 (단방향 조건)
        }

        int result = Integer.MIN_VALUE;
        for (int i = 1; i <= v; i++) {
            if (i == x) continue;
            int tmp = sove_by_dijkstra(i, x) + sove_by_dijkstra(x, i); // 왕복 최단 거리 계산
            result = Math.max(result, tmp);
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int sove_by_dijkstra(int src, int dst) {
        int[] d = new int[v+1];
        for (int i = 1; i <= v; i++) {
            d[i] = inf;
        }
        d[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<Node>
            ((o1, o2) -> Integer.compare(o1.getCost(), o2.getCost()));
        pq.offer(new Node(src, 0));
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            int id = now.getId();
            int cost = now.getCost();
            if (id == dst) {
                break; // 최단 경로 도달 (heap의 특성)
            }
            if (cost > d[id]) {
                continue; // 방문 체크
            }
            for (Node next: adj.get(id)) {
                int nextId = next.getId();
                int moveCost = next.getCost();
                if (d[nextId] > cost + moveCost) {
                    d[nextId] = cost + moveCost; // cost 작을 때만 갱신
                    pq.offer(new Node(nextId, d[nextId])); // 갱신 됐을 때만 offer
                }
            }
        }
        //System.out.println("src = " + src + " ,dst = " + dst + " ,result = " + d[dst]);
        return d[dst];
    }
}
