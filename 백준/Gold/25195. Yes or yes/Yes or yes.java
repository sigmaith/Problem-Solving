// boj 25195 Yes or yes
import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> adj;
    static HashSet<Integer> placeOfGomGom;
    static String result = "Yes";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // vertice
        int m = Integer.parseInt(st.nextToken()); // edge

        adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adj.get(start).add(end);
        }

        int numOfGomGom = Integer.parseInt(br.readLine());
        placeOfGomGom = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numOfGomGom; i++) { 
            placeOfGomGom.add(Integer.parseInt(st.nextToken()));
        }

        if (!placeOfGomGom.contains(1)) {
            dfs(1, 0);
        }
        bw.write(result + "\n");
        bw.close();
        br.close();
    }

    static void dfs(int start, int metGomGom) {
        int count = 0;
        for (int next : adj.get(start)) {
            count++;
            if (!placeOfGomGom.contains(next)) {
                dfs (next, 0);
            }
        }
        if (count == 0 && start != 1 && metGomGom == 0) {
            result = "yes";
        }
        if (count == 0 && start == 1 && metGomGom == 0) {
            result = "yes";
        }
    }
}
