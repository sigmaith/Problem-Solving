// boj 14499 주사위 굴리기

import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int[] dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()); // (1 ≤ N, M ≤ 20)
        int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken()); // (0 ≤ x ≤ N-1, 0 ≤ y ≤ M-1)
        int K = Integer.parseInt(st.nextToken()); // (1 ≤ K ≤ 1,000)

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dice = new int[6];
        Arrays.fill(dice, 0);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken());
            int[] statusInfo = applyDirection(x, y, dir);
            if (statusInfo[0] < 0 || statusInfo[0] >= N || statusInfo[1] < 0 || statusInfo[1] >= M) {
                continue;
            } else {
                x = statusInfo[0];
                y = statusInfo[1];
                applyRule(x, y, dir);
            }
        }
    }

    private static int[] applyDirection(int x, int y, int dir) {
        if (dir == 1) {
            return new int[]{x, y + 1};
        } else if (dir == 2) {
            return new int[]{x, y - 1};
        } else if (dir == 3) {
            return new int[]{x - 1, y};
        } else if (dir == 4) {
            return new int[]{x + 1, y};
        } else {
            return new int[]{0, 0};
        }
    }

    private static void applyRule(int x, int y, int dir) {
        if (dir == 1) {
            int tmp = dice[2];
            dice[2] = dice[1];
            dice[1] = dice[0];
            dice[0] = dice[5];
            dice[5] = tmp;
        } else if (dir == 2) {
            int tmp = dice[0];
            dice[0] = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[5];
            dice[5] = tmp;
        } else if (dir == 3) {
            int tmp = dice[3];
            dice[3] = dice[1];
            dice[1] = dice[4];
            dice[4] = dice[5];
            dice[5] = tmp;
        } else if (dir == 4) {
            int tmp = dice[5];
            dice[5] = dice[4];
            dice[4] = dice[1];
            dice[1] = dice[3];
            dice[3] = tmp;
        }
        System.out.println(dice[1]);
        if (map[x][y] == 0) {
            map[x][y] = dice[5];
        } else {
            dice[5] = map[x][y];
            map[x][y] = 0;
        }
    }
}
