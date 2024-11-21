// boj 1759 암호 만들기
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static List<String> vowels;
    static List<String> consonants;
    static Set<String> resultSet = new HashSet<>(); // 중복 방지
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken()); // 암호의 길이
        int C = Integer.parseInt(st.nextToken()); // 알파벳 개수

        vowels = new ArrayList<>();
        consonants = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            String alphabet = st.nextToken();
            if ("aeiou".contains(alphabet)) {
                vowels.add(alphabet);
            } else {
                consonants.add(alphabet);
            }
        }

        // 모음 + 자음의 개수는 C
        for (int i = 0; i < vowels.size(); i++) {
            String vowelOne = vowels.get(i);
            for (int j = 0; j < consonants.size(); j++) {
                for (int k = j+1; k < consonants.size(); k++) { // 자음 2개 조합
                    String consonantOne = consonants.get(j);
                    String consonantTwo = consonants.get(k);
                    List<String> rest = getRests(vowelOne, consonantOne, consonantTwo);
                    generateCombinations(rest, L-3, new ArrayList<>(), 0, vowelOne, consonantOne, consonantTwo);
                }
            }
        }

        List<String> results = resultSet.stream().sorted().collect(Collectors.toList());
        for (String password: results) {
            System.out.println(password);
        }
    }

    private static List<String> getRests(String vowelOne, String consonantOne, String consonantTwo) {
        List<String> rest = new ArrayList<>();
        vowels.stream().filter(vowel -> !vowel.equals(vowelOne)).forEach(vo -> rest.add(vo));
        consonants.stream().filter(co -> !co.equals(consonantOne) && !co.equals(consonantTwo))
                .forEach(co -> rest.add(co));
        return rest;
    }

    private static void generateCombinations(List<String> rest, int r, List<String> current, int start, String vowelOne, String consonantOne, String consonantTwo) {
        if (current.size() == r) {
            // 완성된 암호 생성
            List<String> password = new ArrayList<>(current); // current 를 포함한 암호배열 생성
            password.add(vowelOne);
            password.add(consonantOne);
            password.add(consonantTwo);
            Collections.sort(password); // 정렬된 형태로 저장
            resultSet.add(String.join("", password));
            return;
        }

        for (int i = start; i < rest.size(); i++) {
            current.add(rest.get(i));
            generateCombinations(rest, r, current, i + 1, vowelOne, consonantOne, consonantTwo);
            current.remove(current.size() - 1); // 백트래킹
        }
    }
}
