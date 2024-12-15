// boj 1065 한수
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();

        int cnt = 0;
        for (int i = 1; i <= X; i++) {
            if (i / 10 < 10) {
                cnt ++;
            } else {
                String s = String.valueOf(i);
                int length = s.length();
                boolean flag = true;
                int n = s.charAt(1) - s.charAt(0);
                for (int j = 2; j < length; j++) {
                    if (s.charAt(j) - s.charAt(j-1) != n) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
