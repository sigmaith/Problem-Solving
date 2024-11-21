// boj 1759 암호 만들기
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken()); // 암호의 길이
        int C = Integer.parseInt(st.nextToken()); // 알파벳 개수

        st = new StringTokenizer(br.readLine());
        char[] alphabets = new char[C];
        for (int i = 0; i < C; i++) {
            alphabets[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(alphabets);
        backtrack(0, new ArrayList<>(), L, alphabets);
    }

    private static void backtrack(int idx, List<Character> current, int L, char[] alphabets) {
        if (current.size() == L) {
            int vowelCount = 0;
            int consonantCount = 0;
            for (int i = 0; i < L; i++) {
                Character c = current.get(i);
                if ("aeiou".contains(c.toString())) {
                    vowelCount++;
                } else {
                    consonantCount++;
                }
            }
            if (vowelCount < 1 || consonantCount < 2) {
                return;
            }
            for (Character c: current) {
                System.out.print(c);
            }
            System.out.println();
        }

        for (int i = idx; i < alphabets.length; i++) {
            current.add(alphabets[i]);
            backtrack(i + 1, current, L, alphabets);
            current.remove(current.size() - 1);
        }
    }
}
