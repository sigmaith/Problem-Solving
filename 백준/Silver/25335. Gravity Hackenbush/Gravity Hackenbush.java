import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // num of node
        int m = Integer.parseInt(st.nextToken()); // num of edges

        int[][] arr = new int[n+1][2];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[i][0] = x;
            arr[i][1] = y;        
        }
        int red_count = 0;
        int blue_count = 0;
        int green_count = 0;

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            String color = st.nextToken();
            if (color.equals("R")) red_count++;
            if (color.equals("B")) blue_count++;    
            if (color.equals("G")) green_count++;
        }
        
        if(green_count%2 == 1) {
            if (red_count >= blue_count) bw.write("jhnah917\n"); // 1번 플레이어 승
            else bw.write("jhnan917\n"); // 2번 플레이어 승
        }
        else{
            if (red_count <= blue_count) bw.write("jhnan917\n"); // 2번 플레이어 승
            else bw.write("jhnah917\n"); // 1번 플레이어 승
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
