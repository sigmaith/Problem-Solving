// boj 11365 !밀비 급일
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String str = br.readLine();
            if (str.equals("END")) {
                break;
            }
            for (int idx = str.length() - 1; idx >= 0; idx--) {
                System.out.print(str.charAt(idx));
            }
            System.out.println();
        }
    }
}
