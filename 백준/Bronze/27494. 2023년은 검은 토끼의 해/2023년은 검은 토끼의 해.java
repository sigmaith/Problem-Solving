import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        // 2023에 해당하는 패턴
        String target = "2023";

        // 결과값 저장
        long result = 0;

        // 각 자릿수에 대해 검사
        for (long i = 2023; i <= N; i++) {
            if (isWinningTicket(String.valueOf(i), target)) {
                result++;
            }
        }

        System.out.println(result);
    }

    // 티켓이 당첨인지 검사하는 함수
    private static boolean isWinningTicket(String ticket, String target) {
        int targetIdx = 0; // target은 "2023"
        
        // 각 자릿수에 대해 순서대로 검사
        for (int i = 0; i < ticket.length(); i++) {
            if (ticket.charAt(i) == target.charAt(targetIdx)) {
                targetIdx++; // 다음 자릿수로 넘어감
            }
            if (targetIdx == 4) { // "2023"을 모두 찾았으면
                return true;
            }
        }
        return false; // "2023"을 찾지 못함
    }
}
