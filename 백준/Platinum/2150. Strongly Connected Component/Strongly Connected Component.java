// # 2150 Strongly Connected Component - Kosaraju's Algorithm

import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int id, anc[];
    static ArrayList<ArrayList<Integer>> SCC;
    static ArrayList<ArrayList<Integer>> adj;
    static boolean finished[];
    static Deque<Integer> stack; 

    private static int dfs(int now){
        anc[now] = ++id;
        stack.addLast(now);

        int parent = anc[now];
        for( int next : adj.get(now)){
            if(anc[next] == 0){
                parent = Math.min(parent, dfs(next));
            }
            else if(!finished[next]){
                parent = Math.min(parent, anc[next]);
            }
        }
        if(parent == anc[now]){
            ArrayList<Integer> scc = new ArrayList<>();
            while(true){
                int t = stack.pollLast();
                scc.add(t);
                finished[t] = true;
                if(t == now) break;
            }
            Collections.sort(scc);
            SCC.add(scc);
        }
        return parent;
    }

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken()); // vertice
        int e = Integer.parseInt(st.nextToken()); // edge

        anc = new int[10001];
        SCC = new ArrayList<>();
        adj = new ArrayList<>();
        for(int i=0; i<=v; i++){
            adj.add(new ArrayList<>()); //adj.get(i) = new ArrayList<>();
        }
        finished = new boolean[10001];
        stack = new ArrayDeque<Integer>(); //Deque<Integer> -> new ArrayDeque<Integer>();

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adj.get(start).add(end);
        }

        for(int i=1; i<=v; i++){
            if(anc[i] == 0) dfs(i);
        }

        Collections.sort(SCC, new Comparator<ArrayList<Integer>>(){ // 어려웡!!!!!!
            @Override
            public int compare(ArrayList<Integer> scc1, ArrayList<Integer> scc2){
                return Integer.compare(scc1.get(0), scc2.get(0));
            }
        });
        bw.write(SCC.size() + "\n");
        for (int i = 0; i < SCC.size(); i++) {
            for (int j = 0; j < SCC.get(i).size(); j++) {
                bw.write(SCC.get(i).get(j) + " ");
            }
            bw.write("-1\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
