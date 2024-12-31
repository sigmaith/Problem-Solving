// boj 2146 다리 만들기

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


public class Main {
    static int N;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // x, y, 시작 섬 번호, 다리 길이
        Deque<int[]> q = new ArrayDeque<>();

        int tag = 1;
        boolean[][] visited1 = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited1[i][j] && map[i][j] == 1) {
                    dfs(i, j, tag, q, visited1);
                    tag++;
                } else if (!visited1[i][j] & map[i][j] == 0) {
                    visited1[i][j] = true;
                }
            }
        }

        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};

        int result = Integer.MAX_VALUE;
        boolean[][][] visited2 = new boolean[tag][N][N];
        for (int i = 0; i < N; i++) {

        }

        while (!q.isEmpty()) {
            int[] info = q.poll();
            int x = info[0], y = info[1], src = info[2], bridge = info[3];
            if (bridge == 0) {
                visited2[src][x][y] = true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) {
                    continue;
                }

                if (map[nx][ny] != 0 && map[nx][ny] != src) { // 다른 섬 찾았을 경우
                    if (result > bridge) {
                        result = bridge;
                    }
                } else if (map[nx][ny] == 0 && !visited2[src][nx][ny]) {
                    visited2[src][nx][ny] = true;
                    q.offer(new int[]{nx, ny, src, bridge + 1});
                }
            }
        }
        System.out.println(result);

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y, int tag, Deque<int[]> q, boolean[][] visited) {
        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};

        visited[x][y] = true;
        map[x][y] = tag;

        boolean flag = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) {
                continue;
            }
            if (!visited[nx][ny] && map[nx][ny] == 1) {
                dfs(nx, ny, tag, q, visited);
            } else if (flag && map[nx][ny] == 0) {
                flag = false;
                q.offer(new int[]{x, y, tag, 0});
            }
        }
    }
}

