// #1647 도시 분할 계획
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int[] desc;
    private static int findUnion(int idx){
        if (desc[idx] != idx) { 
            desc[idx] = findUnion(desc[idx]);
        }
        return desc[idx];
    }
    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        int houseNum = Integer.parseInt(st.nextToken()); // 2~100_000
        int wayNum = Integer.parseInt(st.nextToken()); // 1~1_000_000
        desc = new int[houseNum + 1];
        for (int i = 1; i <= houseNum; i++) {
            desc[i] = i;
        }

        int[][] cost = new int[wayNum][3];
        for (int i = 0; i < wayNum; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cost, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });
        //System.out.println(Arrays.deepToString(cost));//

        int cnt = 0;
        int idx = 0;
        int result = 0;
        while (cnt < houseNum - 2 && idx < wayNum) {
            int parentA = findUnion(cost[idx][0]);
            int parentB = findUnion(cost[idx][1]);
            if (parentA != parentB) {
                desc[parentA] = parentB;
                cnt++;
                result += cost[idx][2];
            }
            idx++;
        }
        //System.out.println(Arrays.toString(desc));//

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
