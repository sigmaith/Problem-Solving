// boj 2023 신기한 소수

import java.io.*;
import java.util.*;

public class Main {
    static int number;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // N(1 ≤ N ≤ 8) -> 2 ~ 99,999,999

        number = 0;
        backtracking(0, N);
    }

    private static void backtracking(int idx, int end) {
        if (idx == end) {
            if (isPrime(number)) {
                System.out.println(number);
            }
            return;
        }
        if (idx != 0 && !isPrime(number)) {
            return;
        }

        for (int i = 1; i <= 9; i++) {
            number = number * 10 + i;
            backtracking(idx + 1, end);
            number /= 10;
        }
    }

    private static boolean isPrime(int num) { // 에라토스테네스의 체 대신 동적 소수 판별
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) { // 타입 프로모션(Type Promotion) 또는 암시적 캐스팅(Implicit Casting)
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

