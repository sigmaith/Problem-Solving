// boj 1339 단어 수학
import java.io.*;
import java.util.*;

/*
 
2
AAA (999)
AAA (999)

1998

2
GCF (683) (784)
ACDEB (98754) (98653)

99437 
=> 알파벳 숫자 중복없이 세서 9부터 배정
=> 자릿수 큰 것부터 큰 수 배정 .. 자릿수 같을 시 중복의 횟수는?
// pq로 자릿수 먼저 고려, 같을 시 중복 횟수 고려하는 전략
// String.replace, Integer.valueOf?
// 최대 int 범위를 넘어가지 않음 987654320이므로..

10
A
B
C
D
E
F
G
H
I
J

45 -> 9~0까지의 합

2
AB (98) (89)
BA (89) (98)

187


 */

class Node {
    char c;
    int weight;
    public Node(char c, int weight) {
        this.c = c;
        this.weight = weight;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());

        String[] strArray = new String[N];
        HashMap<Character, Integer> map = new HashMap<>(); // 제네릭 타입 명시 !
        for (int i = 0; i < N; i++) { // 숫자 입력
            String str = String.valueOf(br.readLine());
            strArray[i] = str;
            int strLen = str.length();

            for (int j = 0; j < strLen; j++) {
                // 알파벳을 키로 하는 Hash
                char ch = str.charAt(j);
                int zeroNum = strLen - j - 1;
                if (map.containsKey(ch)) { // 존재하면 
                    int weight = map.get(ch);
                    weight += (int)Math.pow(10, zeroNum);
                    map.replace(ch, weight);
                } else { 
                    map.put(ch, (int)Math.pow(10, zeroNum));
                }
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
            (a, b) -> {
                return Integer.compare(b.weight, a.weight);
        });
        

        for (char c : map.keySet()) {
            pq.offer(new Node(c, map.get(c)));
        }
        HashMap<Character, Integer> assignment = new HashMap<>();
        int num = 9;
        while (!pq.isEmpty()) { // 으악 헷갈렸다
            Node node = pq.poll();
            assignment.put(node.c, num--);
        }

        int result = 0;
        for (String str: strArray) {
            int strLen = str.length();
            int value = 0;
            for (int i = 0; i < strLen; i++) {
                char ch = str.charAt(i);
                value = value * 10 + assignment.get(ch);
            }
            result += value;
        }

        System.out.println(result);
    }
}
