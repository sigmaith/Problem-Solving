// boj 30676 이 별은 무슨 색일까
import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int lambda = sc.nextInt();
		
		if (lambda >= 620 && lambda <= 780) {
		    System.out.println("Red");
		} else if (lambda >= 590 && lambda < 620) {
		    System.out.println("Orange");
		} else if (lambda >= 570 && lambda < 590) {
		    System.out.println("Yellow");
		} else if (lambda >= 495 && lambda < 570) {
		    System.out.println("Green");
		} else if (lambda >= 450 && lambda < 495) {
		    System.out.println("Blue");
		} else if (lambda >= 425 && lambda < 450) {
		    System.out.println("Indigo");
		} else if (lambda >= 380 && lambda < 425) {
		    System.out.println("Violet");
		}
	}
}
