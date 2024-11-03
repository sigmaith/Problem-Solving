import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		double s1 = sc.nextDouble();
		double s2 = sc.nextDouble();
		
		if(s1 >= s2 / 2) {
			System.out.println("E");
		} else {
			System.out.println("H");
		}
		sc.close();
	}
}