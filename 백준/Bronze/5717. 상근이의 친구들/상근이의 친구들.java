// boj 5717 상근이의 친구들 
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st;
       while (true) {
           st = new StringTokenizer(br.readLine());
           int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
           if (a == 0 && b == 0) {
               break;
           }
           System.out.println(a + b);
       }
    }
}
