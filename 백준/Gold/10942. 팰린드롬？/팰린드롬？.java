// #10942 팰린드롬?
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    public static void main(String[] main) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 수열의 크기
        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        } // 수열
        
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // 질문의 개수

        for (int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int mid = start + (end - start + 1) / 2;
            int flag = 1;
            while(start < mid){
                //bw.write("start = " + arr[start] + "\n");
                //bw.write("end = " + arr[end] + "\n");
                //bw.flush();
                if (arr[start++] != arr[end--]){
                    flag = 0; 
                    break;
                }
            }
            if (flag == 1) bw.write(1 + "\n");
            else bw.write(0 + "\n");
            //bw.flush();
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
