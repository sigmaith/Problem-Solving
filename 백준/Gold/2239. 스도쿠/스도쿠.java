// boj 2239 스도쿠

import java.io.*;
import java.util.*;

public class Main {
    static int[][] sudoku;
    static boolean solved;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sudoku = new int[9][9];

        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = input.charAt(j) - '0'; // 입력은 1-base index
            }
        }
        solve(0, 0);
    }

    private static void solve(int x, int y) {
        if (x == 8 && y == 8) {
            if (sudoku[8][8] == 0) {
                for (int i = 0; i <= 7; i++) {
                    sudoku[8][8] -= sudoku[8][i];
                }
                sudoku[8][8] += 45;
            }

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(sudoku[i][j]);
                }
                System.out.println();
            }
            solved = true;
            return;
        } // 해를 구했음.

        if (solved) {
            return;
        }

        if (sudoku[x][y] != 0) {
            if (y == 8 && x < 8) {
                solve(x + 1, 0);
            } else if (y < 8) {
                solve(x, y + 1);
            }
            return; // 1% -> 100 % 이걸 안하면 원본 스도쿠 숫자가 손실됨.
        }
        boolean[] visited = new boolean[9];
        for (int i = 0; i < 9; i++) { // 가로
            if (sudoku[x][i] != 0) {
                int num = sudoku[x][i];
                visited[num - 1] = true;
            }
        }
        for (int i = 0; i < 9; i++) { // 세로
            if (sudoku[i][y] != 0) {
                int num = sudoku[i][y];
                visited[num - 1] = true;
            }
        }

        int share1 = (x / 3) * 3;
        int share2 = (y / 3) * 3;
        for (int i = share1; i <= share1 + 2; i++) {
            for (int j = share2; j <= share2 + 2; j++) {
                if (sudoku[i][j] != 0) {
                    int num = sudoku[i][j];
                    visited[num - 1] = true;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            if (!visited[i]) {
                sudoku[x][y] = i + 1;
                if (y == 8 && x < 8) {
                    solve(x + 1, 0);
                } else if (y < 8) {
                    solve(x, y + 1);
                }
                sudoku[x][y] = 0;
            }
        }
    }
}