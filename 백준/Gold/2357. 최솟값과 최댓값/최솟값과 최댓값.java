// boj 2357: 최솟값과 최댓값
import java.io.*;
import java.util.*;

public class Main {
    static int[] arr, minTree, maxTree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.valueOf(br.readLine());
        }
        // System.out.println(Arrays.toString(arr));

        minTree = new int[N * 4];
        maxTree = new int[N * 4];
        initMinTree(1, N, 1);
        initMaxTree(1, N, 1); 
        // System.out.println(Arrays.toString(maxTree));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.valueOf(st.nextToken());
            int b = Integer.valueOf(st.nextToken());
            int min = findMinQuery(1, N, 1, a, b);
            int max = findMaxQuery(1, N, 1, a, b);
            bw.write(min + " " + max + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int initMinTree(int start, int end, int node) {
        if (start == end) {
            return minTree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return minTree[node] = Math.min(initMinTree(start, mid, node * 2), initMinTree(mid + 1, end, node * 2 + 1));
    }

    public static int initMaxTree(int start, int end, int node) {
        if (start == end) {
            return maxTree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return maxTree[node] = Math.max(initMaxTree(start, mid, node * 2), initMaxTree(mid + 1, end, node * 2 + 1));
    }
    
    public static int findMinQuery(int start, int end, int node, int left, int right) {
        if (end < left || right < start) {
            return 1_000_000_001;
        }
        if (left <= start && end <= right) {
            return minTree[node];
        }
        int mid = (start + end) / 2;
        return Math.min(findMinQuery(start, mid, node * 2, left, right), findMinQuery(mid + 1, end, node * 2 + 1, left, right));
    }

    public static int findMaxQuery(int start, int end, int node, int left, int right) {
        if (end < left || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return maxTree[node];
        }
        int mid = (start + end) / 2;
        return Math.max(findMaxQuery(start, mid, node * 2, left, right), findMaxQuery(mid + 1, end, node * 2 + 1, left, right));
    }
    
}
