import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static List<Integer> col;
    static List<Integer> pos;
    static List<Integer> neg;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());  // 체스판의 크기 입력

        col = new ArrayList<>();
        pos = new ArrayList<>();
        neg = new ArrayList<>();

        System.out.println(nqueens(0));  // 결과 출력
    }

    static int nqueens(int row) {
        if (row == n) {
            return 1;  // 모든 퀸이 성공적으로 배치된 경우
        }

        int result = 0;
        for (int c = 0; c < n; c++) {
            if (col.contains(c) || pos.contains(row + c) || neg.contains(row - c)) {
                continue;  // 현재 열이나 대각선에 이미 퀸이 있으면 건너뛰기
            } else {
                col.add(c);
                pos.add(row + c);
                neg.add(row - c);
                result += nqueens(row + 1);  // 다음 행에 퀸 배치 시도
                col.remove(col.size() - 1);  // 백트래킹
                pos.remove(pos.size() - 1);  // 백트래킹
                neg.remove(neg.size() - 1);  // 백트래킹
            }
        }
        return result;
    }
}
