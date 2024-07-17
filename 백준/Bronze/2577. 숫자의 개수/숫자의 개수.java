// #2577 숫자의 개수
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        long num = a * b * c;
        int[] cnt = new int[10];

        while (num != 0) {
            cnt[(int)(num % 10)] += 1;
            num /= 10;
        }

        for (int i = 0; i < 10; i++) {
            bw.write(cnt[i] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
