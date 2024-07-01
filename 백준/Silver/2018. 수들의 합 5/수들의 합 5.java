// #2018 수들의 합5
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n;
    static int result;
    static void solve(int startNum) throws Exception{
        int num = startNum;

        //bw.write("startNum = " + startNum + "\n");
        for(int i=startNum+1; ;i++){
            num += i;
            if (num > n) break;
            else if (num == n){
                result += 1;

                //
                
                //

                break;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        result = 0;
        for (int i=1; i<=n; i++){
            solve(i);
        }

        result += 1;
        bw.write(result + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}
