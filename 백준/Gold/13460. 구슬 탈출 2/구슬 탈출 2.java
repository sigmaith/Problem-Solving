// #13460 구슬 탈출2
import java.io.*;
import java.util.*;

class Main{
    public static class Node { //큐에 담길 노드
        int x_red;
        int y_red;
        int x_blue;
        int y_blue;
        int record;
        Node(int x_red, int y_red, int x_blue, int y_blue, int record) {
            this.x_red = x_red;
            this.y_red = y_red;
            this.x_blue = x_blue;
            this.y_blue = y_blue;
            this.record = record;
        }
    }
    public static int[] move_red(int dx, int dy){
        int[] next = new int[2];
        next[0] = x_red;
        next[1] = y_red;
        if(board[x_red][y_red] != 'O'){ // 홀이면 움직이지 않아
            while ( board[next[0] + dx][next[1] + dy] == 'O' // 다음칸이 홀이거나
                    || (board[next[0] + dx][next[1] + dy] == '.' &&!(next[0] + dx == x_blue && next[1] + dy == y_blue))) {
                        // 빈칸이고 파란공이 없을때
                next[0] += dx;
                next[1] += dy;
                if (board[next[0]][next[1]] == 'O') break;
            }
        }
        return next;
    }
    
    public static int[] move_blue(int dx, int dy) {
        int[] next = new int[2];
        next[0] = x_blue;
        next[1] = y_blue;
        if(board[x_blue][y_blue] != 'O'){ // 홀이면 움직이지 않아
            while (board[next[0] + dx][next[1] + dy] == 'O' // 다음칸이 홀이거나
                    || (board[next[0] + dx][next[1] + dy] == '.' && !(next[0] + dx == x_red && next[1] + dy == y_red))) {
                        // 빈칸이고 빨간공이 없을때
                next[0] += dx;
                next[1] += dy;
                if (board[next[0]][next[1]] == 'O') break;
            }
        }
        return next;
    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static char[][] board;
    static Deque<Node> queue;
    static int x_red = 0, y_red = 0, x_blue = 0, y_blue = 0;
    public static void main(String[] args)throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        queue = new ArrayDeque<Node>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = { 0, 0, -1, 1};
        
        int result = -1;
        Set<List<Integer>> visited = new HashSet<>();

        for(int i=0; i<n; i++){ // 보드 입력받기
            String str = br.readLine();
            for(int j=0; j<m; j++){
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'R') {
                    x_red = i;
                    y_red = j;
                    board[i][j] = '.';
                }
                else if (board[i][j] == 'B'){
                    x_blue = i;
                    y_blue = j;
                    board[i][j] = '.';
                }
            }
        }

        queue.addLast(new Node(x_red, y_red, x_blue, y_blue,0));
        visited.add(Arrays.asList(x_red, y_red, x_blue, y_blue));

        while(!queue.isEmpty()){
            Node node = queue.pollFirst();
            

            if (node.record > 10) {
                break;
            }
            // if (board[node.x_red][node.y_red] == 'O' && board[node.x_blue][node.y_blue] == 'O') {
            //     break;
            // }
            if (board[node.x_red][node.y_red] == 'O' && board[node.x_blue][node.y_blue] != 'O'){
                result = node.record;
                break;
            }
            if (board[node.x_blue][node.y_blue] == 'O') {
                continue;
            }

            for (int i=0; i<4; i++){ // 빨간 구슬이 아직 0에 들어가지 않았을때 
                x_red = node.x_red;
                y_red = node.y_red;
                x_blue = node.x_blue;
                y_blue = node.y_blue;

                int[] next1 = move_red(dx[i], dy[i]);
                x_red = next1[0]; 
                y_red = next1[1];
                int[] next2 = move_blue(dx[i], dy[i]);
                x_blue = next2[0]; 
                y_blue = next2[1];
                int[] next3 = move_red(dx[i], dy[i]);
                x_red = next3[0];
                y_red = next3[1];
                int[] next4 = move_blue(dx[i], dy[i]);
                x_blue = next4[0];
                y_blue = next4[1];

                if(x_red == x_blue && y_red == y_blue && board[x_red][y_red] != 'O'){
                    if (Math.abs(x_red - node.x_red) < Math.abs(x_blue - node.x_blue)
                        || Math.abs(y_red - node.y_red) < Math.abs(y_blue - node.y_blue)){
                        x_blue -= dx[i];
                        y_blue -= dy[i];
                    }
                    else{
                        x_red -= dx[i];
                        y_red -= dy[i];
                    }
                }
                List<Integer> next = Arrays.asList(x_red, y_red, x_blue, y_blue);
                if (!visited.contains(next)){
                    visited.add(next);
                    queue.addLast(new Node(x_red, y_red, x_blue, y_blue, node.record+1));
                }
            }
            
        }

        
        bw.write(result + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}
