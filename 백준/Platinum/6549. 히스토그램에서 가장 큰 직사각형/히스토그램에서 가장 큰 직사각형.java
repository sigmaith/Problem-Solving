// boj 6549 히스토그램에서 가장 큰 직사각형
import java.io.*;
import java.util.*;

public class Main {
    static long[] histogram;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n;

        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }

            histogram = new long[n];
            for (int i = 0; i < n; i++) {
                histogram[i] = Long.parseLong(st.nextToken());
            }

            bw.write(divideAndConquer(0, n-1) + "\n");
        }
        
        bw.flush();
        bw.close();
        br.close();
    }

    static long divideAndConquer(int start, int end) {
        if (start == end) {
            return histogram[start];
        }
        
        int mid = (start + end) / 2;
        long left = divideAndConquer(start, mid);
        long right = divideAndConquer(mid + 1, end);
        long max = Math.max(left, right);

        max = Math.max(max, getArea(start, end));

        // System.out.println("start = " + start + " end = " + end + " max = " + max);
        return max;
    }

    static long getArea(int start, int end) {
        int mid = (start + end) / 2;
        int toLeft = mid;
        int toRight = mid;
        long height = histogram[mid];
        long max = histogram[mid];

        while (start < toLeft && toRight < end) {
            if (histogram[toLeft - 1] < histogram[toRight + 1]) { // 높이 큰쪽으로 확장
                toRight++;
                height = Math.min(height, histogram[toRight]); // 높이를 더 작은 것으로 갱신
                // max = Math.max(max, height * (toRight - mid + 1));
            } else {
                toLeft--;
                height = Math.min(height, histogram[toLeft]);
                // max = Math.max(max, height * (mid - toLeft + 1));
            }
            max = Math.max(max, height * (toRight - toLeft + 1));
        }

        while (toRight < end) {
            toRight++;
            height = Math.min(height, histogram[toRight]);
            max = Math.max(max, height * (toRight - toLeft + 1)); // toLeft~toRight넓이 
        }

        while (start < toLeft) {
            toLeft--;
            height = Math.min(height, histogram[toLeft]);
            max = Math.max(max, height * (toRight - toLeft + 1));
        }

        // System.out.println("         getArea = " + max);
        return max;
    }
}
