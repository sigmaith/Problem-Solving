// #1915 가장 큰 정사각형 

import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] array = new int[n][m];

        for(int i=0; i<n; i++){ // 숫자 배열 입력 받기
            String line = br.readLine().trim();
            for(int j=0; j<m; j++){
                array[i][j] = line.charAt(j) - '0';
            }
        }

        int result = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if (array[i][j] == 1 && result == 0){
                    result = 1;
                }
                if (i > 0 && j > 0 && array[i][j]!=0 ){
                    int left = array[i][j-1];
                    int up = array[i-1][j];
                    int diag = array[i-1][j-1];
                    int min = Math.min(Math.min(left, up), diag);
                    if (left == up && up == diag || left == up && diag < left) array[i][j] = diag+1;
                    else array[i][j] = min + 1;
                }
                if (array[i][j] > result) result = array[i][j];
            }
        }

        bw.write(result*result + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}
