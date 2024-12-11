// boj 5532 방학 숙제
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt();
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        int D = sc.nextInt();

        int koreanDays = caculate(A, C);
        int mathDays = caculate(B, D);
        System.out.println(L - Math.max(koreanDays, mathDays));
    }

    private static int caculate(int A, int C) {
        int share = A / C;
        int rest = A % C;
        if (rest == 0) {
            return share;
        }
        return share + 1;
    }
}
