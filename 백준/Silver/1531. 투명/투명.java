//  # 1531 투명
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] paintings = new int[101][101];
        for(int i=0; i<n; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st2.nextToken());
            int y1 = Integer.parseInt(st2.nextToken());
            int x2 = Integer.parseInt(st2.nextToken());
            int y2 = Integer.parseInt(st2.nextToken());

            for(int l=x1; l<=x2; l++){
                for (int k=y1; k<=y2; k++){
                    paintings[l][k]++;
                }
            }
        } 
        int count = 0;
        for(int i=1; i<=100; i++){
            for(int j=1; j<=100; j++){
                if (paintings[i][j] > m){
                    count++;
                }
            }
        }
        bw.write(count+"\n");
        br.close();
        bw.flush();
        bw.close();
    }
}
