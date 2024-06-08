// # 6021 DNA Sequencing
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // male
        int f = Integer.parseInt(st.nextToken()); // female
        
        String[] bulls = new String[m];
        String[] cows = new String[f];

        for (int i=0; i<m; i++){
            bulls[i] = br.readLine();
        }

        for (int i=0; i<f; i++){
            cows[i] = br.readLine();
        }

        int[][] result = new int[m][f];
        
        for (int i=0; i<m; i++){
            for (int j=0; j<f; j++){
                int count = 0;
                for (int k=0; k<m; k++){
                    if (k!=i && canBeChild(bulls[i], cows[j], bulls[k])){
                        count++;
                    }
                }
                for (int k=0; k<f; k++){
                    if (k!=j && canBeChild(bulls[i], cows[j], cows[k])){
                        count++;
                    }
                }
                result[i][j] = count;
            }
        }
        for(int i=0; i<m; i++){
            for(int j=0; j<f; j++){
                bw.write(result[i][j] + " ");
            } 
            bw.write("\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static boolean canBeChild(String bull, String cow, String child){
        for (int i=0; i<25; i++){
            if(child.charAt(i) != bull.charAt(i) && child.charAt(i) != cow.charAt(i))
                return false;
        }
        return true;
    }
}
