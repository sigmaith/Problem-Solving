// boj 15730 수영장 사장님
import java.io.*;
import java.util.*;

public class Main {
    static class Cell {
        int x;
        int y;
        int height;
        Cell(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        PriorityQueue<Cell> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.height, o2.height)); // Min PriorityQueue
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0 || i == N - 1 || j == 0 || j == M - 1) {
                    pq.offer(new Cell(i, j, map[i][j])); // pq에 추가
                    visited[i][j] = true;
                }
            }
        }

        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{1, -1, 0, 0};
        int water = 0;
        while (!pq.isEmpty()) {
            Cell cell = pq.poll();
            int x = cell.x;
            int y = cell.y;
            int h = cell.height;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                    if (map[nx][ny] < h) {
//                        System.out.println("nx = " + nx + " ny = " + ny + " height = " + map[nx][ny] + " water = " + (h - map[nx][ny]));
                        water += h - map[nx][ny];
                    }
                    visited[nx][ny] = true;
                    pq.offer(new Cell(nx, ny, Math.max(h, map[nx][ny]))); // 이렇게 하면 물이 담겼다는 표시 혹은 높은 자리라는 표시를 할 수 있음
//                    pq.offer(new Cell(nx, ny, map[nx][ny])); 틀린이유: 큰것 기준으로 남아야 제일 낮은 곳을 탐색할 수 있다.
//                    pq.offer(new Cell(nx, ny, h)); 틀린이유: 큰것 기준으로 남아야 제일 낮은 곳을 탐색할 수 있다.
                }
            }
        }
        System.out.println(water);
    }
}