import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t1 = sc.nextInt();
		int e1 = sc.nextInt();
		int f1 = sc.nextInt();
		
		int t2 = sc.nextInt();
		int e2 = sc.nextInt();
		int f2 = sc.nextInt();
		
		int total1 = t1 * 3 + e1 * 20 + f1 * 120;
		int total2 = t2 * 3 + e2 * 20 + f2 * 120;
		
		if(total1 > total2) {
			System.out.println("Max");
		}else if(total1 < total2) {
			System.out.println("Mel");
		}else{
			System.out.println("Draw");
		}
		sc.close();
	}
}