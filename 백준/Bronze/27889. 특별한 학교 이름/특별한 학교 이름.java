// NLCS: North London Collegiate School
// BHA: Branksome Hall Asia
// KIS: Korea International School
// SJA: St. Johnsbury Academy
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str = br.readLine();
        if (str.equals("NLCS")) System.out.println("North London Collegiate School");
        if (str.equals("BHA")) System.out.println("Branksome Hall Asia");
        if (str.equals("KIS")) System.out.println("Korea International School");
        if (str.equals("SJA")) System.out.println("St. Johnsbury Academy");
    }
}