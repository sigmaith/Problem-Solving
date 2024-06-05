//  #1806 부분합, 1초 제한 = 1억번 연산
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 입력 파싱 (n, s)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // n의 최대가 10만 -> O(n) 전략
        int s = Integer.parseInt(st.nextToken());

        // 배열 초기화
        int[] arr = new int[n];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st2.nextToken());
        }

        // 이중 포인터 변수 초기화
        int start = 0, end = 0, sum = 0, minLength = Integer.MAX_VALUE;

        while(true){
            if(sum >= s){ // 아무것도 선택 안할수는 없게
                minLength = Math.min(minLength, end - start);
                sum -= arr[start++];
            } else if(end == n) break; // 끝에 도달했어도 if로 먼저 홀쭉하게 만든다.
            else { // 끝에 도달하면 추가할게 없으니까 위 조건이 먼저
                sum += arr[end++];
            }
        }

        if (minLength == Integer.MAX_VALUE) bw.write("0\n");
        else bw.write(minLength + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}
