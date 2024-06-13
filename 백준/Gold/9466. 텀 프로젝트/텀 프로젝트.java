// #9466 텀프로젝트

import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int id;
    static int[] adj;
    static int[] anc;
    static boolean[] finished;
    static Deque<Integer> stack; //ArrayDeque stack;
    static ArrayList<ArrayList<Integer>> SCC;
 
    private static int dfs(int now){
        anc[now] = ++id;
        stack.addLast(now);

        int parent = anc[now];
        int next = adj[now];
        if(anc[next] == 0) parent = Math.min(parent, dfs(next));
        else if(!finished[next]) parent = Math.min(parent, anc[next]);

        if(parent == anc[now]){
            ArrayList<Integer> scc = new ArrayList<>();
            while(true){    
                int t = stack.pollLast();
                scc.add(t);
                finished[t] = true;
                if( t == now ) break;
            }
            SCC.add(scc);
        }

        return parent;
    }

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++){
            int n = Integer.parseInt(br.readLine());
            id = 0;
            adj = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                adj[j] = Integer.parseInt(st.nextToken()); 
            }
            anc = new int[n+1];
            finished = new boolean[n+1];
            stack = new ArrayDeque<>();
            SCC = new ArrayList<>();

            for (int id=1; id<=n; id++){
                if (anc[id] == 0){ dfs(id); }
            }

            int count=0;
            for (int k=0; k<SCC.size(); k++){
                if (SCC.get(k).size() == 1 && adj[SCC.get(k).get(0)] != SCC.get(k).get(0))
                    count++;
            }
            bw.write(count + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
