// 2839번: 설탕 배달
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int result = Integer.MAX_VALUE;
        
        //int[][] math = new int[n + 1][2];
        for (int i = 0; i <= n/3; i++) {
            if ((n - i * 3) % 5 == 0) {
                result = Math.min(result, i + (n - i * 3) / 5);
            }
        }
        if (result != Integer.MAX_VALUE) {
            bw.write(result + "\n");    
        }
        else {
            bw.write("-1");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
