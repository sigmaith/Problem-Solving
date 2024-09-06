import java.io.*;
import java.util.*;

class Score {
    int[] score = new int[5];
    int total;
}

public class Main
{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		Score a = new Score();
		Score b = new Score();
		
		for (int i = 0; i < 2; i++) {
		    st = new StringTokenizer(br.readLine());
		    for (int j = 0; j < 5; j++) {
		        if (i == 0) {
		            a.score[j] = Integer.parseInt(st.nextToken());
		        }
		        else if (i == 1){
		            b.score[j] = Integer.parseInt(st.nextToken());
		        }
		    }
		}
		
		a.total = a.score[0]*6 + a.score[1]*3 + a.score[2]*2 + a.score[3] + a.score[4]*2;
		b.total = b.score[0]*6 + b.score[1]*3 + b.score[2]*2 + b.score[3] + b.score[4]*2;
		
		bw.write(a.total + " " + b.total);
		bw.flush();
		bw.close();
		br.close();
	}
}
