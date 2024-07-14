import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 작으면 tail을 키우고, 크면 head를 앞으로
        int target = Integer.parseInt(br.readLine());
        int answer = 0;

        int sum = 1;
        int head = 1; int tail = 2;
        while (head <= target) {   // 최적화 가능
            if (sum < target) {
                sum += tail ++;
            } else if (sum > target) {
                sum -= head ++;
            } else {
                answer ++;
                // System.out.println(head + " ~ " + tail);
                sum += tail ++;
            }

        }

        System.out.println(answer);

    }
}

/* 
9   = 4 + 5
    = 2 + 3 + 4

15  = 1 + 2 + 3 + 4 + 5
    = 4 + 5 + 6
    = 7 + 8
*/