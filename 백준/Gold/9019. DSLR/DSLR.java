// #9019 DSLR
import java.io.*;
import java.util.*;

class Node {
    private int number;
    private String order;
    Node(int number, String order) {
        this.number = number;
        this.order = order;
    }
    public int getNumber() {
        return this.number;
    }
    public String getOrder() {
        return this.order;
    } 
}

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            String src = st.nextToken();
            String dest = st.nextToken();

            solve_by_bfs(src, dest);
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void solve_by_bfs(String src, String dest) throws Exception{
       ArrayDeque<Node> q = new ArrayDeque<>();
       int[] visited = new int[10000];
       int srcInt = Integer.parseInt(src);
       int destInt = Integer.parseInt(dest);
       q.offerFirst(new Node(srcInt, ""));
       visited[Integer.parseInt(src)] = 1;

       while (!q.isEmpty()) {
            Node node = q.pollLast();
            int number = node.getNumber();
            String order = node.getOrder();
            if (number == destInt) {
                bw.write(order + "\n");
                return;
            }

            int d = formatD(number);
            if (visited[d] == 0) {
                q.offerFirst(new Node(d, order.concat("D")));
                visited[d] = 1;
            }
            int s = formatS(number);
            if (visited[s] == 0) {
                q.offerFirst(new Node(s, order.concat("S")));
                visited[s] = 1;
            }
            int l = formatL(number);
            if (visited[l] == 0) {
                q.offerFirst(new Node(l, order.concat("L")));
                visited[l] = 1;
            }
            int r = formatR(number);
            if (visited[r] == 0) {
                q.offerFirst(new Node(r, order.concat("R")));
                visited[r] = 1;
            }
       }
    }

    static int formatD(int num) {
        num = (num * 2) % 10000;
        return num; // format 함수로 정수를 문자열로 포맷팅 가능
    }

    static int formatS(int num) {
        return num == 0 ? 9999: num - 1;
    }

    static int formatL(int num) {
        int left = num / 1000; // 가장 왼쪽 자리
        int remainder = num % 1000; // 나머지 자리
        return remainder * 10 + left;
    }

    static int formatR(int num) {
        int right = num % 10; // 가장 오른쪽 자리
        int remainder = num / 10; // 나머지 자리
        return right * 1000 + remainder;
    }
}
