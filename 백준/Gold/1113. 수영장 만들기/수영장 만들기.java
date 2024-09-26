// boj 1113 수영장 만들기
import java.io.*;
import java.util.*;

public class Main {
    static int INF = (int)1e8;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] mountain = new int[N][M];
        int[][] water = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(water[i], INF);
        }

        Queue<int[]> q = new LinkedList<int[]>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                mountain[i][j] = Integer.valueOf(str.charAt(j));
                if (i == 0 || i == N - 1 || j == 0 || j == M - 1) {
                    water[i][j] = mountain[i][j];
                    int[] pos = {i, j};
                    q.offer(pos);
                }
            }
        }

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int x = pos[0];
            int y = pos[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (water[x][y] < water[nx][ny]) {
                        water[nx][ny] = Math.max(water[x][y], mountain[nx][ny]);
                        int[] nextPos = { nx, ny };
                        q.offer(nextPos);
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
             for (int j = 0; j < M; j++) {
                if (water[i][j] > mountain[i][j]) {
                    result += water[i][j] - mountain[i][j];
                }
             }
        }
        System.out.println(result);
    }
}
