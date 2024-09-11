// boj 14465 소가 길을 건너간 이유 5
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] trafficLights = new int[n];
        for (int i = 0; i < b; i++) {
            int idx = Integer.parseInt(br.readLine());
            trafficLights[idx-1] = 1;
        }


        int result = 0;
        for(int i = 0; i < k; i++) {
            if (trafficLights[i] == 1) {
                result += 1;
            }
        }

        int fixPoint = result;
        for (int i = k; i < n; i++) {
            if(trafficLights[i] == 1) {
                fixPoint += 1;
            }
            if(trafficLights[i-k] == 1) {
                fixPoint -= 1;
            }
            result = Math.min(result, fixPoint);
        }

        System.out.println(result);
    }
}
