// boj 1941 소문난 칠공주

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Main {
    static int result = 0;
    static char[][] classW;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        classW = new char[5][5];
        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 5; j++) {
                classW[i][j] = str.charAt(j);
            }
        }

        List<int[]> princess = new ArrayList<>();
        backtracking(0, 0, princess);
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    private static void backtracking(int x, int y, List<int[]> princess) {
        if (x == 5) {
            if (princess.size() != 7) {
                return;
            }

            int cntOfDasom = 0;
            for (int i = 0; i < 7; i++) {
                int[] info = princess.get(i);
                int r = info[0], c = info[1];
                if (classW[r][c] == 'S') {
                    cntOfDasom++;
                }
            }
            if (cntOfDasom >= 4 && checkContinuousSevenElement(princess)) { // 4명 이상이 이다솜(S)인지 체크
                result += 1;
                return;
            }
            return;
        }

        if (y < 4) {
            princess.add(new int[]{x, y});
            backtracking(x, y + 1, princess);
            princess.remove(princess.size() - 1);
            backtracking(x, y + 1, princess);
        } else if (y == 4) {
            princess.add(new int[]{x, y});
            backtracking(x + 1, 0, princess);
            princess.remove(princess.size() - 1);
            backtracking(x + 1, 0, princess);
        }
    }

    private static boolean checkContinuousSevenElement(List<int[]> princess) { // princess 원소가 이어진 7개의 원소인지 확인
        boolean[][] visited = new boolean[5][5];
        int[] firstInfo = princess.get(0);
        visited[firstInfo[0]][firstInfo[1]] = true;

        Deque<int[]> q = new ArrayDeque<>();
        q.offerLast(firstInfo);
        int cnt = 1;
        while (!q.isEmpty()) {
            int[] info = q.pollFirst();
            for (int i = 0; i < 4; i++) {
                int nx = info[0] + dx[i], ny = info[1] + dy[i];
                if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && !visited[nx][ny] && hasElement(princess,
                        new int[]{nx, ny})) {
                    visited[nx][ny] = true;
                    q.offerLast(new int[]{nx, ny});
                    cnt++;
                }
            }
        }
        return cnt == 7;
    }

    private static boolean hasElement(List<int[]> princess, int[] element) {
        for (int[] e : princess) {
            if (Arrays.equals(e, element)) {
                return true;
            }
        }
        return false;
    }
}

