// boj 2270 줄번호
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

        if ((N * M) % 2 == 0) {
            System.out.println(N * M / 2);
        } else {
            System.out.println((N * M - 1) / 2);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

