import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(bf.readLine());
		
		if(check(num)) { 
			if(num % 7 != 0) { 
				bw.write(2 + "\n");
			} else { 
				bw.write(3 + "\n");
			}
		} else { 
			if(num % 7 != 0) {
				bw.write(0 + "\n");
			} else {
				bw.write(1 + "\n");
			}
		}
		bw.flush();
		bw.close();
	}

	public static boolean check(int num) {
		return Integer.toString(num).contains("7");
	} 

}