//  #1264 모음의 개수
import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        while (true){
            String str = br.readLine();
            if (str.equals("#"))
                break; 
            
            else {
                int count = 0;
                for (int i=0; i<str.length(); i++){
                    char c = str.charAt(i);
                    if (c == 'a'|| c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'){
                        count++;
                    }
                }
                bw.write(count + "\n");
                bw.flush();
            }
        }

        br.close();
        bw.close();
    }
}
