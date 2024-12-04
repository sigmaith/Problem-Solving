// boj 1644 소수의 연속합
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        boolean[] prime = new boolean[N + 1];
        deleteNonPrimeNumbers(prime, N);
        System.out.println(getContinuousPrimeNumSum(prime, N));
    }

    private static void deleteNonPrimeNumbers(boolean[] prime, int N) {
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;

        for (int i = 2; i < N; i++) {
            if (!prime[i]) {
                continue;
            }
            for (int j = i * 2; j <= N; j += i) {
                prime[j] = false;
            }
        }
    }

    private static int getContinuousPrimeNumSum(boolean[] prime, int N) {
        int result = 0, A = 2, B = 0;

        int sum = 0;
        while (true) {
            if ((B == N && sum == N) || B > N) {
            // 소수인 경우 / 소수가 아닌 경우
                break;
            }
            while (sum < N && B <= N) {
                B++;
                if (B <= N && prime[B]) {
                    sum += B;
                }
            } // sum 은 큰 상태
            while (sum > N && A <= N) {
                if (A <= N && prime[A]) {
                    sum -= A;
                }
                A++;
            }
            if (sum == N) {
                result += 1;
                // 균형깨뜨려주기
                while (B < N) {
                    B++;
                    if (B <= N && prime[B]) {
                        sum += B;
                        break;
                    }
                }
            }
        }
        return result;
    }
}

