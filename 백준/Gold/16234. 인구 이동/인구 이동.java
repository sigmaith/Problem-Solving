// boj 16234 인구 이동
import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int x, y;
        Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());   

        int[][] A = new int[N][N]; // 0 ≤ A[r][c] ≤ 100
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());    
            }
        }
        
        int daysMoved = 0;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        while (true) {
            boolean[][] visited = new boolean[N][N];
            boolean moved = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) {
                        continue;
                    }
                    ArrayDeque<Node> q = new ArrayDeque<>();
                    ArrayDeque<Node> q_search = new ArrayDeque<>();
                    q_search.offer(new Node(i, j));

                    int federation = 0;
                    int population = 0;
                    while(!q_search.isEmpty()) {
                        Node node = q_search.poll();
                        boolean hasFederationFlag = false;

                        for (int idx = 0; idx < 4; idx++) {
                            int x = node.x + dx[idx];
                            int y = node.y + dy[idx];
                            if (0 <= x && x < N && 0 <= y && y < N &&
                                    L <= Math.abs(A[node.x][node.y] - A[x][y]) 
                                    && Math.abs(A[node.x][node.y] - A[x][y]) <= R) {
                                moved = true;
                                if (!visited[node.x][node.y] && !hasFederationFlag) {
                                    visited[node.x][node.y] = true;
                                    q.offer(new Node(i, j));
                                    hasFederationFlag = true;
                                    federation += 1;
                                    population += A[node.x][node.y];
                                }
                                if (!visited[x][y]) {
                                    q.offer(new Node(x, y));
                                    federation += 1;
                                    population += A[x][y];
                                    visited[x][y] = true;
                                    q_search.offer(new Node(x, y));
                                }
                            }
                        }
                    }
                    if (federation != 0) {
                        int populationDistributed = population / federation;
                        // System.out.println(population + " " + federation + "분산인구 = " + populationDistributed);
                        while (!q.isEmpty()) {
                            Node node = q.poll();
                            A[node.x][node.y] = populationDistributed;
                        }
                    }
                }
            }
            // System.out.println(Arrays.deepToString(A));
            if (!moved) {
                break;
            } else {
                daysMoved += 1;
            }
        }
        System.out.println(daysMoved);
    }
}
