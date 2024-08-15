import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        long n = Long.valueOf(br.readLine());
        long m = Long.valueOf(br.readLine());
        System.out.println(n * m);
    }
}