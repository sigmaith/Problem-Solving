import java.util.Scanner;

public class Main {
    public static int hour(int X) {
        return X * 24;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int X = scanner.nextInt();
        System.out.println(hour(X));
        scanner.close();
    }
}
