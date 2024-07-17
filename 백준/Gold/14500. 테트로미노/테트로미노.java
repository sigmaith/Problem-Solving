// #14500 테트로미노
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] p;
    static int result;
    static int sum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        p = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                p[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = -1;
        figure_1();
        figure_2();
        figure_3();
        figure_4();
        figure_5();

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void figure_1() {
        for(int i = 0; i < n; i++) {
            for (int j = 0; j <= m-4; j++) {
                sum = p[i][j] + p[i][j+1] + p[i][j+2] + p[i][j+3];
                result = Math.max(result, sum);
            }
        }
        for(int j = 0; j < m; j++) {
            for (int i = 0; i <= n-4; i++) {
                sum = p[i][j] + p[i+1][j] + p[i+2][j] + p[i+3][j];
                result = Math.max(result, sum);
            }
        }
        // System.out.println("fiture1" + result);
    }

    static void figure_2() {
        for (int i = 0; i <= n-2; i++) {
            for (int j = 0; j <= m-2; j++) {
                sum = p[i][j] + p[i][j+1] + p[i+1][j] + p[i+1][j+1];
                result = Math.max(result, sum);
            }
        }
        //System.out.println("fiture2" + result);
    }

    static void figure_3() {
        for (int i = 0; i <= n-2; i++) {
            for (int j = 0; j <= m-3; j++) {
                int sum1 = p[i][j] + p[i][j+1] + p[i][j+2] + p[i+1][j];
                int sum2 = p[i][j] + p[i][j+1] + p[i][j+2] + p[i+1][j+2];
                int sum3 = p[i][j] + p[i+1][j] + p[i+1][j+1] + p[i+1][j+2];
                int sum4 = p[i+1][j] + p[i+1][j+1] + p[i+1][j+2] + p[i][j+2];
                result = Math.max(result, sum1);
                result = Math.max(result, sum2);
                result = Math.max(result, sum3);
                result = Math.max(result, sum4);
            }
        }

        for (int j = 0; j <= m-2; j++) {
            for (int i = 0; i <= n-3; i++) {
                int sum1 = p[i][j] + p[i+1][j] + p[i+2][j] + p[i][j+1];
                int sum2 = p[i][j] + p[i+1][j] + p[i+2][j] + p[i+2][j+1];
                int sum3 = p[i][j] + p[i][j+1] + p[i+1][j+1] + p[i+2][j+1];
                int sum4 = p[i][j+1] + p[i+1][j+1] + p[i+2][j+1] + p[i+2][j];
                result = Math.max(result, sum1);
                result = Math.max(result, sum2);
                result = Math.max(result, sum3);
                result = Math.max(result, sum4);
            }
        }
        //System.out.println("fiture3" + result);
    }

    static void figure_4() {
        for (int i = 0; i <= n-2; i++) {
            for (int j = 0; j <= m-3; j++) {
                int sum1 = p[i+1][j] + p[i+1][j+1] + p[i][j+1] + p[i][j+2];
                int sum2 = p[i][j] + p[i][j+1] + p[i+1][j+1] + p[i+1][j+2];
                result = Math.max(result, sum1);
                result = Math.max(result, sum2);
            }
        }

        for (int j = 0; j <= m-2; j++) {
            for (int i = 0; i <= n-3; i++) {
                int sum1 = p[i][j] + p[i+1][j] + p[i+1][j+1] + p[i+2][j+1];
                int sum2 = p[i][j+1] + p[i+1][j+1] + p[i+1][j] + p[i+2][j];
                result = Math.max(result, sum1);
                result = Math.max(result, sum2);
            }
        }
        //System.out.println("fiture4" + result);
    }

    static void figure_5() {
        for (int i = 0; i <= n - 2; i++) {
            for (int j = 0; j <= m - 3; j++) {
                int sum1 = p[i][j] + p[i][j + 1] + p[i][j + 2] + p[i+1][j + 1];
                int sum2 = p[i+1][j] + p[i+1][j + 1] + p[i + 1][j + 2] + p[i][j + 1];
                result = Math.max(result, sum1);
                result = Math.max(result, sum2);
            }
        }

        for (int j = 0; j <= m - 2; j++) {
            for (int i = 0; i <= n - 3; i++) {
                int sum1 = p[i][j] + p[i + 1][j] + p[i + 2][j] + p[i + 1][j + 1];
                int sum2 = p[i][j + 1] + p[i + 1][j + 1] + p[i + 2][j+1] + p[i + 1][j];
                result = Math.max(result, sum1);
                result = Math.max(result, sum2);
            }
        }
        //System.out.println("fiture5" + result);
    }
}
