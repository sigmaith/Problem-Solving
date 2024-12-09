// boj 32929 UOS 문자열
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = "UOS";
        int x = sc.nextInt();
        System.out.println(str.charAt((x - 1) % 3));
    }
}
