// #13549 숨바꼭질 3
import java.io.*;
import java.util.*;

class Node {
    private int idx;
    private int cost;
    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
    public int getIdx() {
        return idx;
    }
    public int getCost() {
        return cost;
    }
}
public class Main {
    private static final int INF = (int)1e9;
    public static int dijkstra(int src, int dst) {
        int[] d = new int[100_000 + 1];
        for (int i = 0; i <= 100_000; i++) {
            d[i] = INF;
        }
        d[src] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.getCost() - o2.getCost());
        pq.offer(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.getIdx();
            int cost = node.getCost();
            if (now == dst) {
                break;
            }
            if (cost < d[now]) {
                continue;
            }
            if (now - 1 >= 0 && d[now] + 1 < d[now - 1]) {
                d[now - 1] = d[now] + 1;
                pq.offer(new Node(now - 1, d[now - 1]));
            }
            if (now + 1 <= 100_000 && d[now] + 1 < d[now + 1]) {
                d[now + 1] = d[now] + 1;
                pq.offer(new Node(now + 1, d[now + 1]));
            }
            if (now * 2 <= 100_000 && d[now] < d[now * 2]) {
                d[now * 2] = d[now];
                pq.offer(new Node(now * 2, d[now * 2]));
            }
        }
        return d[dst];
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int result = dijkstra(n, k);
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
