// #6497번: 전력난
import java.io.*;
import java.util.*;

public class Main {
    static int[] p;
    static class Node {
        private int id1;
        private int id2;
        private int cost;

        public Node(int id1, int id2, int cost) {
            this.id1 = id1;
            this.id2 = id2;
            this.cost = cost;
        }

        public int getId1() {
            return this.id1;
        }

        public int getId2() {
            return this.id2;
        }

        public int getCost() {
            return this.cost;
        }
    }
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int v = -1, e = -1;
        while (true) {
            st = new StringTokenizer(br.readLine().strip());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            if (v == 0 && e == 0) {
                break;
            }
            int savedCost = calculateSavedElectic(v, e);
            // System.out.println("savedCost = " + savedCost);
            bw.write(savedCost + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int calculateSavedElectic(int v, int e) throws Exception{
        PriorityQueue<Node> edges = new PriorityQueue<Node>((a, b) -> {
            return Integer.compare(a.getCost(), b.getCost());
        });

        int wholeCost = 0; // 다 켜면 소비되는 전력
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.offer(new Node(a, b, cost));
            wholeCost += cost;
        }

        p = new int[v]; // 조상
        for (int i = 0; i < v; i++) {
            p[i] = i;
        } // 초기화

        int count = 0;
        int saveCost = 0;
        while (count < v-1) { // 6 ㅋㅋㅋ ㅠㅠ 울고싶다
            Node node = edges.poll();
            int v1 = node.getId1();
            int v2 = node.getId2();
            int cost = node.getCost();

            int anc1 = findAncestor(v1);
            int anc2 = findAncestor(v2);
            if (anc1 != anc2) {
                p[anc1] = anc2;
                saveCost += cost;
                count += 1;
            }
        }

        int result = wholeCost - saveCost;
        // System.out.println("result = " + result);
        return result;
    } 

    static int findAncestor(int v) {
        if (p[v] != v) {
            p[v] = findAncestor(p[v]);
        }
        return p[v];
    }
}
