// boj 14890 경사로

import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        boolean flat = true;
        // 행 검사
        for (int i = 0; i < N; i++) {
            flat = true;
            boolean[] canUse = new boolean[N];
            Arrays.fill(canUse, true);
            for (int j = 0; j < N - 1; j++) {
                if (map[i][j] != map[i][j + 1]) { // 오른쪽 방향
                    if (Math.abs(map[i][j] - map[i][j + 1]) != 1) {
                        flat = false;
                    }
                    if (map[i][j + 1] > map[i][j]) { // 클 경우
                        if (!canUse[j]) {
                            flat = false;
                        }
                        int j2 = j, l = 1;
                        while (j2 - 1 >= 0 && map[i][j2 - 1] == map[i][j]) {
                            if (!canUse[j2 - 1]) {
                                break;
                            }
                            j2 -= 1;
                            l += 1;
                        }
                        if (l < L) {
                            flat = false;
                        }
                    } else { // 작을경우
                        int tmp = 1, j2 = j + 1;
                        while (j2 + 1 < N && map[i][j2 + 1] == map[i][j + 1]) {
                            j2 += 1;
                            tmp += 1;
                        }
                        if (tmp < L) {
                            flat = false;
                        } else {
                            // 사용 불가로 표시해두기 todo: 개수셀때도 조건 비교
                            for (int k = j + 1; k < j + 1 + L; k++) {
                                canUse[k] = false;
                            }
                        }
                    }
                }
            }
            if (flat) {
                result += 1;
            }
        }

        // 열 검사
        for (int j = 0; j < N; j++) {
            flat = true;
            boolean[] canUse = new boolean[N];
            Arrays.fill(canUse, true);
            for (int i = 0; i < N - 1; i++) {
                if (map[i][j] != map[i + 1][j]) { // 아래 방향
                    if (Math.abs(map[i][j] - map[i + 1][j]) != 1) {
                        flat = false;
                    }
                    if (map[i + 1][j] > map[i][j]) { // 클 경우
                        if (!canUse[i]) {
                            flat = false;
                        }
                        int i2 = i, l = 1;
                        while (i2 - 1 >= 0 && map[i2 - 1][j] == map[i][j]) {
                            if (!canUse[i2 - 1]) {
                                break;
                            }
                            i2 -= 1;
                            l += 1;
                        }
                        if (l < L) {
                            flat = false;
                        }
                    } else { // 작을경우
                        int tmp = 1, i2 = i + 1;
                        while (i2 + 1 < N && map[i2 + 1][j] == map[i + 1][j]) {
                            i2 += 1;
                            tmp += 1;
                        }
                        if (tmp < L) {
                            flat = false;
                        } else {
                            // 사용 불가로 표시해두기 todo: 개수셀때도 조건 비교
                            for (int k = i + 1; k < i + 1 + L; k++) {
                                canUse[k] = false;
                            }
                        }
                    }
                }
            }
            if (flat) {
                result += 1;
            }
        }

        System.out.println(result);
    }
}