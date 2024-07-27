// #2206 벽 부수고 이동하기
import java.io.*;
import java.util.*;

class Node{
    private int x;
    private int y;
    private int flag;
    public Node(int x, int y, int flag) {
        this.x = x;
        this.y = y;
        this.flag = flag;
    }
    public int getX(){ 
        return this.x; 
    }
    public int getY() {
        return this.y;
    }
    public int getFlag() {
        return this.flag;
    }
}

public class Main {
    static int n;
    static int m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int[][][] visited = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }

        

        ArrayDeque<Node> q = new ArrayDeque<>();
        visited[0][0][0] = 1;
        visited[0][0][1] = 1;
        q.offerLast(new Node(0, 0, 0));

        int result = -1;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            Node node = q.pollFirst();
            // System.out.println("x = " + node.getX() + " y = " + node.getY() + " num = " + node.getNum() + " flag = " + node.getFlag());
            if (node.getX() == n-1 && node.getY() == m-1) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int x = node.getX() + dx[i];
                int y = node.getY() + dy[i];
                int flag = node.getFlag();

                if (isInMap(x, y)) {
                    if (map[x][y] == 0 && visited[x][y][flag] == 0) {
                        visited[x][y][flag] = visited[node.getX()][node.getY()][flag] + 1;
                        q.offerLast(new Node(x, y, flag));
                    }

                    else if (map[x][y] == 1 && flag == 0 && visited[x][y][1] == 0) {
                        visited[x][y][1] = visited[node.getX()][node.getY()][flag] + 1;
                        q.offerLast(new Node(x, y, 1));
                    }
                }
            }
        }

        if (visited[n-1][m-1][1] != 0 && visited[n-1][m-1][0] != 0) {
            result = Math.min(visited[n-1][m-1][1], visited[n-1][m-1][0]);
        }
        else{
            if (visited[n-1][m-1][1] != 0 ) result = visited[n-1][m-1][1];
            else if (visited[n-1][m-1][0] != 0 ) result = visited[n-1][m-1][0];
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean isInMap(int x, int y) {
        if (x < 0 || y < 0 || x > n-1 || y > m-1) {return false;}
        return true;
    }
}