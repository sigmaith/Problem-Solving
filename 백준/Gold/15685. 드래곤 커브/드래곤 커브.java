// boj 15685 드래곤 커브

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[101][101];
        for (int i = 0; i < N; i++) { // 드래곤 커브 기록
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(
                    st.nextToken()); // 0 ≤ x, y ≤ 100 -> n^2도 10000
            int d = Integer.parseInt(st.nextToken()), g = Integer.parseInt(st.nextToken()); // 0 ≤ d ≤ 3, 0 ≤ g ≤ 10

            ArrayList<int[]> records = new ArrayList<>();
            for (int gen = 0; gen <= g; gen++) { // 세대 동안
                evolve(gen, records, d);
            }
            map[y][x] = true;
            for (int[] info : records) {
                y += info[0];
                x += info[1];
                map[y][x] = true;
            }
        }

        int result = 0;
        int[] dx = {0, 0, 1, 1};
        int[] dy = {0, 1, 1, 0};
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];
                    if (ni <= 100 && nj <= 100 && map[ni][nj]) {
                        cnt += 1;
                    }
                }
                if (cnt == 4) {
                    result += 1;
                }
            }
        }
        System.out.println(result);
    }

    private static void evolve(int g, ArrayList<int[]> records, int dir) {
        if (g == 0) {
            if (dir == 0) {
                records.add(new int[]{0, 1});
            } else if (dir == 1) {
                records.add(new int[]{-1, 0});
            } else if (dir == 2) {
                records.add(new int[]{0, -1});
            } else if (dir == 3) {
                records.add(new int[]{1, 0});
            }
        } else if (g == 1) {
            if (dir == 0 || dir == 2) {
                expand1(records);
            } else {
                expand2(records);
            }
        } else {
            if (dir == 0 || dir == 2) {
                expand2(records);
            } else {
                expand1(records);
            }
        }
    }

    private static void expand1(ArrayList<int[]> records) {
        int size = records.size();
        for (int i = size - 1; i >= 0; i--) {
            int c = (size - 1 - i) % 2;
            int[] info = records.get(i);
            if (c == 0) {
                records.add(new int[]{info[1] * -1, info[0] * -1});
            } else {
                records.add(new int[]{info[1], info[0]});
            }
        }
    }

    private static void expand2(ArrayList<int[]> records) {
        int size = records.size();
        for (int i = size - 1; i >= 0; i--) {
            int c = (size - 1 - i) % 2;
            int[] info = records.get(i);
            if (c == 0) {
                records.add(new int[]{info[1], info[0]});
            } else {
                records.add(new int[]{info[1] * -1, info[0] * -1});
            }
        }
    }
}