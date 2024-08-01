// #2533 사회망 서비스(SNS)
import java.io.*;
import java.util.*;

public class Main {
    static int early_adapters;
    static ArrayList<ArrayList<Integer>> adj;
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int v = Integer.parseInt(br.readLine());
        adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < v + 1; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 1; i < v; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        parent = new int[v + 1];
        dfs(1);
        // System.out.println(Arrays.toString(parent));
        early_adapters = 0;
        dp_tree(1);

        bw.write(early_adapters + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static void dfs(int node) {
        for (int adj: adj.get(node)) {
            if (adj != parent[node]) { //부모 아닐 때만 
                parent[adj] = node; // 부모기록 (트리 특징: 자식에게 부모는 하나)
                dfs(adj);
            }
        }
    }

    static int dp_tree(int node){
        if (node != 1 && adj.get(node).size() == 1) { // leaf node
            return 0;
        }
        else{
            int adapters = 0;
            for (int adjNode : adj.get(node)) {
                if (adjNode != parent[node]) {
                    adapters += dp_tree(adjNode);
                }
            }
            int size;
            if (node == 1) {
                size = adj.get(node).size();
            } else {
                size = adj.get(node).size() - 1;
            }
            // System.out.println("node = "+ node + " size = " + size);
            if (adapters < size) { // 자식이 한 명이라도 얼리어답터가 아니면
                early_adapters += 1;
                return 1;
            } else {
                return 0; // 모든 자식이 얼리어답터 이면
            }
        }
    }
}
