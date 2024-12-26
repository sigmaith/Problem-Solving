// boj 3584 가장 가까운 공통 조상
import java.io.*;
import java.util.*;

public class Main {
    static int[][] parent;
    static int[] depth;
    static ArrayList<ArrayList<Integer>> graph;
    static int kmax;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int testCnt = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
        for (int t = 0; t < testCnt; t++) {
            int v = Integer.parseInt(br.readLine()); // 정점 개수
            graph = new ArrayList<>(); // 인접 리스트
            for (int i = 0; i <= v; i++) {
                graph.add(new ArrayList<>());
            } // graph 생성 및 초기화

            int root = -1;
            for (int i = 1; i < v; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (i == 1) {
                    root = a;
                }
                /*
                한 간선 당 한 줄에 두 개의 숫자 A B 가 순서대로 주어지는데, 이는 A가 B의 부모라는 뜻입니다. (당연히 정점이 N개인 트리는 항상 N-1개의 간선으로 이루어집니다!)
                 */

                graph.get(a).add(b);
                graph.get(b).add(a);
            } // 인접 리스트
            depth = new int[v + 1]; // 깊이

            int temp = 1;
            kmax = 0;
            while (temp <= v) {
                temp <<= 1;
                kmax += 1;
            }
            parent = new int[kmax + 1][v + 1]; // 조상 정보 sparse table
            dfs(root, 0); // top-down 형식으로 깊이 기록, 부모 기록

            for (int k = 1; k <= kmax; k++) {
                for (int n = 1; n <= v; n++) {
                    parent[k][n] = parent[k-1][parent[k-1][n]];
                }
            }


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
        for (Integer next : graph.get(node)) {
            if (next != parent[0][node]) { // 방문 여부 체크
                parent[0][next] = node;
                dfs(next, dep + 1);
            }
        }
    }

    static int findCostToLCA(int a, int b) {
        if (depth[a] > depth[b]) { // 더 깊은 depth가 b가 되도록 변경
            int temp = a;
            a = b;
            b = temp;
        }

        for (int k = kmax; k >= 0; k--) { // depth 빠르게 맞추기
            if (Math.pow(2, k) <= depth[b] - depth[a]) {
                if (depth[a] <= depth[parent[k][b]]) {
                    b = parent[k][b];
                }
            }
        }

        for (int k = kmax; k >= 0; k--) { // 둘 모두 끌어올리기
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        int LCA = a;
        if (a != b) {
            LCA = parent[0][LCA];
        }

        return LCA;
    }
}
