import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] map = writeMap(br, R, C);

        int N = Integer.parseInt(br.readLine());
        int[][] rules = writeRules(br, N);
        if (R == 1 && List.of(map[0]).contains(1)) {
            System.out.println(0);
            return;
        } else if (N == 0) {
            System.out.println(-1);
            return;
        }
        boolean[][] visited = new boolean[R][C];

        Deque<int[]> q = new ArrayDeque<>();
        for (int j = 0; j < C; j++) {
            if (map[0][j] == 1) {
                visited[0][j] = true;
                q.offerLast(new int[]{0, j, 0});
            }
        }
        
        while (!q.isEmpty()) {
            int[] now = q.pollFirst();
            int nowX = now[0];
            int nowY = now[1];
            int nowMove = now[2];
            if (nowX == R-1) {
                System.out.println(nowMove); // 첫번째 nowMove 발견 시 바로 break
                return;
            }
            for (int[] rule: rules) {
                int nextX = nowX + rule[0];
                int nextY = nowY + rule[1];
                if (isInMap(R, C, nextX, nextY) && map[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    q.offerLast(new int[]{nextX, nextY, nowMove + 1});
                }
            }
        }
        System.out.println(-1);
        br.close();
    }

    static int[][] writeMap(BufferedReader br, int R, int C) throws IOException{
        int[][] map = new int[R][C];
        for (int i = 0; i < R; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return map;
    }

    static int[][] writeRules(BufferedReader br, int N) throws IOException{
        int[][] rules = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            rules[i][0] = Integer.parseInt(st.nextToken());
            rules[i][1] = Integer.parseInt(st.nextToken());
        }
        return rules;
    }

    static boolean isInMap(int R, int C, int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}