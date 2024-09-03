// boj 18436 수열과 쿼리 37 - segtree
import java.io.*;
import java.util.*;

public class Main {
    static long[] arr, treeOdd, treeEven;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        treeOdd = new long[n * 4];
        treeEven = new long[n * 4];
        initOddTree(1, 0, n - 1);
        initEvenTree(1, 0, n - 1);

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int what = Integer.parseInt(st.nextToken());
            if (what == 1) { // update
                int idx = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                arr[idx - 1] = num;
                updateOdd(1, 0, n - 1, idx - 1);    
                updateEven(1, 0, n - 1, idx - 1);
            }
            else if (what == 2) { // num of even
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                bw.write(query(treeEven,1,0,n - 1,l - 1 ,r - 1) + "\n");
            }
            else if (what == 3) { // num of odd
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                bw.write(query(treeOdd, 1, 0, n - 1, l - 1, r - 1) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static long initOddTree(int node, int start, int end) {
        if (start == end) {
            return treeOdd[node] = (arr[start] % 2);
        }
        long part1 = initOddTree(node * 2, start, (start + end) / 2);
        long part2 = initOddTree(node * 2 + 1, (start + end) / 2 + 1, end);
        return treeOdd[node] = part1 + part2;
    }

    static long initEvenTree(int node, int start, int end) {
        if (start == end) {
            if (arr[start] % 2 == 0) treeEven[node] = 1;
            else treeEven[node] = 0;
            return treeEven[node];
        }
        long part1 = initEvenTree(node * 2, start, (start + end) / 2);
        long part2 = initEvenTree(node * 2 + 1, (start + end) / 2 + 1, end);
        return treeEven[node] = part1 + part2;
    }

    static long updateOdd(int node, int start, int end, int idx) {
        if (start > idx || end < idx) {
            return 0;
        } 
        if (start == idx && end == idx) {
            if (treeOdd[node] == 1 && arr[idx] % 2 == 1){ // leaf node 값 -> 홀짝 여부로 표현하면서 고치지 못했음
                return 0; // 둘다 홀수
            } else if (treeOdd[node] == 0 && arr[idx] % 2 == 1) { // 원래 짝수 -> 홀수
                treeOdd[node] = 1;
                return 1; // 증가
            } else if (treeOdd[node] == 1 && arr[idx] % 2 == 0) { // 원래 홀수 -> 짝수
                treeOdd[node] = 0;
                return -1; // 감소
            } else if (treeOdd[node] == 0 && arr[idx] % 2 == 0) {
                return 0;
            }
        }

        long dx1 = updateOdd(node * 2, start, (start + end) / 2, idx);
        long dx2 = updateOdd(node * 2 + 1, (start + end) / 2 + 1, end, idx);
        treeOdd[node] += (dx1 + dx2);
        return dx1 + dx2;
    }
    
    static long updateEven(int node, int start, int end, int idx) {
        if (start > idx || end < idx) {
            return 0;
        }
        if (start == idx && end == idx) {
            if (treeEven[node] == 1 && arr[idx] % 2 == 0) { // leaf node 값 -> 홀짝 여부로 표현하면서 고치지 못했음
                return 0;
            } else if (treeEven[node] == 1 && arr[idx] % 2 == 1) { // 원래 짝수 -> 홀수
                treeEven[node] = 0;
                return -1; // 감소
            } else if (treeEven[node] == 0 && arr[idx] % 2 == 0) { // 원래 홀수 -> 짝수
                treeEven[node] = 1;
                return 1; // 증가
            } else if (treeEven[node] == 0 && arr[idx] % 2 == 1) {
                return 0;
            }
        }

        long dx1 = updateEven(node * 2, start, (start + end) / 2, idx);
        long dx2 = updateEven(node * 2 + 1, (start + end) / 2 + 1, end, idx);
        treeEven[node] += (dx1 + dx2);
        return dx1 + dx2;
    }

    static long query(long[] tree, int node, int start, int end, int left, int right) {
        if (end < left || start > right) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return query(tree, node * 2, start, mid, left, right) 
                    + query(tree, node * 2 + 1, mid + 1, end, left, right);
    }
}
