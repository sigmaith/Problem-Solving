// boj 1761 정점들의 거리
import java.io.*;
import java.util.*;

class Node{
    private int id;
    private int cost;
    public Node(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }
    public int getId() {return this.id;}
    public int getCost() {return this.cost;}
}

public class Main {
    static int[][] parent;
    static int[][] cost;
    static int[] depth;
    static ArrayList<ArrayList<Node>> graph;
    static int kmax;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int v = Integer.parseInt(br.readLine()); // 정점 개수
        graph = new ArrayList<>(); // 인접 리스트
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        } // graph 생성 및 초기화

        for (int i = 1; i < v; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        } // 인접 리스트

        depth = new int[v + 1]; // 깊이
        int temp = 1;
        kmax = 0;
        while (temp <= v) {
            temp <<= 1;
            kmax += 1;
        }
        parent = new int[25][v + 1]; // 조상 정보 sparse table
        cost = new int[25][v + 1]; // 비용 정보 sparse table

        dfs(1, 0); // top-down 형식으로 깊이 기록, 부모 기록

        for (int k = 1; k <= kmax; k++) {
            for (int n = 1; n <= v; n++) {
                parent[k][n] = parent[k-1][parent[k-1][n]];
                cost[k][n] = cost[k-1][n] + cost[k-1][parent[k-1][n]];
            }
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int result = findCostToLCA(a, b);
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int node, int dep) {
        depth[node] = dep;
        for (Node next : graph.get(node)) {
            int nextId = next.getId();
            int nextCost = next.getCost();
            if (nextId != parent[0][node]) { // 방문 여부 체크
                parent[0][nextId] = node;
                cost[0][nextId] = nextCost;
                dfs(nextId, dep + 1);
            }
        }
    }

    static int findCostToLCA(int a, int b) {
        if (depth[a] > depth[b]) { // 더 깊은 depth가 b가 되도록 변경
            int temp = a;
            a = b;
            b = temp;
        }

        int ret = 0;
        for (int k = kmax; k >= 0; k--) { // depth 빠르게 맞추기
            if (Math.pow(2, k) <= depth[b] - depth[a]) {
                if (depth[a] <= depth[parent[k][b]]) {
                    ret += cost[k][b];
                    b = parent[k][b];
                }
            }
        }

        for (int k = kmax; k >= 0; k--) { // 둘 모두 끌어올리기
            if (parent[k][a] != parent[k][b]) {
                ret += (cost[k][a] + cost[k][b]);
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        int LCA = a;
        if (a != b) {
            ret += (cost[0][a] + cost[0][b]);
            LCA = parent[0][LCA];
        }

        return ret;
    }
}
