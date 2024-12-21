// boj 11444 피보나치 수 6
import java.io.*;
import java.util.*;

public class Main {
    final static long MOD = 1_000_000_007;
    public static long[][] origin = {{1, 1}, {1, 0}}; // 초기값을 갖고있는 행렬

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
         *                n
         *       | 1   1 |    | F(n+1)  F(n)  |
         * A^n = |       |  = |               |
         *       | 1   0 |    |  F(n)  F(n-1) |
         */

        long[][] A = {{1, 1}, {1, 0}};

        long N = Long.parseLong(br.readLine());

        // Fn 을 구하려면  A행렬을 n-1제곱 한 뒤 반환된 행렬 A11 원소를 출력하면 된다.
        System.out.println(pow(A, N)[1][0]);
    }

    // 행렬 제곱 분할 정복 메소드
    public static long[][] pow(long[][] A, long exp) {
        // 지수가 1이나 0이면 A 리턴
        if (exp == 1 || exp == 0) {
            return A;
        }

        // 지수를 절반으로 분할하여 재귀 호출
        long[][] ret = pow(A, exp/2);

        // 하위 재귀에서 얻은 행렬을 제곱해준다.
        ret = multiply(ret, ret);

        // 만약 지수가 홀수라면 마지막에 A^1(origin)을 곱해준다.
        if (exp % 2 == 1L) {
            ret = multiply(ret, origin);
        }

        return ret;
    }

    // 행렬 o1과 o2를 곱해주는 메서드
    public static long[][] multiply(long[][] o1, long[][] o2) {
        long[][] ret = new long[2][2];

        ret[0][0] = ((o1[0][0] * o2[0][0]) + (o1[0][1] * o2[1][0])) % MOD;
        ret[0][1] = ((o1[0][0] * o2[0][1]) + (o1[0][1] * o2[1][1])) % MOD;
        ret[1][0] = ((o1[1][0] * o2[0][0]) + (o1[1][1] * o2[1][0])) % MOD;
        ret[1][1] = ((o1[1][0] * o2[0][1]) + (o1[1][1] * o2[1][1])) % MOD;

        return  ret;
    }
}
