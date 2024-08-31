// boj 16975 수열과 쿼리 21
import java.io.*;
import java.util.*;

public class Main {
    static long[] a;
    static long[] tree;
    static long[] lazy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }
        int h = (int) Math.ceil(Math.log(n) / Math.log(2));
        int tree_size = (1 << (h + 1));
        tree = new long[tree_size];
        lazy = new long[tree_size];
        init(1, 0, n-1);
        
        int m = Integer.parseInt(br.readLine());
        String[] line;
        while (m-- > 0) {
            line = br.readLine().split(" ");
            int what = Integer.parseInt(line[0]);
            if (what == 1) {  // 구간 업데이트
                int left = Integer.parseInt(line[1]);
                int right = Integer.parseInt(line[2]);
                long diff = Long.parseLong(line[3]);
                update_range(1, 0, n - 1, left - 1, right - 1, diff);
            } else {
                int idx = Integer.parseInt(line[1]);
                bw.write(query(1, 0, n - 1, idx - 1) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = a[start];
        } else {
            init(node * 2, start, (start + end) / 2);
            init(node * 2 + 1, (start + end) / 2 + 1, end);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    static void update_lazy(int node, int start, int end) { 
        if (lazy[node] != 0) { // 쌓인 태스크가 있었을 시에..
            tree[node] += (end - start + 1) * lazy[node]; // 해당 태스크 반영
            if (start != end) { // leaf node가 아닐 시, 
                lazy[node * 2] += lazy[node]; // prop 미뤘다는 표시 (왼쪽 자식)
                lazy[node * 2 + 1] += lazy[node]; // prop 미루기 (오른쪽 자식)
            }
            lazy[node] = 0; // prop 완료 표시
        }
    }

    static long query(int node, int start, int end, int idx) {
        // System.out.println("node = "+ node +" start = " + start + " end = " + end + " idx = " + idx);
        update_lazy(node, start, end);
        if (end < idx || start > idx) {
            return 0;
        }
        if (start == idx && end == idx) {
            // System.out.println("tree[node] = " + tree[node]);
            return tree[node];
        }
        else if (start == end && start != idx) {
            return 0;
        }
        else {
            return query(node * 2, start, (start + end) / 2, idx) 
                + query( node * 2 + 1, (start + end) / 2 + 1, end, idx);
        }
    }

    static void update_range(int node, int start, int end, int left, int right, long diff) {
        update_lazy(node, start, end); // 접근하려는 노드에 밀린 처리가 있으면 이제서야 내려 보내주기 위해
        if (left > end || right < start) { // 완전히 범위를 벗어난 경우
            return;
        }
        if (left <= start && end <= right) { // 구간에 완전히 포함될 때만 
            tree[node] += (end - start + 1) * diff;
            // System.out.println("changed tree[node] =" + tree[node]);
            if (start != end) {
                lazy[node * 2] += diff; // prop 미루기
                lazy[node * 2 + 1] += diff; // prop 미루기
            }
            return;
        }
        update_range(node * 2, start, (start + end) / 2, left, right, diff);
        update_range(node * 2 + 1, (start + end) / 2 + 1, end, left, right, diff);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
}
