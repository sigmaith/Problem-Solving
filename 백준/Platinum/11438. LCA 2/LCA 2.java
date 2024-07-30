// #11438 LCA2
import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> adj;
    static int[] depth;
    static int[][] parent;
    static int kmax;
    static int check;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int v = Integer.parseInt(br.readLine());    
        adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < v+1; i++) {
            adj.add(new ArrayList<Integer>());
        }

        for (int i = 1; i < v; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        depth = new int[v+1];
        parent = new int[25][v + 1];
        check_depth_parent(1, 0);
        
        int tmp = 1; // 2의 몇승까지? *************************
        kmax = 0; // *************************
        while (tmp <= v) {
            tmp <<= 1;
            kmax += 1;
        }
        for (int k = 1; k <= kmax ; k++) {
            for (int n = 1; n <= v; n++) {
                parent[k][n] = parent[k-1][parent[k-1][n]];
            }
        }

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int result = find_LCA(a, b);
            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
    static void check_depth_parent(int now, int dep) {
        depth[now] = dep;
        if (dep > kmax) {
            kmax = dep; // 최대 깊이 저장
        }
        for (int next : adj.get(now)) {
            if (next != parent[0][now]){ // 부모가 아닐 때만 탐색!!! *************************
                parent[0][next] = now;
                check_depth_parent(next, dep + 1);
            }
        }
    }
    static int find_LCA(int a, int b) {
        if (depth[a] > depth[b]) { // 더 깊은 depth가 b가 되도록 변경
            int temp = a;
            a = b;
            b = temp;
        }
        for (int k = kmax; k >= 0; k--) { // depth 맞추기 *************************
            if (Math.pow(2, k) <= depth[b] - depth[a]) { // *************************
                if (depth[a] <= depth[parent[k][b]]) {
                    b = parent[k][b];
                }
            }
        }
        for (int k = kmax; k >= 0; k--) { // 이 코드가 나는 머리속으로 나오질 않아서 while을 썼었다..
            if (parent[k][a] != parent[k][b]) {  // *************************
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        int LCA = a;
        if (a != b) 
            LCA = parent[0][LCA];

        return LCA;        
    }
}
