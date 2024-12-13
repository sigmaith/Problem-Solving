// boj 1016 제곱 ㄴㄴ수
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());
        long cnt = max - (min - 1);

        boolean[] squareYesYesNumber = new boolean[(int)cnt];
        for (long i = 2; i <= Math.sqrt(max); i++) {
            long num = i * i;
            long share = min % num == 0 ? min / num : min / num + 1;

            for (long j = share; num * j <= max; j++) {
                if (!squareYesYesNumber[(int)(num * j - min)]) {
                    squareYesYesNumber[(int)(num * j - min)] = true;
                }
            }
        }

        int yyNum = 0;
        for (int i = 0; i < cnt; i++) {
            if (squareYesYesNumber[i]) yyNum++;
        }
        System.out.println(cnt - yyNum);
    }
}
