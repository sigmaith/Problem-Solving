// #3733 Shares
import java.io.*;
import java.util.*;

public class Main {
    static void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true){
            String cur = br.readLine();
            if (cur == null) break;
            StringTokenizer st = new StringTokenizer(cur);
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            sb.append(s/(n+1)).append('\n');
        }
        System.out.println(sb);
    }
    public static void main(String[] args) throws Exception{
        solution();
    }
}
