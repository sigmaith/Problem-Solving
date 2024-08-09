// #16398번: 행성 연결
import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        private int a;
        private int b;
        private int cost;

        public Node(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        public int getA() {
            return this.a;
        }

        public int getB() {
            return this.b;
        }

        public int getCost() {
            return this.cost;
        }
    }
    public static void main(String[] argsl) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int v = Integer.valueOf(br.readLine());
        int[][] cost = new int[v][v];
        ArrayList<Node> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < v; j++) {
                cost[i][j] = Integer.valueOf(st.nextToken());
                if (j > i) {
                    adj.add(new Node(i, j, cost[i][j]));
                }
            }
        }
        adj.sort((a, b) -> {
            return Integer.compare(a.getCost(), b.getCost());
        });

        int[] p = new int[v];
        for (int i = 0; i < v; i++) {
            p[i] = i;
        }
        long result = solveByMST(p, v, adj);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static long solveByMST(int[] p, int v, ArrayList<Node> adj) {
        int cnt = 0;
        long wholeCost = 0;
        int idx = 0;
        while (true) {
            if (cnt == v-1) {
                break;
            }
            Node node = adj.get(idx);
            int a = node.getA();
            int b = node.getB();
            int cost = node.getCost();
            int a_anc = union_find(a, p);
            int b_anc = union_find(b, p);
            if (a_anc != b_anc) {
                wholeCost += cost;
                p[a_anc] = b_anc;
                cnt += 1;
            }
            idx += 1;
        }
        return wholeCost;
    }

    public static int union_find(int a, int[] p) {
        if (a != p[a]) {
            p[a] = union_find(p[a], p); // 경로 압축
        }
        return p[a];
    }
}
