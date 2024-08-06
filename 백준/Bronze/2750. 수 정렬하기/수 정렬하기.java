import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // BufferedReader for fast input reading
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // PrintWriter for fast output
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        // Reading the number of elements
        int n = Integer.parseInt(br.readLine());
        // Array to store the elements
        int[] array = new int[n];

        // Reading the elements into the array
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
        }

        // Sorting the array
        Arrays.sort(array);

        // Printing the sorted elements
        for (int element : array) {
            pw.println(element);
        }

        // Closing the PrintWriter
        pw.flush();
        pw.close();
        br.close();
    }
}
