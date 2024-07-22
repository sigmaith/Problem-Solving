// #7662 이중 우선순위 큐
import java.io.*;
import java.util.*;

class Node {
    int idx;
    int num;
    public Node(int idx, int num) {
        this.idx = idx;
        this.num = num;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Node node;

        int t = Integer.parseInt(br.readLine()); // 시행 횟수
        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            ArrayList<Boolean> deleted = new ArrayList<>();
            int idx = 0;
            PriorityQueue<Node> minHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.num, o2.num));
            PriorityQueue<Node> maxHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.num, o1.num));

            for (int j = 0; j < k; j++) { // 연산 횟수



                String[] opr = br.readLine().split(" ");
                if (opr[0].equals("I")) {
                    deleted.add(false); 
                    // insert 되는 숫자 말고 고유한 id를 매기고, 그 id에 대한 삭제 여부를 기록
                    minHeap.offer(new Node(idx, Integer.parseInt(opr[1]))); 
                    maxHeap.offer(new Node(idx, Integer.parseInt(opr[1])));
                    idx += 1;
                }

                else if (opr[0].equals("D")) {
                    if (opr[1].equals("1")) {
                        while (true) {
                            node = maxHeap.poll();
                            if (node == null) break;
                            if (!deleted.get(node.idx)) break; // 진짜 최댓값 찾기
                        }
                        if (node != null) {
                            deleted.set(node.idx, true); // 삭제 표시
                        }
                    }
                    else if (opr[1].equals("-1")) {
                        while (true) {
                            node = minHeap.poll();
                            if (node == null) break;
                            if (!deleted.get(node.idx)) break; // 진짜 최솟값 찾기
                        }
                        if (node != null) {
                            deleted.set(node.idx, true); // 삭제 표시
                        }
                    }
                }
            }

            long min = Long.MIN_VALUE; // 주어지는 숫자는 int 범위니깐
            long max = Long.MAX_VALUE;
            while (!minHeap.isEmpty()) {
                node = minHeap.poll();
                if (node != null && !deleted.get(node.idx)) {
                    min = node.num;
                    break;
                }
            }
            while (!maxHeap.isEmpty()) {
                node = maxHeap.poll();
                if (node != null && !deleted.get(node.idx)) {
                    max = node.num;
                    break;
                }
            }

            if (min == Long.MIN_VALUE && max == Long.MAX_VALUE) {
                bw.write("EMPTY\n");
            }
            else {
                bw.write(String.valueOf(max) + " " + String.valueOf(min) + "\n");
            }
        }
        bw.flush();
        bw.close(); 
        br.close();
    }
}
