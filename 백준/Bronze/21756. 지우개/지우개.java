// boj 21756 지우개
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i =0; i < N; i++) {
            arr[i] = i + 1;
        }

        while (N != 1) {
            N = N / 2;
            int idx = 1;
            int[] newArr = new int[N];
            for (int i = 0; i < N; i++) {
                newArr[i] = arr[idx];
                idx += 2;
            }
            arr = newArr;
            if (N == 1) {
                System.out.println(newArr[0]);
                return;
            }
        }

        System.out.println(arr[0]);
    }
}
