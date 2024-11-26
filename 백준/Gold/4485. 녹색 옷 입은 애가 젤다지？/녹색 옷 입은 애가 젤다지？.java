// boj 4485 녹색 옷 입은 애가 젤다지?
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cnt = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            long[][] map = new long[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Long.parseLong(st.nextToken());
                }
            }

            PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> {return Long.compare(o1[2], o2[2]);}); // x,y,costs
            pq.offer(new long[]{0, 0, map[0][0]});
            long[] dx = {0, 0, 1, -1};
            long[] dy = {1, -1, 0, 0};
            long[][] costRecord = new long[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(costRecord[i], Long.MAX_VALUE);
            }
            costRecord[0][0] = map[0][0];
            while(!pq.isEmpty()) {
                long[] node = pq.poll();
                long nowX = node[0];
                long nowY = node[1];
                long nowCost = node[2];

                if (nowX == n-1 && nowY == n-1) {
                    System.out.println("Problem " + cnt + ": " + nowCost);
                    break;
                }

                if (costRecord[(int)nowX][(int)nowY] < nowCost) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    long nextX = nowX + dx[d];
                    long nextY = nowY + dy[d];
                    if (0 <= nextX && nextX < n && 0 <= nextY && nextY < n) {
                        if (nowCost + map[(int)nextX][(int)nextY] < costRecord[(int) nextX][(int) nextY]) {
                            costRecord[(int)nextX][(int)nextY] = nowCost + map[(int)nextX][(int)nextY];
                            pq.offer(new long[]{nextX, nextY, costRecord[(int)nextX][(int)nextY]});
                        }
                    }
                }
            }
            cnt++;
        }
    }
}
