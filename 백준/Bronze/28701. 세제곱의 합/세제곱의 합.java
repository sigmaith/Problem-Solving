import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int total = 0;
		for(int i = 1; i <= n; i++) {
			total += i;
		}
		System.out.println(total);
		System.out.println(total * total);
		
		total = 0;
		for(int i = 1; i <= n; i++) {
			total += i * i * i;
		}
		System.out.println(total);
		sc.close();
	}
}