import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int p = sc.nextInt();
        int result = l * p;
        for (int i = 0; i < 5; i++) {
            int n = sc.nextInt();
            System.out.print(n - result + " ");
        }
        sc.close();
    }
}