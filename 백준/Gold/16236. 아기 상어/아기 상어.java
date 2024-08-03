// #16236 아기 상어
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] sea;
    static int sharkSize;
    static boolean[][] visited;
    private static class Node{
        private int x;
        private int y;
        private int move;

        Node(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getMove() {
            return this.move;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        sea = new int[n][n];
        int sharkX = 0, sharkY = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
                if (sea[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                }
            }
        } 

        sharkSize = 2;
        int fishAte = 0;
        PriorityQueue<Node> q = new PriorityQueue<Node>((a, b) -> {
            if (a.getMove() != b.getMove()) {
                return Integer.compare(a.getMove(), b.getMove());
            }
            else {
                if (a.getX() != b.getX()) {
                    return Integer.compare(a.getX(), b.getX());
                }
                else {
                    return Integer.compare(a.getY(), b.getY());
                }
            }
        });
        visited = new boolean[n][n];

        q.offer(new Node(sharkX, sharkY, 0));
        visited[sharkX][sharkY] = true;

        int[] dx = {-1, 0, 0, 1}; // 위쪽, 왼쪽 순으로 탐색
        int[] dy = {0, -1, 1, 0};
        int wholeTime = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.getX(), y = node.getY(), move = node.getMove();

            // 물고기가 있고 물고기 먹을수 있으면
            if (sea[x][y]!= 0 && sea[x][y] < sharkSize) {
                // System.out.println("x =" + x + " y =" + y + " move =" + move + "sec =" + wholeTime);

                fishAte += 1; // 상어 먹은 횟수 늘리고 
                if (fishAte == sharkSize) { // 상어 몸집만큼 물고기를 먹었을 시
                    fishAte = 0; // 먹은 물고기 초기화
                    sharkSize += 1; // 상어 몸집 키우기
                }
                sea[sharkX][sharkY] = 0; ////////////////////////// 원래있던 상어 위치도 비워줘야함!(이동했으므로)
                sharkX = x; sharkY = y; // 상어 위치 초기화
                wholeTime += move; // 시간 추가
                q = new PriorityQueue<Node>((a, b) -> { // 우선순위큐 초기화
                    if (a.getMove() != b.getMove()) {
                        return Integer.compare(a.getMove(), b.getMove());
                    } else {
                        if (a.getX() != b.getX()) {
                            return Integer.compare(a.getX(), b.getX());
                        } else {
                            return Integer.compare(a.getY(), b.getY());
                        }
                    }
                }); 
                q.offer(new Node(sharkX, sharkY, 0)); // 상어 위치 다시 넣기
                visited = new boolean[n][n]; // 초기화
                visited[sharkX][sharkY] = true; // 방문 체크
                sea[x][y] = 0; // 먹어서 빈자리
                continue; //이거 안하면 상어자리 poll을 안하고 탐색하게돼서 꼬인다
            }
            
            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                if (isInSea(nextX, nextY) && !isVisited(nextX, nextY)
                    && canPass(nextX, nextY)) { 
                    q.offer(new Node(nextX, nextY, move + 1));
                    visited[nextX][nextY] = true;
                }   
            }
        }
        bw.write(wholeTime + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean isInSea(int x, int y) { // 맵의 범위 안?
        if (x < 0 || x > n - 1 || y < 0 || y > n - 1) { 
            return false;
        }
        return true;
    }

    static boolean isVisited(int x, int y) { // 방문?
        if (visited[x][y]) return true;
        else return false;
    }

    static boolean canPass(int x, int y) { // 지나갈 수?
        if (sea[x][y] == 0 || sea[x][y] <= sharkSize) { 
            return true;
        }
        else return false;
    }
}
