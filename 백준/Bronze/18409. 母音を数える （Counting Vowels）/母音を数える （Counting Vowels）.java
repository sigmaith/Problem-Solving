// boj 18409 母音を数える (Counting Vowels)
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            switch (s.charAt(i)) {
                case 'a': case 'e': case 'i': case 'o': case 'u':
                    cnt++;
            }
        }
        System.out.println(cnt);
    }
}
