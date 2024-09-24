// boj 14324 Rain(Small)
import java.io.*;
import java.util.*;

public class Main {
    static int[][] island;
    static int[][] water;
    static int INF = (int)1e8;

    static class Node{
        int x, y;
        Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            island = new int[r][c];
            water = new int[r][c];
            for (int j = 0; j < r; j++) {
                Arrays.fill(water[j], INF);
            }
            
            ArrayDeque<Node> q = new ArrayDeque<>();

            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < c; k++) {
                    island[j][k] = Integer.parseInt(st.nextToken());
                    if (j == 0 || j == r-1 || k == 0 || k == c-1) {
                        water[j][k] = island[j][k];
                        q.offer(new Node(j, k));
                    }
                }
            }

            int[] dx = {0, 0, -1, 1};
            int[] dy = {-1, 1, 0, 0};
            while (!q.isEmpty()) {
                Node node = q.poll();
                int x = node.x;
                int y = node.y;
                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    
                    if (nx >= 0 && nx < r && ny >= 0 && ny < c) {
                        if (water[x][y] < water[nx][ny]) {
                            water[nx][ny] = Math.max(island[nx][ny], water[x][y]);
                            q.offer(new Node(nx, ny));
                        }
                    }
                }
            }

            int result = 0;
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    if (water[j][k] > island[j][k]) {
                        result += water[j][k] - island[j][k];
                    }
                }
            }
            System.out.println("Case #" + (i + 1) + ": " + result);
        }
    }
}
