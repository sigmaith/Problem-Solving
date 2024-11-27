// boj 11779 최소비용 구하기 2
import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        ArrayList<Integer> route;
        int cost;
        Node(final ArrayList<Integer> route, final int cost) {
            this.route = route;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            d[i] = Integer.MAX_VALUE;
        }

        ArrayList<ArrayList<int[]>> adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dest =  Integer.parseInt(st.nextToken());
            int cost =  Integer.parseInt(st.nextToken());
            adj.get(src).add(new int[]{dest, cost});
        }

        st = new StringTokenizer(br.readLine());
        int src = Integer.parseInt(st.nextToken());
        int dest =  Integer.parseInt(st.nextToken());
        d[src] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {return Integer.compare(o1.cost, o2.cost);});
        ArrayList<Integer> start = new ArrayList<>();
        start.add(src);
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            ArrayList<Integer> route = node.route;
            int nowCity = route.get(route.size() - 1);
            int nowCost = node.cost;
            if (nowCity == dest) {
                System.out.println(nowCost);
                System.out.println(route.size());
                for (int i = 0; i < route.size(); i++) {
                    System.out.print(route.get(i) + " ");
                }
                break;
            }
            if (d[nowCity] < nowCost) {
                continue;
            }
            for (int[] info: adj.get(nowCity)) {
                int nextCity = info[0];
                int cost = info[1];
                if (d[nextCity] > nowCost + cost) {
                    d[nextCity] = nowCost + cost;
                    ArrayList<Integer> routeUpdated = new ArrayList<>(route);
                    routeUpdated.add(nextCity);
                    pq.offer(new Node(routeUpdated, d[nextCity]));
                }
            }
        }

    }
}
