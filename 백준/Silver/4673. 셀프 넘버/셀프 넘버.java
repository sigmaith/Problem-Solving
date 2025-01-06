// boj 4673 셀프 넘버

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        boolean[] selfNumbers = new boolean[10001];

        for (int i = 1; i <= 10000; i++) {
            int num = i;
            while (dFunction(num) <= 10000) {
                int dNum = dFunction(num);
                selfNumbers[dNum] = true;
                num = dNum;
            }
        }

        for (int i = 1; i <= 10000; i++) {
            if (!selfNumbers[i]) {
                System.out.println(i);
            }
        }
    }

    private static int dFunction(int num) {
        int result = num;
        while (num != 0) {
            result += num % 10;
            num /= 10;
        }
        return result;
    }
}

