// #2623 음악프로그램
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static List<List<Integer>> graph;
    static int[] in_degree;
    private static void topological_sort(int n)throws Exception{
        List<Integer> result = new ArrayList<>();
        Deque<Integer> q = new ArrayDeque<>(); // Deque를 큐처럼 쓰기
        for (int i=1; i<n+1; i++){
            if (in_degree[i] == 0) q.addLast(i); // 진입차수 0인것을 큐에 넣기
        }
        while (!q.isEmpty()){
            int now = q.pollFirst(); // q에서 뽑아서 
            result.add(now);
            for (int ele: graph.get(now)){ // 인접 행렬 탐색하며 
                in_degree[ele] -= 1; // 진입차수 갱신
                if (in_degree[ele] == 0){ 
                    q.addLast(ele); // 0이면 큐에 넣기
                }
            }
        }
        if(result.size() != n) {
            bw.write("0\n"); // 사이클이 존재할 시, 순서랄것이 없어 size 가 vertice개수보다 작아짐.
        }else{
            for (int element : result) {
                bw.write(element + "\n");
            }
        }
    }
    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // vertice
        int m = Integer.parseInt(st.nextToken()); // pd 명수 (엣지를 제시해줄 것임)
        int flag = 0;

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++){
            List<Integer> array = new ArrayList<>();
            graph.add(array);
        } // adjacent list(인접리스트) 생성

        in_degree = new int[n + 1]; // 진입차수: 자기 자신을 가리키는 엣지의 개수
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine()); 
            int num = Integer.parseInt(st.nextToken()); // vertice 개수
            int[] numList = new int[num]; // vertice 개수만큼 정적 배열 생성
            for (int j=0; j<num; j++){
                numList[j] = Integer.parseInt(st.nextToken()); // num개수만큼 잘라 넣기
            }
            for (int j=1; j<num; j++){
                graph.get(numList[j-1]).add(numList[j]); // graph 시작점 배열에 도착점 넣기
                in_degree[numList[j]] += 1; // 진입 차수 갱신
            }
        }
        if (flag == 0)topological_sort(n);
        else bw.write(0 + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}
