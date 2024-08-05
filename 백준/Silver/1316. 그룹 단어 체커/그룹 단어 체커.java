// #1316번: 그룹 단어 체커
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = 0;
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            if (check()) {
                count ++;
            }
        }
        
        bw.write(count + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean check() throws Exception {
        boolean[] check = new boolean[26];
        int prev = 0;
        String str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            int now = str.charAt(i);

            if (prev != now) {
                if (!check[now - 'a']) {
                    check[now - 'a'] = true;
                    prev = now;
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }
}
