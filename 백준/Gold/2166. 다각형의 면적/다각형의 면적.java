// #2166 다각형의 면적
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long[][] arr = new long[n+1][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            arr[i][0] = x;
            arr[i][1] = y;
        }

        // 마지막 점을 첫 번째 점과 동일하게 저장
        arr[n][0] = arr[0][0];
        arr[n][1] = arr[0][1];

        double sum1 = 0;
        for (int i=1; i<=n; i++){
            sum1 += arr[i][0] * arr[i-1][1];
        }

        long sum2 = 0;
        for (int i=0; i<n; i++){
            sum2 += arr[i][0] * arr[i+1][1];
        }
        // long 타입은 큰 정수 값을 처리하는 데 사용되며, 계산 과정에서 발생할 수 있는 오버플로우를 방지합니다. double 타입은 실수 값을
        // 처리할 수 있기 때문에, 최종 결과를 소수점까지 포함하여 정확하게 표현할 수 있습니다.
        double result = Math.abs((sum2 - sum1)/2.0); // 신발끈 공식
        result = (Math.round(result*10)/10.0);

        bw.write(String.format("%.1f", result) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }   
}

/*
 

5
-100000 100000
-100000 -100000
100000 -100000
100000 99999
99999 100000

// ans : 39999999999.5



 */