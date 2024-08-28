// boj 1275: 커피숍2
import java.io.*;
import java.util.*;

public class Main {
    static long[] arr;
    static long[] tree;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.valueOf(st.nextToken());
        }

        tree = new long[N * 4];
        init(1, N, 1);
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());
            long value = Long.parseLong(st.nextToken());

            long result = findSumQuery(1, N, 1, Math.min(a, b), Math.max(a, b));
            bw.write(result + "\n");
            update(1, N, 1, idx, value - arr[idx]);
            arr[idx] = value;
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }    
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    public static long findSumQuery(int start, int end, int node, int left, int right) {
        if (end < left || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return findSumQuery(start, mid, node * 2, left, right) + findSumQuery(mid + 1, end, node * 2 + 1, left, right);
    }

    public static void update(int start, int end, int node, int idx, long dif) {
        if (idx < start || end < idx) {
            return;
        }
        tree[node] += dif;
        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, dif);
        update(mid + 1, end, node * 2 + 1, idx, dif);
    }
}
