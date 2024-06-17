// #17256 달달함이 흘러넘쳐
import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());    
        int a_x = Integer.parseInt(st.nextToken());
        int a_y = Integer.parseInt(st.nextToken());
        int a_z = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int c_x = Integer.parseInt(st.nextToken());
        int c_y = Integer.parseInt(st.nextToken());
        int c_z = Integer.parseInt(st.nextToken());

        int b_x = c_x - a_z;
        int b_y = c_y / a_y;
        int b_z = c_z - a_x;

        bw.write(b_x + " " + b_y + " " + b_z + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}
