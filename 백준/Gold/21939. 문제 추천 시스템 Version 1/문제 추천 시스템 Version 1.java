// boj 21939 문제 추천 시스템 Version 1

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        TreeMap<Integer, TreeSet<Integer>> problemNumbersByLevel = new TreeMap<>(); // 난이도별 문제 TreeSet
        Map<Integer, Integer> problemToLevel = new HashMap<>(); // 문제 번호 -> 난이도 매핑

        // 1 ≤ N, P ≤ 100,000
        int N = Integer.parseInt(br.readLine()); // 문제의 개수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int problemNumber = Integer.parseInt(st.nextToken()); // 문제 번호
            int level = Integer.parseInt(st.nextToken()); // 난이도

            problemNumbersByLevel.computeIfAbsent(level, k -> new TreeSet<>()).add(problemNumber);
            problemToLevel.put(problemNumber, level);
        }

        int M = Integer.parseInt(br.readLine()); // 명령문의 개수
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("recommend")) { // 문제 추천
                int num = Integer.parseInt(st.nextToken()); // 1 or -1

                if (num == 1) { // 가장 어려운 문제
                    int hardestLevel = problemNumbersByLevel.lastKey();
                    bw.write(problemNumbersByLevel.get(hardestLevel).last() + "\n"); // 가장 큰 문제 번호
                } else if (num == -1) { // 가장 쉬운 문제
                    int easiestLevel = problemNumbersByLevel.firstKey();
                    bw.write(problemNumbersByLevel.get(easiestLevel).first() + "\n"); // 가장 작은 문제 번호
                }

            } else if (cmd.equals("add")) { // 문제 추가
                int problemNumber = Integer.parseInt(st.nextToken()); // 문제 번호
                int level = Integer.parseInt(st.nextToken()); // 난이도

                problemNumbersByLevel.computeIfAbsent(level, k -> new TreeSet<>()).add(problemNumber);
                problemToLevel.put(problemNumber, level);

            } else if (cmd.equals("solved")) { // 문제 해결
                int problemNumber = Integer.parseInt(st.nextToken()); // 문제 번호
                int level = problemToLevel.get(problemNumber); // 문제의 난이도 찾기
                problemNumbersByLevel.get(level).remove(problemNumber); // 문제 제거

                if (problemNumbersByLevel.get(level).isEmpty()) {
                    problemNumbersByLevel.remove(level); // 해당 난이도에서 문제가 없어지면 제거
                }
                problemToLevel.remove(problemNumber); // 문제 번호 매핑 제거
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

