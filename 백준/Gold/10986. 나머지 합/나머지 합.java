// 10986번: 나머지 합
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.valueOf(st.nextToken());
        int m = Integer.valueOf(st.nextToken());

        long[] arr = new long[n];
        long[] sum = new long[n];
        long[] remainder = new long[m];
        long result = 0; // (틀렸습니다 -> 100)

        st = new StringTokenizer(br.readLine());
        long s = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Long.valueOf(st.nextToken()); // 원본 배열 저장
            s += arr[i]; sum[i] = s; // 합 배열 저장
            int mod = (int)(s % m); // (ArrayIndexOutOfBounds -> 틀렸습니다)
            remainder[mod]++; // 나머지 개수 갱신 
            if (mod == 0) {
                result += 1;
            }
        }
        // System.out.println(Arrays.toString(arr));
        // System.out.println(Arrays.toString(sum));
        // System.out.println(Arrays.toString(remainder));
        
        for (int i = 0; i < m; i++) {
            if (remainder[i] > 1) {
                result += (remainder[i] * (remainder[i] - 1)) / 2; // 0개가 아니면 nC2 연산
            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
