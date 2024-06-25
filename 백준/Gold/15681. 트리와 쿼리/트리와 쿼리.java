// #15861 트리와 쿼리
import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static ArrayList<ArrayList<Integer>> connect;
    static ArrayList<ArrayList<Integer>> child;
    static ArrayList<ArrayList<Integer>> parent;
    static int[] size;
    public static void makeTree(int currentNode, int parentNode){
        for (int i=0; i<connect.get(currentNode).size(); i++){
            int Node = connect.get(currentNode).get(i);
            if (Node != parentNode){
                child.get(currentNode).add(Node);
                parent.get(Node).add(currentNode);
                makeTree(Node, currentNode);
            }
        }
    }

    public static void countSubtreeNodes(int currentNode){
        size[currentNode] = 1;
        for (int i=0; i<child.get(currentNode).size(); i++){
            int Node = child.get(currentNode).get(i);
            countSubtreeNodes(Node);
            size[currentNode] += size[Node];
        }
    }

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()), // 정점의 개수
            R = Integer.parseInt(st.nextToken()), // 루트의 번호
            Q = Integer.parseInt(st.nextToken()); // 쿼리의 개수
        
        child = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < N + 1; i++) {
            ArrayList<Integer> arr = new ArrayList<>();
            child.add(arr); // 초기화 - 1base index
        }

        parent = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < N + 1; i++) {
            ArrayList<Integer> arr = new ArrayList<>();
            parent.add(arr); // 초기화 - 1base index
        }

        connect = new ArrayList<ArrayList<Integer>>();
        for (int i=0; i<N + 1; i++){
            ArrayList<Integer> arr = new ArrayList<>();
            connect.add(arr); // 초기화 - ibase index
        }

        size = new int[N+1];

        for (int i=0; i<N - 1; i++){
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken()),
                nodeB = Integer.parseInt(st.nextToken());

            connect.get(nodeA).add(nodeB); // 입력 받고 adj리스트 추가
            connect.get(nodeB).add(nodeA); //  양방향 연결!!
        }

        int[] query = new int[Q];
        for(int i=0; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            query[i] = Integer.parseInt(st.nextToken());
        }

        makeTree(R, -1);
        countSubtreeNodes(R);

        for(int i=0; i<Q; i++){
            bw.write(size[query[i]] + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
