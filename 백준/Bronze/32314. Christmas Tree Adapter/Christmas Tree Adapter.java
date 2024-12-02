import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int a = Integer.parseInt(br.readLine());
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int w = Integer.parseInt(st.nextToken());
	    int v = Integer.parseInt(st.nextToken());
	    if (w/v >= a) {
	        System.out.println(1);
	    } else {
            System.out.println(0);
	    }
	}
}
