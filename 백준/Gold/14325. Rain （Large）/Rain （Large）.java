import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1000000000;
    static final int[] dx = { 0, 1, 0, -1 };
    static final int[] dy = { -1, 0, 1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int Y = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());

            int[][] b = new int[Y][X];
            int[][] can = new int[Y][X];
            Queue<Node> q = new LinkedList<>();

            // 입력 받기
            for (int y = 0; y < Y; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < X; x++) {
                    b[y][x] = Integer.parseInt(st.nextToken());
                    // 가장자리에 위치한 곳은 바로 큐에 넣고 처리
                    if (y == 0 || y == Y - 1 || x == 0 || x == X - 1) {
                        can[y][x] = b[y][x];
                        q.offer(new Node(y, x));
                    } else {
                        can[y][x] = INF;
                    }
                }
            }

            // BFS 시작
            while (!q.isEmpty()) {
                Node current = q.poll();
                int y = current.y;
                int x = current.x;

                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if (ny >= 0 && ny < Y && nx >= 0 && nx < X) {
                        int h = can[y][x];
                        if (can[ny][nx] > h) {
                            can[ny][nx] = Math.max(h, b[ny][nx]);
                            q.offer(new Node(ny, nx));
                        }
                    }
                }
            }

            int ans = 0;
            // 결과 계산
            for (int y = 0; y < Y; y++) {
                for (int x = 0; x < X; x++) {
                    ans += can[y][x] - b[y][x];
                }
            }

            System.out.println("Case #" + (1 + n) + ": " + ans);
        }

    }

    // Node 클래스 정의
    static class Node {
        int y, x;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
