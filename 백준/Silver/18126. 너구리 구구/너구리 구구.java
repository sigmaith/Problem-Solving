import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int dest;
        long weight;

        public Node(int dest, long weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        List<List<Node>> adj= new ArrayList<>(N);
        for (int i = 0; i < N + 1; i++) { // 1 base index
            adj.add(new ArrayList<Node>());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long weight = Integer.parseInt(st.nextToken());
            adj.get(a).add(new Node(b, weight));
            adj.get(b).add(new Node(a, weight));
        }

        boolean[] visited = new boolean[N+1];
        visited[1] = true;
        Deque<Node> q = new ArrayDeque<>();
        for (Node node : adj.get(1)) {
            q.offerLast(new Node(node.dest, node.weight));
        }

        long maxResult = 0;
        while (!q.isEmpty()) {
            Node node = q.pollFirst();
            int nowId = node.dest;
            long nowWeight = node.weight;
            if (maxResult < nowWeight) {
                maxResult = nowWeight;
            }
            for (Node nextNode: adj.get(nowId)) {
                int nextId = nextNode.dest;
                long addedWeight = nextNode.weight;
                if (!visited[nextId]) {
                    visited[nextId] = true;
                    q.offerLast(new Node(nextId, nowWeight + addedWeight));
                }
            }
        }

        System.out.println(maxResult);
    }
}