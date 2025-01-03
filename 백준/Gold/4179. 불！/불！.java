// boj 4179 불!

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        int[][] timePerson = new int[R][C];
        boolean[][] visitedPerson = new boolean[R][C];
        int[][] timeFire = new int[R][C];
        boolean[][] visitedFire = new boolean[R][C];

        Deque<int[]> qPerson = new ArrayDeque<>();
        Deque<int[]> qFire = new ArrayDeque<>();

        int[] person = new int[2];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            Arrays.fill(timePerson[i], -1);
            Arrays.fill(timeFire[i], -1);
            for (int j = 0; j < C; j++) {

                if (str.charAt(j) == 'J') {
                    map[i][j] = '.';
                    visitedPerson[i][j] = true;
                    person[0] = i;
                    person[1] = j;
                    timePerson[i][j] = 0;
                    qPerson.offerLast(new int[]{i, j, 0});
                } else if (str.charAt(j) == 'F') {
                    map[i][j] = '.';
                    visitedFire[i][j] = true;
                    timeFire[i][j] = 0;
                    qFire.offerLast(new int[]{i, j, 0});
                } else {
                    map[i][j] = str.charAt(j);
                }
            }
        }

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        while (!qPerson.isEmpty()) {
            int[] info = qPerson.pollFirst();
            int x = info[0], y = info[1], time = info[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visitedPerson[nx][ny] && map[nx][ny] == '.') {
                    visitedPerson[nx][ny] = true;
                    timePerson[nx][ny] = time + 1;
                    qPerson.offerLast(new int[]{nx, ny, time + 1});
                }
            }
        }

        while (!qFire.isEmpty()) {
            int[] info = qFire.pollFirst();
            int x = info[0], y = info[1], time = info[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visitedFire[nx][ny] && map[nx][ny] == '.') {
                    visitedFire[nx][ny] = true;
                    timeFire[nx][ny] = time + 1;
                    qFire.offerLast(new int[]{nx, ny, time + 1});
                }
            }
        }

        boolean canEscape = false;
        int time = Integer.MAX_VALUE;

        for (int j = 0; j < C; j++) {
            if (timePerson[0][j] != -1 && (timeFire[0][j] == -1 ||
                    (timeFire[0][j] != -1 && timePerson[0][j] < timeFire[0][j]))) { // 사람이 도달한 경우에 대해 불이 도달하지 않았거나 불보다 먼저 도달한 경우
                canEscape = true;
                if (time > timePerson[0][j] + 1) {
                    time = timePerson[0][j] + 1;
                }
            } else if (timePerson[R - 1][j] != -1 && (timeFire[R - 1][j] == -1 ||
                    (timeFire[R - 1][j] != -1 && timePerson[R - 1][j] < timeFire[R - 1][j]))) {
                canEscape = true;
                if (time > timePerson[R - 1][j] + 1) {
                    time = timePerson[R - 1][j] + 1;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            if (timePerson[i][0] != -1 && (timeFire[i][0] == -1 || (
                    timeFire[i][0] != -1 && timePerson[i][0] < timeFire[i][0]))) {
                canEscape = true;
                if (time > timePerson[i][0] + 1) {
                    time = timePerson[i][0] + 1;
                }
            } else if (timePerson[i][C - 1] != -1 && (timeFire[i][C - 1] == -1 ||
                    (timeFire[i][C - 1] != -1 && timePerson[i][C - 1] < timeFire[i][C - 1]))) {
                canEscape = true;
                if (time > timePerson[i][C - 1] + 1) {
                    time = timePerson[i][C - 1] + 1;
                }
            }
        }

        if (canEscape) {
            System.out.println(time);
        } else {
            System.out.println("IMPOSSIBLE");
        }
    }
}

