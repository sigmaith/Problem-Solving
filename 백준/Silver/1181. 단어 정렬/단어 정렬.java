// boj 1181 단어 정렬

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> words = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (!words.contains(word)) {
                words.add(word);
            }
        }

        words.sort((o1, o2) -> {
            if (o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }
            return o1.compareTo(o2);
        });
        for (int i = 0; i < words.size(); i++) {
            System.out.println(words.get(i));
        }
    }
}

