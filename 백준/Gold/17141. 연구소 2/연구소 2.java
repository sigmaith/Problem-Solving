// boj 17141 연구소 2
import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static List<int[]> virus;
    static Deque<int[]> q;
    static int result = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 연구소 크기
        int M = Integer.parseInt(st.nextToken()); // 바이러스 놓을 수 있는 자리 수
        map = new int[N][N];
        virus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virus.add(new int[]{i, j}); // 바이러스 자리 추가
                }
            }
        }

        q = new ArrayDeque<>();
        selectVirus(0, 0, M);
        System.out.println(result);
    }

    private static void selectVirus(int idx, int cnt, int M) { // 바이러스 놓을 개수
        if (q.size() == M) {
            simulate(); // 연구소 시뮬레이션 돌리기
            return;
        }

        if (cnt >= M || idx > virus.size() - 1) {
            return;
        }

        q.offerLast(virus.get(idx));
        selectVirus(idx + 1, cnt + 1, M);
        q.pollLast();
        selectVirus(idx + 1, cnt, M);
    }

    private static void simulate() {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        int N = map.length;
        boolean[][] visited = new boolean[N][N];
        int[][] mapCopy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    mapCopy[i][j] = -1; // 벽
                    visited[i][j] = true;
                } else { // 0 이나 2 (퍼질 수 있는 곳이나 바이러스가 놓일 수 있는 곳)
                    mapCopy[i][j] = -2; // 퍼질 수 있는 곳
                }
            }
        }
        Deque<int[]> qCopy = new ArrayDeque<>(); // qCopy를 만들어야 원본 손실이 안된다
        for (int[] info: q) {
            qCopy.add(info);
        }
        for (int[] info : qCopy) {
            visited[info[0]][info[1]] = true;
            mapCopy[info[0]][info[1]] = 0; // 바이러스 퍼지는 곳
        }
        while (!qCopy.isEmpty()) {
            int[] info = qCopy.pollFirst();
            for (int i = 0; i < 4; i++) {
                int nx = info[0] + dx[i];
                int ny = info[1] + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && mapCopy[nx][ny] == -2) {
                    visited[nx][ny] = true;
                    mapCopy[nx][ny] = mapCopy[info[0]][info[1]] + 1;
                    qCopy.offerLast(new int[]{nx, ny});
                }
            }
        }

        boolean flag = true;
        int time = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (mapCopy[i][j] == -2) {
                    flag = false;
                }
                if (time < mapCopy[i][j]) {
                    time = mapCopy[i][j];
                }
            }
        }

        if (flag && result == -1) {
            result = time;
        } else if (flag && result != -1) {
            result = Math.min(result, time);
        }
    }
}