// boj 2133 타일 채우기
import java.io.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N(1 <= N <= 30)
        int N = Integer.parseInt(br.readLine());

        // N은 1부터 시작하기 때문에 N + 1의 크기를 갖는 배열을 선언
        int[] DP = new int[N + 1];

        // N이 홀수일 경우, 2x1 or 1x2의 타일로 채울 수 없기 때문에 0을 출력하고 return
        if (N % 2 != 0) {
            System.out.println(0);
            return;
        }

        // 타일이 없을 경우 2x1, or 1x2의 타일로 채울 수 있는 경우의 수는 아무것도 채우지 않는 경우.
        DP[0] = 1;
        
        // N은 홀수가 될 수 없고 짝수만 될 수 있기 때문에 2씩 증가
        for (int i = 2; i <= N; i += 2) {
            DP[i] = DP[i - 2] * 3;
            for (int j = i - 4; j >= 0; j -= 2) {
                DP[i] += DP[j] * 2;
            }
        }

        // 결과값 출력
        System.out.println(DP[N]);
    }
}