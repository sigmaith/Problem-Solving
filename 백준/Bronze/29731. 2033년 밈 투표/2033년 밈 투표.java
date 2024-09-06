import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] check = {
            "Never gonna give you up",
            "Never gonna let you down",
            "Never gonna run around and desert you",
            "Never gonna make you cry",
            "Never gonna say goodbye",
            "Never gonna tell a lie and hurt you",
            "Never gonna stop"
        };
        
        int T = Integer.parseInt(br.readLine().trim());
        boolean found = false;
        
        while (T-- > 0) {
            String s = br.readLine();
            boolean matchFound = false;
            
            for (int i = 0; i < 7; i++) {
                if (s.equals(check[i])) {
                    matchFound = true;
                    break;
                }
            }
            
            if (!matchFound) {
                System.out.println("Yes");
                return;
            }
        }
        
        System.out.println("No");
        br.close();
    }
}
