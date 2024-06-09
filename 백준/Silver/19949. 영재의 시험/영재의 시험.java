// #19949 영재의 시험

import java.io.*;
import java.util.*;

class Main {
    static int count = 0;
    static int[] test_yj = new int[10];
    static int[] answer = new int[10];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<10; i++)
            answer[i] = Integer.parseInt(st.nextToken());
        
        
        // 3개의 연속된 숫자는 선택하지 않는 영재 

        backtracking(0, 0);

        bw.write(count + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
    public static void backtracking(int idx, int score){
        if (idx==10) {
            if (score >= 5) count++;
            return;
        } 
        for (int i=1; i<=5; i++){
            if (idx >= 2 && test_yj[idx - 1] == test_yj[idx - 2] && i == test_yj[idx - 1])
                continue; // 3개 연속된 숫자 방지
            test_yj[idx] = i;
            if (test_yj[idx] == answer[idx])
                backtracking(idx + 1, score + 1);
            else
                backtracking(idx + 1, score);
        }
    } 
}
