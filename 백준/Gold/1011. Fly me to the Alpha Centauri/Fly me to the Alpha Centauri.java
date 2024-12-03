// boj 1011 Fly me to the Alpha Centauri
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testCase; t++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = y - x;

            int src = (int) Math.sqrt((double)d);
            if (d == src * src) {
                System.out.println(2 * src - 1);
            } else if (d <= src * src + src) {
                System.out.println(2 * src);
            } else {
                System.out.println(2 * src + 1);
            }
        }
    }
}
