// #1504번: 특정한 최단 경로
import java.io.*;
import java.util.*;

class Node {
    private int id;
    private int cost;
    public Node(int id, int cost) {
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
    private static final int INF = (int)1e9;
    static int v;
    static ArrayList<ArrayList<Node>> graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList<ArrayList<Node>>(); // 정점, 엣지, 비용 기록
        for (int i = 0; i < v + 1; i++) {
            graph.add(new ArrayList<Node>());
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine()); // 꼭 거쳐야 하는 정점
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken()); 
        
        // 1 -> v1 -> v2 -> v
        // 1 -> v2 -> v1 -> v
        int p1_1 = dijkstra(1, v1), p1_2 = dijkstra(v1, v2), p1_3 = dijkstra(v2, v); 
        int path1 = -1;
        if (p1_1 != -1  && p1_2 != -1 && p1_3 != -1) { // 75% -> 100%
            path1 = p1_1 + p1_2 + p1_3;
        }

        int p2_1 = dijkstra(1, v2), p2_2 = dijkstra(v2, v1), p2_3 = dijkstra(v1, v);
        int path2 = -1;
        if (p2_1 != -1 && p2_2 != -1 && p2_3 != -1) { // 75% -> 100%
            path2 = p2_1 + p2_2 + p2_3;
        }
        int result = Math.min(path1, path2);
        
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static int dijkstra(int src, int dst) {
        int[] d = new int[v + 1]; 
        for (int i = 1; i < v + 1; i++) {
            d[i] = INF;
        }
        d[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> {return Integer.compare(a.getCost(), b.getCost());});
        pq.offer(new Node(src, 0));

        int result = -1;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int presentId = now.getId();
            int presentCost = now.getCost();
            if (presentCost > d[presentId]) {
                continue;
            }
            if (presentId == dst) {
                result = presentCost;
                break;
            }
            for (Node node: graph.get(presentId)) {
                int futureId = node.getId();
                int cost = node.getCost();
                // 업데이트 됐을 때만 pq에 넣기
                if (d[futureId] > presentCost + cost) {
                    int futureCost = presentCost + cost;
                    d[futureId] = futureCost;
                    pq.offer(new Node(futureId, futureCost));
                }
            }
        }
        // System.out.println("result = " + result);

        return result;
    }
}
