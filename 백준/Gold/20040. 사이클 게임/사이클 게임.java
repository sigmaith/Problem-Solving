// #20040 사이클 게임
import java.io.*;
import java.util.*;

public class Main {
    static int[] anc;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.valueOf(st.nextToken());
        int e = Integer.valueOf(st.nextToken());
        anc = new int[v];
        for (int i = 0; i < v; i++) {
            anc[i] = i;
        }

        int result = 0;
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.valueOf(st.nextToken());
            int b = Integer.valueOf(st.nextToken());

            int anc_a = union_find(a);
            int anc_b = union_find(b);

            if (anc_a != anc_b) {
                anc[anc_a] = anc_b;
            }

            else if (result == 0){
                result = i + 1;
            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int union_find(int id) {
        if (anc[id] != id) {
            anc[id] = union_find(anc[id]);
        }
        return anc[id];
    }
}