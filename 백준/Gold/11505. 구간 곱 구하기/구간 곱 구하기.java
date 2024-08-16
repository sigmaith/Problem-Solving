import java.io.*;
import java.util.*;

public class Main {
    static long[] arr, tree;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        tree = new long[N * 4];

        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(1, N, 1, b, c);
                arr[b] = c;
            } else if (a == 2) {
                sb.append(multiply(1, N, 1, b, (int) c)).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static long init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start] % MOD;
        }

        int mid = (start + end) / 2;
        return tree[node] = (init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1)) % MOD;
    }

    public static long multiply(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return 1;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return (multiply(start, mid, node * 2, left, right) *
                multiply(mid + 1, end, node * 2 + 1, left, right)) % MOD;
    }

    public static void update(int start, int end, int node, int idx, long newValue) {
        if (idx < start || idx > end) {
            return;
        }

        if (start == end) {
            tree[node] = newValue % MOD;
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, newValue);
        update(mid + 1, end, node * 2 + 1, idx, newValue);

        tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD;
    }
}