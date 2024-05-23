import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 위상정렬 결과를 출력할 객체
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 위상정렬에 사용할 진입차수 저장 배열
        int[] edgeCount = new int[n + 1];

        // 위상정렬에 사용할 그래프 2차원 리스트로 구현
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i<n+1; i++){
            graph.add(new ArrayList<>());
        }

        // dp 배열 : 해당 과목을 이수할 때 걸리는 학기 기록
        int[] dp = new int[n+1];
        for (int i=1; i<n+1 ; i++){
            dp[i] = 1; // 초기화
        }

        for(int i=0; i<m; i++){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            graph.get(x).add(y);
            edgeCount[y] += 1;
        }

        // 위상정렬에 사용할 큐
        Queue<Integer> q = new LinkedList<>();
        
        // 진입차수가 0인 노드 큐에 넣기
        for (int i=1; i<n+1; i++){
            if(edgeCount[i] == 0){
                q.offer(i);
            }
        }

        while(!q.isEmpty()){
            int nodeNo = q.poll();

            // bw.write(String.valueOf(nodeNo) + " ");

            List<Integer> list = graph.get(nodeNo);
            for (int i=0; i<list.size(); i++){
                edgeCount[list.get(i)] -- ;
                if (edgeCount[list.get(i)] == 0){
                    q.offer(list.get(i));
                    dp[list.get(i)] = dp[nodeNo] + 1;
                }
            }
        }

        for(int i=1; i<n+1; i++){
            System.out.print(dp[i] + " ");
        }
    }
}
