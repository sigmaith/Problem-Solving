import java.util.*;
public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int total = 0;
		
		for(int i = 0; i < n; i++) {
			int a = sc.nextInt();
			if(a == -1) {
				total = total - 1;
			}else if(a == 1) {
				total = total + 1;
			}
		}
		
		if(total > 0) {
			System.out.println("Right");
		}else if(total < 0) {
			System.out.println("Left");
		}else if(total == 0) {
			System.out.println("Stay");
		}
		sc.close();
	}
}