// boj 10222 Tons Of Damage
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N   = Integer.parseInt(st.nextToken());
            double adt = Double.parseDouble(st.nextToken()); // 공격력 x2 확률(%)
            double adp = Double.parseDouble(st.nextToken()); // 공격력 +1 확률(%)
            double apt = Double.parseDouble(st.nextToken()); // 주문력 x2 확률(%)
            double app = Double.parseDouble(st.nextToken()); // 주문력 +1 확률(%)

            // 확률을 [0,1] 범위로
            double pA = adt / 100.0;  // 공격력 x2
            double pB = adp / 100.0;  // 공격력 +1
            double pC = apt / 100.0;  // 주문력 x2
            double pD = app / 100.0;  // 주문력 +1

            // 초기 기댓값들
            double eAD  = 0.0;  // E[공격력]
            double eAP  = 0.0;  // E[주문력]
            double eADAP= 0.0;  // E[공격력×주문력]
            
            double totalDamage = 0.0; // N번 사용 시 데미지 누적

            for (int i = 1; i <= N; i++) {
                // 1) 이번 턴 직후의 공격력 기대값
                double newEAD   = eAD * (1 + pA) + pB;

                // 2) 이번 턴 직후의 주문력 기대값
                double newEAP   = eAP * (1 + pC) + pD;

                // 3) 이번 턴 직후의 곱(AD×AP) 기대값
                double newEADAP = eADAP * (1 + pA + pC)
                                  + pB * eAP
                                  + pD * eAD;

                // 이번 턴 데미지는 newEADAP의 기대값
                totalDamage += newEADAP;

                // 다음 턴 준비
                eAD   = newEAD;
                eAP   = newEAP;
                eADAP = newEADAP;
            }

            sb.append(totalDamage).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}
