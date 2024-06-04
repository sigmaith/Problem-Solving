//  # 10866 덱
import java.util.*;
import java.io.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new ArrayDeque<Integer>();

        for (int i=0; i<n; i++){
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input, " ");
            String command = st.nextToken();

            switch (command){
                case "push_front":
                    int element = Integer.parseInt(st.nextToken());
                    deque.addFirst(element);
                    break;
                case "push_back":
                    int element2 = Integer.parseInt(st.nextToken());
                    deque.addLast(element2);
                    break;
                case "pop_front":
                    if (deque.isEmpty())
                        bw.write("-1\n");
                    else
                        bw.write(deque.pollFirst() + "\n");
                    break;
                case "pop_back":
                    if (deque.isEmpty()) bw.write("-1\n");
                    else bw.write(deque.pollLast() + "\n");
                    break;
                case "size":
                    bw.write(deque.size() + "\n");
                    break;
                case "empty":
                    if (deque.isEmpty()) bw.write("1\n");
                    else bw.write("0\n");
                    break;
                case "front":
                    if (deque.isEmpty()) bw.write("-1\n");
                    else bw.write(deque.peekFirst() + "\n");
                    break;
                case "back":
                    if (deque.isEmpty()) bw.write("-1\n");
                    else bw.write(deque.peekLast() + "\n");
                    break;
            }
        }
        bw.flush(); // Flush once at the end
        br.close();
        bw.close();
    }
}
