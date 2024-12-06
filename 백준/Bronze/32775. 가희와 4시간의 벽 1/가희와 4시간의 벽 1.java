// boj 32775 가희와 4시간의 벽 1
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int S = sc.nextInt();
        int F = sc.nextInt();
        if (S <= F) {
            System.out.println("high speed rail");
        } else {
            System.out.println("flight");
        }
    }
}

