// #12021 보물찾기
import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] main)throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        bw.write(Math.sqrt((double)a*b) + " " +Math.sqrt((double)a*b));

        br.close();
        bw.flush();
        bw.close();
    }
}
