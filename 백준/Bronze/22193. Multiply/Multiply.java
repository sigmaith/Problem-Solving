import java.io.*;
import java.util.*;
import java.math.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        BigInteger a = new BigInteger(br.readLine());
		BigInteger b = new BigInteger(br.readLine());
        System.out.println(a.multiply(b));
    }
}