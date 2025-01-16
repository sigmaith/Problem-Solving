// boj 14890 경사로

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // (5 ≤ N, M ≤ 100)

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 치즈 입력

        int time = 0;
        while (true) {
            boolean nothing = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        nothing = false;
                        break;
                    }
                }
            }
            if (nothing) {
                System.out.println(time);
                break;
            }
            int[][] cheeseRemove = new int[N][M]; // 사라질 치즈 체크
            for (int i = 0; i < N; i++) {
                Arrays.fill(cheeseRemove[i], 0);
            }
            boolean[][] visited = new boolean[N][M]; // 방문체크
            Deque<int[]> q = new ArrayDeque<>(); // 빈칸 체크
            visited[0][0] = true;
            q.offerLast(new int[]{0, 0});

            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};
            while (!q.isEmpty()) {
                int[] info = q.pollFirst();
                for (int i = 0; i < 4; i++) {
                    int nx = info[0] + dx[i];
                    int ny = info[1] + dy[i];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                        if (map[nx][ny] == 0 && !visited[nx][ny]) { // 0이면 큐에담고
                            visited[nx][ny] = true;
                            q.offerLast(new int[]{nx, ny});
                        } else if (map[nx][ny] == 1) { // 1이면 count
                            cheeseRemove[nx][ny] += 1;
                        }
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (cheeseRemove[i][j] >= 2) {
                        map[i][j] = 0;
                    }
                }
            }

            time += 1;
        }
    }
}