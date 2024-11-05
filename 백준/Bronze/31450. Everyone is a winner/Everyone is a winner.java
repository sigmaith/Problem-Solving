import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); 
        int k = Integer.parseInt(st.nextToken()); 

        if (m % k == 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

    }
}