// #2957번: 이진 탐색 트리
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.valueOf(br.readLine());
        TreeSet<Integer> set = new TreeSet<>();

        long count = 0;
        int depth[] = new int[n+2];
        depth[0] = -1;
        depth[n+1] = -1;
        set.add(0);
        set.add(n+1);

        for (int i = 0; i < n; i++) {
            int number = Integer.valueOf(br.readLine());
            depth[number] = Math.max(depth[set.lower(number)], depth[set.higher(number)]) + 1;
            set.add(number);
            count += depth[number];
            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
