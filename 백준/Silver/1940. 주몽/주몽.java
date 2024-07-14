// #1940 주몽의 명령
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        // System.out.println(Arrays.toString(nums));

        int start = 0;
        int end = n - 1;
        int result = 0;
        while (start < end) {
            if (nums[start] + nums[end] == m) {
                result += 1;
                start += 1;
                end -= 1;
            }
            else if (nums[start] + nums[end] < m) {
                start += 1;
            }
            else if (nums[start] + nums[end] > m) {
                end -= 1;
            }
        }
        
        bw.write(result + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}
