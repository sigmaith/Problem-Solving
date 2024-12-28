// boj 15684 사다리 조작
import java.io.*;
import java.util.*;

public class Main {
    static int N, H;
    static boolean[][] routes;
    static List<int[]> candidates;
    static int cntOfLines;
    static boolean solved = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); //  (2 ≤ N ≤ 10, 1 ≤ H ≤ 30, 0 ≤ M ≤ (N-1)×H)
        N = Integer.parseInt(st.nextToken()); // 세로선의 수 N
        int M = Integer.parseInt(st.nextToken()); // 가로선의 수 M
        H = Integer.parseInt(st.nextToken()); // 세로선 마다 가로선을 놓을 수 있는 위치의 개수 H

        routes = new boolean[H][N - 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // a번 점선 위치
            int b = Integer.parseInt(st.nextToken()); // b번 세로선과 b+1번 세로선
            routes[a - 1][b - 1] = true;
        }

        candidates = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (!routes[i][j]) {
                    candidates.add(new int[]{i, j});
                }
            }
        }

        /*
        i번 세로선의 결과가 i번이 나오도록 사다리 게임을 조작하려면, 추가해야 하는 가로선 개수의 최솟값을 출력한다. 만약, 정답이 3보다 큰 값이면 -1을 출력한다. 또, 불가능한 경우에도 -1을 출력한다.
        */

        for (int i = 0; i <= 3; i++) {
            cntOfLines = i;
            simulate(0, 0);
            if (solved) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    private static void simulate(int id, int cnt) { // 좌표와 추가한 가로선 개수
        if (id == candidates.size() && cnt == cntOfLines) {
            if (check()) {
                solved = true;
            }
            return;
        }
        if (solved || cnt > cntOfLines || id > candidates.size() - 1) {
            return;
        }
        int[] info = candidates.get(id);
        if (canPlace(info[0], info[1])) { // 놓기 가능하면 (선택 or 선택x)
            routes[info[0]][info[1]] = true;
            simulate(id + 1, cnt + 1);
            routes[info[0]][info[1]] = false;
            simulate(id + 1, cnt);
        } else { // 아니면 그냥 간다.
            simulate(id + 1, cnt);
        }
    }

    private static boolean canPlace(int x, int y) {
        if (y + 1 <= N - 2 && routes[x][y + 1]) {
            return false;
        }
        if (y - 1 >= 0 && routes[x][y - 1]) {
            return false;
        }
        return true;
    }

    private static boolean check() {
        for (int i = 0; i < N; i++) {
            int idx = i;
            int level = 0;
            while (level < H) {
                if (idx == 0 && routes[level][idx]) {
                    idx = idx + 1;
                } else if (idx == N - 1 && routes[level][idx - 1]) {
                    idx = idx - 1;
                } else if (idx >= 1 && idx <= N - 2 && routes[level][idx - 1]) { // < N - 2 였어서 틀렸음
                    idx = idx - 1;
                } else if (idx >= 1 && idx <= N - 2 && routes[level][idx]) { // < N - 2 였어서 틀렸음
                    idx = idx + 1;
                }
                level++;
            }
            if (idx != i) {
                return false;
            }
        }
        return true;
    }
}
