// boj 10868 최솟값
import java.io.*;
import java.util.*;

public class Main {
    static int[] arr, tree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 최대 십만
        int M = Integer.parseInt(st.nextToken()); // 최대 십만

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.valueOf(br.readLine());// 최대 십억
        }

        tree = new int[N * 4];
        init(1, N, 1);
        // System.out.println(Arrays.toString(tree));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int result = findMinQuery(1, N, 1, a, b);
            bw.write(result + "\n");
        }
        
        bw.flush();
        bw.close();
        br.close();
    }

    public static int init(int start, int end, int node) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = Math.min(init(start, mid, node * 2) , init(mid + 1, end, node * 2 + 1));
    }

    //start, end가 tree를 탐색하는 인덱스, left, right이 변하지 않는 찾고자 하는 구간
    public static int findMinQuery(int start, int end, int node, int left, int right) {
        if (end < left || right < start) {
            return 1_000_000_001;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return Math.min(findMinQuery(start, mid, node * 2, left, right), findMinQuery(mid + 1, end, node*2 + 1, left, right));
    }
}
