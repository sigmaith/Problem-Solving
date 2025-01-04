// boj 22252 정보 상인 호석

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Integer Q = Integer.parseInt(br.readLine()); // 1 ≤ Q ≤ 100,000,

        Map<String, PriorityQueue<Integer>> market = new HashMap<>();

        long get = 0;
        for (int q = 0; q < Q; q++) {
            String[] query = br.readLine().split(" ", -1);
            if (query[0].equals("1")) { // 고릴라가 정보를 얻었을 경우
                String name = query[1];
                int infoCnt = Integer.parseInt(query[2]);
                if (!market.containsKey(name)) { // 고릴라 없을 경우 추가
                    market.put(name, new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1)));
                }
                for (int i = 3; i < 3 + infoCnt; i++) { // 정보 개수만큼 정보 추가
                    market.get(name).add(Integer.parseInt(query[i]));
                }
            } else if (query[0].equals("2")) { // 호석이가 고릴라에게 정보를 사는 경우
                String name = query[1];
                int infoBuy = Integer.parseInt(query[2]);
                while (market.containsKey(name) && !market.get(name).isEmpty() && infoBuy > 0) {
                    get += market.get(name).poll();
                    infoBuy -= 1;
                }
            }
        }
        System.out.println(get);
    }
}

