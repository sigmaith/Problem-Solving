
// #1761 정점들의 거리
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
    static int[] depth;
    static boolean[] isCounted;
    static ArrayList<ArrayList<Node>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        parent = new int[n + 1][2]; // 부모 노드 정보 + cost 저장
        depth = new int[n + 1]; // 깊이
        isCounted = new boolean[n + 1]; // 깊이가 계산 되었는지 여부
        graph = new ArrayList<ArrayList<Node>>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Node>());
        } // graph 생성 및 초기화

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }

        dfs(1, 0); // top-down 형식으로 깊이 기록, 부모 기록
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int result = lca(a, b); 
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int x, int dep) {
        isCounted[x] = true; // 방문 체크
        depth[x] = dep;
        for (Node node : graph.get(x)) {
            int id = node.getId();
            int cost = node.getCost();
            if (isCounted[id]) { // 방문 여부 체크
                continue;
            }
            parent[id][0] = x;
            parent[id][1] = cost;
            dfs(id, dep + 1);
        }
    }

    static int lca(int a, int b) {
        int result = 0;
        while (depth[a] != depth[b]) { // 깊이가 더 깊은것을 위로 끌어올리기 (높이 맞추기)
            if (depth[a] > depth[b]) {
                result += parent[a][1];
                a = parent[a][0];
            } else {
                result += parent[b][1];
                b = parent[b][0];
            }
        }
        while (a != b) { // a,b 같이 점프 뿅뿅뿅
            result += parent[a][1];
            result += parent[b][1];
            a = parent[a][0];
            b = parent[b][0];
        }
        return result; // 같아지면 반환
    }
}
