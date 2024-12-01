// boj 14497 주난의 난(難)
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken()) - 1, y1 = Integer.parseInt(st.nextToken()) - 1; // 주난
        int x2 = Integer.parseInt(st.nextToken()) - 1, y2 = Integer.parseInt(st.nextToken()) - 1; // 범인

        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        boolean[][] visited = new boolean[N][M];
        visited[x1][y1] = true;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x1, y1, 0}); // x좌표, y좌표, 시간

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        while (!q.isEmpty()) {
            int[] info = q.pollFirst();
            int x = info[0];
            int y = info[1];
            int time = info[2];
            if (map[x][y] == '#') {
                System.out.println(time);
                break;
            }
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if (map[nx][ny] == '1' || map[nx][ny] == '#') { // 쓰러뜨림
                        q.offerLast(new int[]{nx, ny, time + 1});
                    } else {
                        // 가중치가 0인 지점이 먼저 도달하도록 (구하는 것: 범인을 잡기 위한 최소 점프 횟수)
                        q.offerFirst(new int[]{nx, ny, time}); // 3% -> 100%
                    }
                }
            }
        }
    }
}