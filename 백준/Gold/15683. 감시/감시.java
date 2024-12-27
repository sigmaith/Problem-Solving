// boj 15683 감시
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] office;
    static List<int[]> CCTVs;
    static boolean[][] seeingSpots;
    static int result;
    public static void main(String[] args) throws Exception {
        //  CCTV의 방향을 적절히 정해서, 사각 지대의 최소 크기를 구하는 프로그램
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        office = new int[N][M]; //  (1 ≤ N, M ≤ 8)

        CCTVs = new ArrayList<>(); // cctv 모음
        seeingSpots = new boolean[N][M]; // 감시 가능한 곳 체크
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] =  Integer.parseInt(st.nextToken()); // 1 ~ 5는 cctv, 6은 벽, 0은 빈칸
                if (office[i][j] >= 1 && office[i][j] <= 5) {
                    CCTVs.add(new int[]{i, j}); // CCTV 의 최대 개수는 8개를 넘지 않는다.
                    seeingSpots[i][j] = true;
                } else if (office[i][j] == 6) {
                    seeingSpots[i][j] = true;
                }
            }
        }

        result = N * M; // cctv 개수가 0일 경우
        simulate(0);
        System.out.println(result);
    }

    private static void simulate(int idx) {
        if (idx == CCTVs.size()) { // 사각지대 개수 세고 result 업데이트
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!seeingSpots[i][j]) {
                        cnt++;
                    }
                }
            }
            if (result > cnt) {
                result = cnt;
            }
            return;
        }

        int[] info = CCTVs.get(idx);
        int x = info[0], y = info[1];
        if (office[x][y] == 1) {
            CCTV1(x, y, idx);
        } else if (office[x][y] == 2) {
            CCTV2(x, y, idx);
        } else if (office[x][y] == 3) {
            CCTV3(x, y, idx);
        } else if (office[x][y] == 4) {
            CCTV4(x, y, idx);
        } else if (office[x][y] == 5) {
            CCTV5(x, y, idx);
        }
    }

    private static void CCTV1(int x, int y, int idx) {
        List<int[]> check = new ArrayList<>();
        for (int i = x - 1; i >= 0; i--) { // 상
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
                check.add(new int[]{i, y});
            }
        }
        simulate(idx + 1);
        for (int[] info : check) {
            seeingSpots[info[0]][info[1]] = false;
        }
        check.clear(); // check 초기화
        for (int i = x + 1; i < N; i++) { // 하
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
                check.add(new int[]{i, y});
            }
        }
        simulate(idx + 1);
        for (int[] info : check) {
            seeingSpots[info[0]][info[1]] = false;
        }
        check.clear(); // check 초기화
        for (int j = y - 1; j >= 0; j--) { // 좌
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
                check.add(new int[]{x, j});
            }
        }
        simulate(idx + 1);
        for (int[] info : check) {
            seeingSpots[info[0]][info[1]] = false;
        }
        check.clear(); // check 초기화
        for (int j = y + 1; j < M; j++) { // 우
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
                check.add(new int[]{x, j});
            }
        }
        simulate(idx + 1);
        for (int[] info : check) {
            seeingSpots[info[0]][info[1]] = false;
        }
        check.clear(); // check 초기화
    }

    private static void CCTV2(int x, int y, int idx) {
        List<int[]> check = new ArrayList<>();
        for (int i = x - 1; i >= 0; i--) { // 상
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
                check.add(new int[]{i, y});
            }
        }
        for (int i = x + 1; i < N; i++) { // 하
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
                check.add(new int[]{i, y});
            }
        }

        simulate(idx + 1);
        for (int[] info : check) {
            seeingSpots[info[0]][info[1]] = false;
        }
        check.clear(); // check 초기화

        for (int j = y - 1; j >= 0; j--) { // 좌
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
                check.add(new int[]{x, j});
            }
        }
        for (int j = y + 1; j < M; j++) { // 우
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
                check.add(new int[]{x, j});
            }
        }
        simulate(idx + 1);
        for (int[] info : check) {
            seeingSpots[info[0]][info[1]] = false;
        }
        check.clear(); // check 초기화
    }

    private static void CCTV3(int x, int y, int idx) {
        List<int[]> check = new ArrayList<>();
        for (int i = x - 1; i >= 0; i--) {
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
                check.add(new int[]{i, y});
            }
        }
        for (int j = y + 1; j < M; j++) {
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
                check.add(new int[]{x, j});
            }
        } // 상우
        simulate(idx + 1);
        for (int[] info : check) {
            seeingSpots[info[0]][info[1]] = false;
        }
        check.clear(); // check 초기화

        for (int j = y + 1; j < M; j++) {
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
                check.add(new int[]{x, j});
            }
        }
        for (int i = x + 1; i < N; i++) {
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
                check.add(new int[]{i, y});
            }
        } // 우하
        simulate(idx + 1);
        for (int[] info : check) {
            seeingSpots[info[0]][info[1]] = false;
        }
        check.clear(); // check 초기화

        for (int i = x + 1; i < N; i++) {
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
                check.add(new int[]{i, y});
            }
        }
        for (int j = y - 1; j >= 0; j--) {
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
                check.add(new int[]{x, j});
            }
        } // 하좌
        simulate(idx + 1);
        for (int[] info : check) {
            seeingSpots[info[0]][info[1]] = false;
        }
        check.clear(); // check 초기화

        for (int j = y - 1; j >= 0; j--) {
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
                check.add(new int[]{x, j});
            }
        }
        for (int i = x - 1; i >= 0; i--) {
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
                check.add(new int[]{i, y});
            }
        } // 좌상
        simulate(idx + 1);
        for (int[] info : check) {
            seeingSpots[info[0]][info[1]] = false;
        }
        check.clear(); // check 초기화
    }

    private static void CCTV4(int x, int y, int idx) {
        List<int[]> check = new ArrayList<>();
        for (int j = y - 1; j >= 0; j--) { // 좌
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
                check.add(new int[]{x, j});
            }
        }
        for (int i = x - 1; i >= 0; i--) { // 상
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
                check.add(new int[]{i, y});
            }
        }
        for (int j = y + 1; j < M; j++) { // 우
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
                check.add(new int[]{x, j});
            }
        }
        simulate(idx + 1);
        for (int[] info : check) {
            seeingSpots[info[0]][info[1]] = false;
        }
        check.clear(); // check 초기화
        /* */

        for (int i = x - 1; i >= 0; i--) { // 상
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
                check.add(new int[]{i, y});
            }
        }
        for (int j = y + 1; j < M; j++) { // 우
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
                check.add(new int[]{x, j});
            }
        }
        for (int i = x + 1; i < N; i++) { // 하
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
                check.add(new int[]{i, y});
            }
        }
        simulate(idx + 1);
        for (int[] info : check) {
            seeingSpots[info[0]][info[1]] = false;
        }
        check.clear(); // check 초기화
        /* */
        for (int j = y + 1; j < M; j++) { // 우
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
                check.add(new int[]{x, j});
            }
        }
        for (int i = x + 1; i < N; i++) { // 하
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
                check.add(new int[]{i, y});
            }
        }
        for (int j = y - 1; j >= 0; j--) { // 좌
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
                check.add(new int[]{x, j});
            }
        }
        simulate(idx + 1);
        for (int[] info : check) {
            seeingSpots[info[0]][info[1]] = false;
        }
        check.clear(); // check 초기화
        /* */
        for (int i = x + 1; i < N; i++) { // 하
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
                check.add(new int[]{i, y});
            }
        }
        for (int j = y - 1; j >= 0; j--) { // 좌
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
                check.add(new int[]{x, j});
            }
        }
        for (int i = x - 1; i >= 0; i--) { // 상
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
                check.add(new int[]{i, y});
            }
        }
        simulate(idx + 1);
        for (int[] info : check) {
            seeingSpots[info[0]][info[1]] = false;
        }
        check.clear(); // check 초기화
    }

    private static void CCTV5(int x, int y, int idx) {
        for (int i = x - 1; i >= 0; i--) { // 상
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
            }
        }
        for (int i = x + 1; i < N; i++) { // 하
            if (office[i][y] == 6) {
                break;
            }
            if (!seeingSpots[i][y]) {
                seeingSpots[i][y] = true;
            }
        }
        for (int j = y - 1; j >= 0; j--) { // 좌
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
            }
        }
        for (int j = y + 1; j < M; j++) { // 우
            if (office[x][j] == 6) {
                break;
            }
            if (!seeingSpots[x][j]) {
                seeingSpots[x][j] = true;
            }
        }
        simulate(idx + 1);
    }
}
