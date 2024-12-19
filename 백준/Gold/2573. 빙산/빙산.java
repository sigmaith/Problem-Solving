// boj 2573 빙산
import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0, 0, 1, -1},  dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        Deque<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    q.offerLast(new int[]{i, j});
                }
            }
        }


        int year = 0;
        while (true) {
            while (!q.isEmpty()) {
                int[] info = q.pollFirst();
                for (int i = 0; i < 4; i++) {
                    int nx = info[0] + dx[i];
                    int ny = info[1] + dy[i];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] > 0) {
                        map[nx][ny] -= 1;
                    }
                }
            }
//            System.out.println(Arrays.deepToString(map));

            year++;
//            System.out.println(year);
            int numberOfChunk = getNumberOfChunk(map, N, M);
            if (numberOfChunk >= 2) {
                System.out.println(year);
                return;
            }

            boolean flag = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        q.offerLast(new int[]{i, j});
                    } else {
                        flag = false;
                    }
                }
            }
            if (flag) {
                break;
            }
        }
        System.out.println(0);
    }

    static int getNumberOfChunk(int[][] map, int N, int M) {
        boolean[][] visited = new boolean[N][M];
        int cnt = 0;

        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    cnt++;
                    q.offerLast(new int[]{i, j});
                    while (!q.isEmpty()) {
                        int[] info = q.pollFirst();
                        for (int k = 0; k < 4; k++) {
                            int nx = info[0] + dx[k];
                            int ny = info[1] + dy[k];
                            if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && map[nx][ny] > 0) {
                                visited[nx][ny] = true;
                                q.offerLast(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
        }
        return cnt;
    }
}
