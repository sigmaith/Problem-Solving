// boj 16118 달빛 여우
import java.io.*;
import java.util.*;

class Node {
    int id;
    double cost;
    int state;

    Node(int id, double cost, int state) {
        this.id = id;
        this.cost = cost;
        this.state = state;
    }
}

public class Main {
    private static final Integer MAX = (int) 1e15;
    static ArrayList<ArrayList<Node>> adj;
    static PriorityQueue<Node> pq;
    static double[] distFox;
    static double[][] distWolf;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adj = new ArrayList<ArrayList<Node>>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<Node>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            adj.get(a).add(new Node(b, d, -1));
            adj.get(b).add(new Node(a, d, -1));
        }

        pq = new PriorityQueue<>((a, b) -> {
            return Double.compare(a.cost, b.cost);
        });

        distFox = new double[n + 1];
        distWolf = new double[2][n + 1];
        for (int i = 1; i <= n; i++) {
            distFox[i] = MAX;
            distWolf[0][i] = MAX;
            distWolf[1][i] = MAX;
        }

        foxDajikstra();
        pq.clear();
        distWolf[0][1] = 0;
        wolfDajikstra();

        // System.out.println(Arrays.toString(distFox));
        // System.out.println(Arrays.deepToString(distWolf));
        int result = 0;
        for (int i = 1; i <= n; i++) {
            double wolf = Math.min(distWolf[1][i], distWolf[0][i]);
            if (distFox[i] < wolf) {
                result++;
            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void foxDajikstra() {
        distFox[1] = 0;
        pq.offer(new Node(1, 0, -1));
        while (!pq.isEmpty()) {
            Node nodeNow = pq.poll();
            int nowId = nodeNow.id;
            double nowCost = nodeNow.cost;
            if (nowCost > distFox[nowId]) {
                continue;
            }
            for (Node nodeNext : adj.get(nowId)) {
                int nextId = nodeNext.id;
                double costRequired = nodeNext.cost;
                if (distFox[nowId] + costRequired < distFox[nextId]) {
                    distFox[nextId] = distFox[nowId] + costRequired;
                    pq.offer(new Node(nextId, distFox[nextId], -1));
                }
            }
        }
    }

    static void wolfDajikstra() {
        pq.offer(new Node(1, 0, 0)); // latest mode) 0: slow, 1: fast
        while (!pq.isEmpty()) {
            Node nodeNow = pq.poll();
            int nowId = nodeNow.id;
            double nowCost = nodeNow.cost;
            int state = nodeNow.state;
            if (state == 0 && nowCost > distWolf[0][nowId]) {
                continue;
            }
            if (state == 1 && nowCost > distWolf[1][nowId]) {
                continue;
            }
            for (Node nodeNext : adj.get(nodeNow.id)) {
                int nextId = nodeNext.id;
                double costRequired = nodeNext.cost;

                if (state == 0 && distWolf[0][nowId] + costRequired / 2 < distWolf[1][nextId]) {
                    distWolf[1][nextId] = distWolf[0][nowId] + costRequired / 2;
                    pq.offer(new Node(nextId, distWolf[1][nextId], 1));
                }
                if (state == 1 && distWolf[1][nowId] + costRequired * 2 < distWolf[0][nextId]) {
                    distWolf[0][nextId] = distWolf[1][nowId] + costRequired * 2;
                    pq.offer(new Node(nextId, distWolf[0][nextId], 0));
                }
            }
        }
    }
}
