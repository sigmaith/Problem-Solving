// #2623 음악프로그램
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static List<List<Integer>> graph;
    static int[] in_degree;
    static int[] desc;
    private static void topological_sort(int n)throws Exception{
        List<Integer> result = new ArrayList<>();
        Deque<Integer> q = new ArrayDeque<>();
        for (int i=1; i<n+1; i++){
            if (in_degree[i] == 0) q.addLast(i);
        }
        while (!q.isEmpty()){
            int now = q.pollFirst();
            result.add(now);
            for (int ele: graph.get(now)){
                in_degree[ele] -= 1;
                if (in_degree[ele] == 0){
                    q.addLast(ele);
                }
            }
        }

        if(result.size() != n) {
            bw.write("0\n");
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
        int m = Integer.parseInt(st.nextToken()); // pd 개수
        int flag = 0;

        graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++){
            List<Integer> array = new ArrayList<>();
            graph.add(array);
        } // graph 동적 2차원 정수(Integer) 배열 초기화

        in_degree = new int[n + 1]; // 진입차수: 자기 자신을 가리키는 엣지의 개수
        desc = new int[n + 1]; // 조상기록 
        for(int i=1; i<n+1; i++){
            desc[i] = i;
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine()); 
            int num = Integer.parseInt(st.nextToken()); // vertice 개수
            int[] numList = new int[num]; // vertice 개수만큼 정적 배열 생성
            for (int j=0; j<num; j++){
                numList[j] = Integer.parseInt(st.nextToken()); // num개수만큼 잘라 넣기
            }
            for (int j=1; j<num; j++){
                graph.get(numList[j-1]).add(numList[j]); // graph 시작점 배열에 도착점 넣기
                in_degree[numList[j]] += 1;
            }
        }
        if (flag == 0)topological_sort(n);
        else bw.write(0 + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}
