import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long R = Long.valueOf(br.readLine());
        Long S = Long.valueOf(br.readLine());
        Long result = (R * 8 + S * 3) - 28;
        System.out.println(result);
    }
}