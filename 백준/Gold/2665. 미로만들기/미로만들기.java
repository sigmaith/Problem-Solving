// boj 2665 미로만들기
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 아이디어: 경로에 black 칸 기준 min PriorityQueue 생성
        // v = n^2, e = 2v = 2(n^2) O(n^2)
        // dijkstra time complexity: O((E+V)logV) 시간은 1sec.. (1억) 충분
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // x, y, blackCnt -> 1st: blackCnt, 2nd: whiteCnt
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1[2], o2[2]);
        });

        pq.offer(new int[]{0, 0, 0});
        dist[0][0] = 0;
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int nowX = now[0];
            int nowY = now[1];
            int cost = now[2];
            if (nowX == n-1 && nowY == n-1) {
                System.out.println(cost);
                break;
            }
            if (dist[nowX][nowY] < cost) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if (isInMap(n, nextX, nextY)) {
                    int nextCost = cost + isBlack(map, nextX, nextY);
                    if (dist[nextX][nextY] > nextCost) {
                        dist[nextX][nextY] = nextCost;
                        pq.offer(new int[]{nextX, nextY, nextCost});
                    }
                }
            }
        }

    }

    static int isBlack(int[][] map, int x, int y) {
        if (map[x][y] == 1) {
            return 0;
        }
        return 1;
    }

    static boolean isInMap(int n, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
