// boj 28444

import java.util.*;
public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int h = sc.nextInt();
		int i = sc.nextInt();
		int a = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		
		System.out.println(h * i - a * r * c);
	}
}