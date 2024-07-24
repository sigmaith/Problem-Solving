// #11437
import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] depth;
    static boolean[] isCounted;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        parent = new int[n + 1]; // 부모 노드 정보
        depth = new int[n + 1]; // 깊이
        isCounted = new boolean[n + 1]; // 깊이가 계산 되었는지 여부
        graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
        } // graph 생성 및 초기화

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dfs(1, 0); // top-down 형식으로 깊이 기록, 부모 기록
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int result = lca(a, b); // 높이 맞추고, a,b 같이 점프 뿅뿅뿅
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int x, int dep) {
        isCounted[x] = true;
        depth[x] = dep;
        for (int y : graph.get(x)) {
            if (isCounted[y]) {
                continue;
            }
            parent[y] = x;
            dfs(y, dep + 1);
        }
    }

    static int lca(int a, int b) {
        while (depth[a] != depth[b]) { // 깊이가 더 깊은것을 위로 끌어올리기
            if (depth[a] > depth[b]) {
                a = parent[a];
            }
            else {
                b = parent[b];
            }
        }
        while (a != b) { // 같이 점프
            a = parent[a];
            b = parent[b];
        }
        return a; // 같아지면 반환
    }
}
