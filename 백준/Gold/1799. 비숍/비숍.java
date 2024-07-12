import java.io.*;
import java.util.*;

public class Main {
    static int count_black;
    static int count_white;
    static int n;
    static boolean[][] danger;
    static boolean[] diag1;
    static boolean[] diag2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        danger = new boolean[n][n];
        diag1 = new boolean[2 * n - 1];
        diag2 = new boolean[2 * n - 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                if (Integer.parseInt(st.nextToken()) == 0) {
                    danger[i][j] = true; // 놓을 수 없는 자리 표시
                }
            }
        }
        count_black = 0;
        count_white = 0;
        backtracking_black(0, 0, 0);
        backtracking_white(0, 1, 0);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int result = count_black + count_white;
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void backtracking_black(int i, int j, int cnt) {
        if (j >= n) {
            i++;
            if (i % 2 == 0)j = 0;
            else j = 1;
        }
        if (i >= n) {
            count_black = Math.max(count_black, cnt);
            return;
        }
        if (!danger[i][j] && !diag1[i + j] && !diag2[i - j + n - 1]) {
            diag1[i + j] = true;
            diag2[i - j + n - 1] = true;
            backtracking_black(i, j + 2, cnt + 1);
            diag1[i + j] = false;
            diag2[i - j + n - 1] = false;
        }
        backtracking_black(i, j + 2, cnt);
    }

    static void backtracking_white(int i, int j, int cnt) {
        if (j >= n) {
            i++;
            if (i % 2 == 0) j = 1;
            else j = 0;
        }
        if (i >= n) {
            count_white = Math.max(count_white, cnt);
            return;
        }
        if (!danger[i][j] && !diag1[i + j] && !diag2[i - j + n - 1]) {
            diag1[i + j] = true;
            diag2[i - j + n - 1] = true;
            backtracking_white(i, j + 2, cnt + 1);
            diag1[i + j] = false;
            diag2[i - j + n - 1] = false;
        }
        backtracking_white(i, j + 2, cnt);
    }
}
