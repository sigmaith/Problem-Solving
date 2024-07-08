// #16928 뱀과 사다리 게임
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static class Node{
        int moveNum;
        int location;
        public Node(int moveNum, int location){
            this.moveNum = moveNum;
            this.location = location;
        }
    }
    public static void main(String[] main) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 사다리 정보
        int m = Integer.parseInt(st.nextToken()); // 뱀 정보
        int[][] map = new int[10][10]; // 맵 정보

        HashMap<Integer, Integer> ladder = new HashMap<>(); // 사다리 정보 기록 -> key 찾는 속도가 빠른 Hash Map 이용: O(1)
        for(int i = 0; i < n; i++) { 
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            ladder.put(start-1, end-1);
        }

        HashMap<Integer, Integer> snake = new HashMap<>(); // 뱀 정보 기록 -> key 찾는 속도가 빠른 Hash Map 이용: O(1)
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            snake.put(start-1, end-1); // zero-base index 사용
        }

        ArrayDeque<Node> q = new ArrayDeque<>(); // 탐색 정보 
        q.addLast(new Node(0, 0));
        while (!q.isEmpty()){
            Node node = q.pollFirst();
            int moveNum = node.moveNum;
            int location = node.location;
            if (location == 99) {
                bw.write(moveNum+ "\n"); 
                break;
            }
            for (int i = 1; i <= 6; i++){ // 6개 주사위
                int next = location + i;
                int x_next = next / 10;
                int y_next = next % 10;
                if (next > 99) break; // 벗어나면 break
                if (ladder.get(next) != null) { // 사다리 이동 * 같은 칸에 사다리와 뱀이 있을 수 없음. (문제조건)
                    next = ladder.get(next);
                    x_next = next / 10;
                    y_next = next % 10;
                }
                if (snake.get(next) != null) { // 뱀 이동
                    next = snake.get(next);
                    x_next = next / 10;
                    y_next = next % 10;
                }
                if (map[x_next][y_next] == 0 || map[x_next][y_next] > moveNum + 1) 
                    // 방문하지 않았을 때 혹은 더 작은 이동횟수로 갈 수 있을 때
                    q.addLast(new Node(moveNum + 1, next));
                    map[x_next][y_next] = moveNum + 1;
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }    
}
