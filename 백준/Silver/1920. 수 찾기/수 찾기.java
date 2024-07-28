// #1920 수 찾기
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        HashSet<Integer> hash = new HashSet<>();

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            hash.add(Integer.parseInt(st.nextToken()));
        }
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int x; 
        for (int i = 0; i < m; i++) {
            x = Integer.parseInt(st.nextToken());
            if (hash.contains(x)) bw.write("1\n");
            else bw.write("0\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
