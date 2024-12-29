// boj 1062 가르침
import java.io.*;
import java.util.*;

public class Main {
    static int K;
    static boolean[] alphabetsAll;
    static int result = 0;
    static List<String> words;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 단어의 개수 N은 50보다 작거나 같은 자연수
        K = Integer.parseInt(st.nextToken()); // K는 26보다 작거나 같은 자연수 또는 0

        alphabetsAll = new boolean[26]; // 배운 알파벳
        words = new ArrayList<>();

        // 둘째 줄부터 N개의 줄에 남극 언어의 단어가 주어진다. 단어는 영어 소문자로만 이루어져 있고, 길이가 8보다 크거나 같고, 15보다 작거나 같다. 모든 단어는 중복되지 않는다.
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words.add(word);
        }

        // 기본적으로 antic 은 배워야 함.
        if (K < 5) {
            System.out.println(0);
        } else {
            String essential = "antic";
            for (int i = 0; i < essential.length(); i++) {
                alphabetsAll[essential.charAt(i) - 'a'] = true;
            }

            backtracking(0, 0);
            System.out.println(result);
        }
    }

    private static void backtracking(int idx, int cnt) {
        if (cnt == K - 5) {
            countReadableWords();
            return;
        }
        if (cnt >= K - 5 || idx == 26) {
            return;
        }
        if (alphabetsAll[idx]) {
            backtracking(idx + 1, cnt);
            return;
        }
        alphabetsAll[idx] = true;
        backtracking(idx + 1, cnt + 1);
        alphabetsAll[idx] = false;
        backtracking(idx + 1, cnt);
    }

    private static void countReadableWords() {
        int cnt = 0;
        for (String word : words) {
            boolean readable = true;
            for (int i = 0; i < word.length(); i++) {
                if (!alphabetsAll[word.charAt(i) - 'a']) {
                    readable = false;
                    break;
                }
            }
            if (readable) {
                cnt++;
            }
        }
        if (cnt > result) {
            result = cnt;
        }
    }
}

/*
1 8
antatica
answer: 1
 */
// 가르치는 알파벳이 꼭 단어에 출현하지 않는 알파벳일 수 있음.
