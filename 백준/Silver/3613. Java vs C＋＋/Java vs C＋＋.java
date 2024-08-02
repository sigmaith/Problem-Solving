// #3613 Java vs C++
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();

        if (str.charAt(str.length() - 1) == '_') {
            bw.write("Error!");
            bw.flush();
            return;
        }
        if (str.charAt(0) == '_') {
            bw.write("Error!");
            bw.flush();
            return;
        }
        if (str.contains("__")) {
            bw.write("Error!");
            bw.flush();
            return;
        }
        if (Character.isUpperCase(str.charAt(0))) {
            bw.write("Error!");
            bw.flush();
            return;
        }
        int upperFlag = 0;
        int signFlag = 0;
        for(int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) upperFlag = 1;
            if (str.charAt(i) == '_') signFlag = 1;
        }
        if (upperFlag == 1 && signFlag == 1) {
            bw.write("Error!");
            bw.flush();
            return;
        }

        if (Character.isLowerCase(str.charAt(0)) && str.contains("_")) { // c++
            StringBuilder sb = new StringBuilder();
            sb.append(str.charAt(0));

            for(int i = 1; i < str.length(); i++) {
                if (str.charAt(i-1) == '_') {
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(Character.toUpperCase(str.charAt(i)));
                }
                else sb.append(str.charAt(i));
            }
            bw.write(sb.toString() + "\n");
        }
        else if (Character.isLowerCase(str.charAt(0)) && !str.contains("_")) {
            StringBuilder sb = new StringBuilder();
            sb.append(str.charAt(0));

            for (int i = 1; i < str.length(); i++) {
                if (Character.isUpperCase(str.charAt(i))) {
                    sb.append('_');
                    sb.append(Character.toLowerCase(str.charAt(i)));
                }
                else sb.append(str.charAt(i));
            }
            bw.write(sb.toString() + "\n");
        }
        else if (isAllCharacterLowerCase(str)) {
            bw.write(str + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean isAllCharacterLowerCase(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLowerCase(str.charAt(i))) return false;
        }
        return true;
    }
}
