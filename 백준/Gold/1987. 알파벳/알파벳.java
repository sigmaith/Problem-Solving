// 1987: 알파벳
import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static HashSet hashSet;
    static int max_result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.valueOf(st.nextToken());
        C = Integer.valueOf(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        max_result = 1;
        hashSet = new HashSet<>();
        hashSet.add(map[0][0]);
        backtracking(0, 0, 1);

        bw.write(max_result + "\n");
        bw.flush();
        br.close();
        bw.close();
    }   

    public static void backtracking(int x, int y, int count) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (isInMap(nextX, nextY)) { // 범위 체크
                if (hashSet.contains(map[nextX][nextY])){ // 중복
                    max_result = Math.max(max_result, count); 
                } else {
                    hashSet.add(map[nextX][nextY]); // 중복 x
                    backtracking(nextX, nextY, count + 1);
                    hashSet.remove(map[nextX][nextY]);
                }
            }
        }

    }

    public static boolean isInMap(int x, int y) {
        if (x >= R || x < 0 || y >= C || y < 0) {
            return false;
        }
        return true;
    }
}
