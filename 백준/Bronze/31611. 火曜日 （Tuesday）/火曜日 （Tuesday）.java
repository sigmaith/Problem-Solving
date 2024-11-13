import java.util.*;

public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int day = sc.nextInt();
		if (day % 7 == 2) {
		    System.out.println(1);
		    return;
		}
		System.out.println(0);
	}
}
