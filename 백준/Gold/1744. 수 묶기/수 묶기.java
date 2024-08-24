// boj 1744: 수 묶기
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        long[] arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.valueOf(br.readLine());
        }

        Arrays.sort(arr);
        // System.out.println(Arrays.toString(arr));

        int result = 0;
        int idx = 0;
        int zeroIdx = -1;
        for (int i = 0; i < N; i++) {
            if (arr[i] == 0) {
                zeroIdx = i;
            }
        }
        if (zeroIdx != -1) { // 0이 존재
            // 음수가 짝수개 -> 음수끼리 곱해야함.
            if ((zeroIdx - 0 + 1) % 2 != 0) {
                while (idx < N && idx + 1 < N && arr[idx + 1] < 0) { // 다음수가 음수일 때까지
                    result += arr[idx] * arr[idx + 1];
                    idx += 2;
                }
                if (idx < N && arr[idx] == 0) {
                    idx += 1;
                }
            }
            // 음수가 홀수개 -> 0까지 곱해야함.
            else {
                while (idx < N && idx + 1 < N && arr[idx + 1] <= 0) { // 다음수가 음수거나 0일때까지
                    result += arr[idx] * arr[idx + 1];
                    idx += 2;
                }
            }
        }
        else { // 0이 없을 경우
            while (idx < N && idx + 1 < N && arr[idx + 1] < 0) { // 다음수가 음수일 때까지
                result += arr[idx] * arr[idx + 1];
                idx += 2;
            }
            // arr[idx]가 음수일 수 있음
            if (idx < N && arr[idx] <= 0) {
                result += arr[idx];
                idx += 1;
            }
        }
        // 1의 경우도 다뤄야함. 1, 1이 있을땐 곱하는 것보다 더하는 게 낫다.

        if (idx < N && (N-1-idx+1) % 2 != 0) { // 양수가 홀수개 (1이 아닌것)
            result += arr[idx];
            idx += 1;
        } 
        while (idx < N && idx + 1 < N) { 
            if (arr[idx] == 1) {
                result += arr[idx];
                result += arr[idx + 1];
            }
            else {
                result += arr[idx] * arr[idx + 1];
            }
            idx += 2;
        }
        System.out.println(result);
    }
}
