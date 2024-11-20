// boj 2636 치즈
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 초기 map 입력 받기

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int hour = 0;
        List<int[]> cheezeSquares = new ArrayList<>(); // 치즈칸
        Deque<int[]> q = new ArrayDeque<>();// 공기칸
        while (true) {
            if (isAllEmpty(map)) { // 모든 칸이 0이 될때까지 지속
                break;
            }

            q.clear();// 공기칸 초기화
            boolean[][] visited = new boolean[N][M];// 방문 체크배열 초기화
            q.offerLast(new int[]{0, 0}); // 항상 0
            visited[0][0] = true;

            // 공기칸 탐색하며 치즈칸 기록
            cheezeSquares.clear(); // 치즈칸 초기화
            while (!q.isEmpty()) {
                int[] air = q.pollFirst();
                int airX = air[0];
                int airY = air[1];
                for (int i = 0; i < 4; i++) { // 방향 탐색
                    int nextX = airX + dx[i];
                    int nextY = airY + dy[i];
                    if (isInMap(nextX, nextY) && !visited[nextX][nextY]) {
                        visited[nextX][nextY] = true;
                        if (map[nextX][nextY] == 0) {
                            q.offerLast(new int[]{nextX, nextY}); // 공기 큐에는 공기칸만 담는다.
                        } else if (map[nextX][nextY] == 1) {
                            cheezeSquares.add(new int[]{nextX, nextY}); // 공기와 근접한 치즈칸 기록
                        }
                    }
                }
            }
            // 기록된 치즈칸 지우기
            cheezeSquares.stream().forEach(ch -> map[ch[0]][ch[1]] = 0);
            hour++; // 1시간 소요
        }

        System.out.println(hour);
        System.out.println(cheezeSquares.size());
    }

    static boolean isAllEmpty(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isInMap(int x, int y){
        return x >= 0 && x < N && y >=0 && y < M;
    }
}
