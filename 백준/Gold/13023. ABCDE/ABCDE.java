// boj 13023 ABCDE
import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> adj;
    static boolean[] visited;
    static int N;
    static boolean solved = false;
    static boolean flag = false;
    /*
    이게 성립할 수 있냐의 여부라서 1인 겁니다. 개수 아닙니다.
    저는 개인적으로 이 사실을 몰랐어서(개수인 줄 알았음) 좀 시간을 허비했습니다.
    https://www.acmicpc.net/board/view/117217
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N (5 ≤ N ≤ 2000)
        int M = Integer.parseInt(st.nextToken()); // M (1 ≤ M ≤ 2000)

        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        simulate();
        if (flag) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static void simulate() {
        for (int i = 0; i < N; i++) {
            if (solved) {
                return;
            }
            visited = new boolean[N];
            visited[i] = true;
            backtracking(i, 0);
        }
    }

    private static void backtracking(int id, int cnt) {
        if (cnt == 4) {
            flag = true;
            solved = true;
            return;
        }
        if (solved) {
            return;
        }
        for (int next: adj.get(id)) {
            if (!visited[next]) {
                visited[next] = true;
                backtracking(next, cnt + 1);
                visited[next] = false;
            }
        }
    }
}
