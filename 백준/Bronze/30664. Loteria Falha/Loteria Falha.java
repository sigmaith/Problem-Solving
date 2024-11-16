import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger divisor = BigInteger.valueOf(42); // 나눗셈 기준 값
        while (true) {
            String input = br.readLine();
            BigInteger a = new BigInteger(input); // BigInteger로 입력 처리
            if (a.equals(BigInteger.ZERO)) {
                break;
            } else {
                if (a.mod(divisor).equals(BigInteger.ZERO)) {
                    System.out.println("PREMIADO");
                } else {
                    System.out.println("TENTE NOVAMENTE");
                }
            }
        }
    }
}
