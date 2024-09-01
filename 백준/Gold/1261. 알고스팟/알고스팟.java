// boj 1261 알고스팟
import java.io.*;
import java.util.*;

class Node {
    int x;
    int y;
    int breakCnt;
    Node(int x, int y, int breakCnt) {
        this.x = x;
        this.y = y;
        this.breakCnt = breakCnt;
    }
}

public class Main {
    static int n, m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        m = Integer.parseInt(st.nextToken()); // 열의 개수
        n = Integer.parseInt(st.nextToken()); // 행의 개수

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Character.getNumericValue(s.charAt(j));
            }
        }

        Deque<Node> q = new ArrayDeque<Node>();
        int[][] visit = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visit[i][j] = 10000;
            }
        }

        visit[0][0] = 0;
        q.offerLast(new Node(0, 0, 0));
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        while (!q.isEmpty()) {
            Node node = q.pollFirst();
            // System.out.println("node.x = " + node.x + " node.y = " + node.y + " node.breakCnt = " + node.breakCnt);
            
            if (node.x == n-1 && node.y == m-1 && visit[node.x][node.y] > node.breakCnt) {
                visit[node.x][node.y] = node.breakCnt;
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int nextX = node.x + dx[i];
                int nextY = node.y + dy[i];
                if (isInMap(nextX, nextY)) {
                    if (map[nextX][nextY] == 0 && node.breakCnt < visit[nextX][nextY]) {
                        q.offerLast(new Node(nextX, nextY, node.breakCnt));
                        visit[nextX][nextY] = node.breakCnt;
                    } 
                    else if (map[nextX][nextY] == 1 && node.breakCnt + 1 < visit[nextX][nextY]){
                        q.offerLast(new Node(nextX, nextY, node.breakCnt + 1));
                        visit[nextX][nextY] = node.breakCnt + 1;
                    }
                }
            }
        }
        bw.write(visit[n-1][m-1] + "\n");
        bw.close();
        br.close();
    }

    static boolean isInMap(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return false;
        }
        return true;
    }
}
