// boj 28235 코드마스터 2023
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        HashMap<String, String> hash = new HashMap<>();
        hash.put("SONGDO", "HIGHSCHOOL");
        hash.put("CODE", "MASTER");
        hash.put("2023", "0611");
        hash.put("ALGORITHM", "CONTEST");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(hash.get(str));
    }
}
