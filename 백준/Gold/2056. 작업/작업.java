// #2056번: 작업 
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int v = Integer.valueOf(br.readLine());
        int[] inDegree = new int[v+1]; // 진입차수 기록
        int[] outDegree = new int[v+1]; // 진출차수 기록
        int[] time = new int[v+1];
        int[] dp = new int[v+1]; // 각 id에 도달하기까지 걸리는 최소이자 최대시간 

        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < v + 1; i++) {
            adj.add(new ArrayList<>());
        }
 
        for (int i = 1; i <= v; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.valueOf(st.nextToken()); 
            dp[i] = time[i]; 
            int taskNum = Integer.valueOf(st.nextToken());

            for (int j = 0; j < taskNum; j++) { // 선행되어야할 task
                int taskId = Integer.valueOf(st.nextToken());
                inDegree[i] += 1; // 진출차수 증가
                outDegree[taskId] += 1; // 진입차수 증가
                adj.get(taskId).add(i);   
            }
        }
        
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= v; i++) {
            if (inDegree[i] == 0) { 
                // 작업들 중에는, 그것에 대해 선행 관계에 있는 작업이 하나도 없는 작업이 반드시 하나 이상 존재한다. 
                //(1번 작업이 항상 그러하다)
                q.offerLast(i); // 진입차수가 0인 모든 정점을 넣어야 해.(중요!)
            }
        }
        
        // System.out.println(Arrays.toString(time));
        // System.out.println(Arrays.toString(dp));

        while (!q.isEmpty()) {
            int id = q.pollFirst();
            // System.out.println("id = " + id);
            
            for (int next: adj.get(id)) {
                // System.out.println("de + time = " + (dp[id] + time[next]));
                dp[next] = Math.max(dp[next], dp[id] + time[next]); // 갱신됐을때만 하면안됨
                inDegree[next]--;
                if (inDegree[next] == 0) { 
                    q.offer(next);
                }
            }
        }
        // System.out.println(Arrays.toString(time));
        // System.out.println(Arrays.toString(dp));

        boolean isAllSeparated = true;
        for (int i = 1; i <= v; i++) {
            if (inDegree[i] != 0) {
                isAllSeparated = false;
                break;
            }
        }

        int result = 0;
        for (int i = 1; i <= v; i++) {
            result = Math.max(result, dp[i]); // 전체 dp 배열에서 최대값을 찾음
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
