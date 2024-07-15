// #1916 최소비용 구하기
import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    private int idx;
    private int dist;
    public Node(int idx, int dist) {
        this.idx = idx;
        this.dist = dist;
    } 
    public int getIdx() {
        return this.idx;
    }
    public int getDist() {
        return this.dist;
    }
    // 거리(비용)이 더 짧은 것이 더 높은 우선순위를 가지도록 설정
    @Override
    public int compareTo(Node other) {
        if (this.dist < other.dist) {
            return -1; // 음수가 더 높은 우선순위 
        }
        return 1;
    }
}

public class Main {
    public static final int INF = (int)1e9; 
    static int n;
    static ArrayList<ArrayList<Node>> adj;

    public static int dijkstra(int src, int dst) {
        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            d[i] = INF;
        }
        d[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll(); 
            int now = node.getIdx();
            int dist = node.getDist();
            if (now == dst) {
                break;
            }

            if (dist > d[now]) {
                continue; // 방문 체크
            }
            for (Node n : adj.get(now)) {
                int next = n.getIdx();
                int nextDist = n.getDist();
                if (d[now] + nextDist < d[next]) {
                    d[next] = d[now] + nextDist;
                    pq.offer(new Node(next, d[next]));
                }
            }
        }
        return d[dst];
    }
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        adj = new ArrayList<ArrayList<Node>>();
        for (int i = 0; i < n+1; i++) {
            ArrayList<Node> arr = new ArrayList<Node>();
            adj.add(arr);
        } // initialization 

        int start, end, cost;
        for (int i = 0; i < m; i++) { 
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            Node node = new Node(end, cost);
            adj.get(start).add(node);
        } // bus information
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int src = Integer.parseInt(st.nextToken());
        int dsc = Integer.parseInt(st.nextToken());

        int result = dijkstra(src, dsc);
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
