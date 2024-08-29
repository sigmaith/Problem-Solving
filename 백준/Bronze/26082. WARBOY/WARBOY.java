import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int a = Integer.parseInt(st.nextToken());
	    int b = Integer.parseInt(st.nextToken());
	    int c = Integer.parseInt(st.nextToken());
	    
	    int result = 3 * b * c / a;
	    System.out.println(result);
	}
}
