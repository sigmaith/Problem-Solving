// boj 17144 미세먼지 안녕!
import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] map = new int[R][C];
        ArrayDeque<Node> q = new ArrayDeque<>();
        int cleaner_1 = -1;
        int cleaner_2 = -1;

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1 && cleaner_1 == -1) {
                    cleaner_1 = i; // 공기청정기 1
                } else if (map[i][j] == -1 && cleaner_2 == -1) {
                    cleaner_2 = i; // 공기청정기 2
                } else if (map[i][j] >= 5) { 
                    q.offer(new Node(i, j)); // 먼지
                }
            }
        }
        // System.out.println("1 = " + cleaner_1 );
        // System.out.println("2 = " + cleaner_2);

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        while (true) {
            if (T == 0) {
                break;
            }
            // System.out.println(Arrays.deepToString(map)); // 디버깅
            int[][] newMap = new int[R][C]; // 새로운 맵 생성

            for(int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] < 5) {
                        newMap[i][j] = map[i][j];
                    }
                }
            }

            while (!q.isEmpty()) { // 먼지의 확산
                Node node = q.poll();
                int x = node.x;
                int y = node.y;
                int dustScattered = 0;
                for (int i = 0; i < 4; i++) {
                    int newX = x + dx[i];
                    int newY = y + dy[i];
                    if (0 <= newX && newX < R && 0 <= newY && newY < C && 
                        !(newX == cleaner_1 && newY == 0) && !(newX == cleaner_2 && newY == 0)) {
                            dustScattered += map[x][y] / 5; // 원래 먼지 자리의 먼지 감소량
                            newMap[newX][newY] += map[x][y] / 5; // 먼지 확산
                        }
                }
                newMap[x][y] += map[x][y] - dustScattered;    
            }   

            // System.out.println("먼지확산 뒤====================");
            // for (int i = 0; i < R; i++) {
            //     for (int j = 0; j < C; j++) {
            //         System.out.print(newMap[i][j] + " ");
            //     }
            //     System.out.println();
            // }
            // System.out.println("====================");

            for (int i = cleaner_1 - 1; i >= 1; i--) {  // 반시계 방향 공기 청소
                newMap[i][0] = newMap[i - 1][0];
            }
            for(int j = 0; j <= C - 2; j++) {
                newMap[0][j] = newMap[0][j + 1];
            }
            for (int i = 0; i <= cleaner_1 - 1; i++) {
                newMap[i][C-1] = newMap[i+1][C-1];
            }
            for (int j = C-1; j >=2; j--) {
                newMap[cleaner_1][j] = newMap[cleaner_1][j-1];
            }

            for (int i = cleaner_2 + 1; i <= R - 2; i++) { // 시계 방향 공기 청소
                newMap[i][0] = newMap[i+1][0];
            }
            for (int j = 0; j <= C - 2; j++) {
                newMap[R-1][j] = newMap[R-1][j+1];
            }
            for (int i = R-1; i >= cleaner_2 + 1; i--) {
                newMap[i][C-1] = newMap[i-1][C-1];
            }
            for (int j = C-1; j >= 2; j--) {
                newMap[cleaner_2][j] = newMap[cleaner_2][j-1];
            }
            newMap[cleaner_1][1] = 0;
            newMap[cleaner_2][1] = 0;

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (newMap[i][j] >= 5) {
                        q.offer(new Node(i, j));
                    }
                }
            }

            // map = newMap; (x) call by reference
            // System.out.println("공기청소 뒤====================");/////
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    map[i][j] = newMap[i][j];
                    // System.out.print(map[i][j] + " "); ////
                }
                // System.out.println();/////////
            }
            // System.out.println("====================");/////
            T -= 1; // 1초 경과
        }

        // System.out.println(Arrays.deepToString(map));
        int result = 0;
        for (int i = 0; i < R; i++) {
             for (int j = 0; j < C; j++) {
                if (map[i][j] != -1) {
                    result += map[i][j];
                }
             }
        }
        System.out.println(result);

    }
}
