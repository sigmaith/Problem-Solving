// #2143 두 배열의 합
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
      br = new BufferedReader(new InputStreamReader(System.in));
      bw = new BufferedWriter(new OutputStreamWriter(System.out));

      long t = Long.parseLong(br.readLine());

      int aSize = Integer.parseInt(br.readLine());
      int[] a = new int[aSize];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < aSize; i++) {
        a[i] = Integer.parseInt(st.nextToken());
      }

      int bSize = Integer.parseInt(br.readLine());
      int[] b = new int[bSize];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < bSize; i++) {
          b[i] = Integer.parseInt(st.nextToken());
      }

      for (int i = 1; i < aSize; i++) {
          a[i] += a[i - 1];
      }

      for (int i = 1; i < bSize; i++) {
          b[i] += b[i - 1];
      }

      int ps_A = aSize * (aSize + 1) / 2;
      int[] partialSumA = new int[ps_A];
      int ps_B = bSize * (bSize + 1) / 2;
      int[] partialSumB = new int[ps_B];

      int idx = 0;
      for (int i = 0; i < aSize; i++) {
        for (int j = i; j < aSize; j++) {
            if (i > 0) {
                partialSumA[idx++] = a[j] - a[i-1];
            }
            else { 
                partialSumA[idx++] = a[j];
            }
        }
      }
      
      idx = 0;
      for (int i = 0; i < bSize; i++) {
          for (int j = i; j < bSize; j++) {
              if (i > 0) {
                  partialSumB[idx++] = b[j] - b[i - 1];
              } else {
                  partialSumB[idx++] = b[j];
              }
          }
      }

      long cnt = 0;
      Arrays.sort(partialSumA);
      Arrays.sort(partialSumB);
      int idxA = 0;
      int idxB = ps_B - 1;

    //   System.out.println(Arrays.toString(partialSumA));
    //   System.out.println(Arrays.toString(partialSumB));

      while (idxA < ps_A && idxB >= 0) {
        if (partialSumA[idxA] + partialSumB[idxB] == t) {
            int partA = partialSumA[idxA];
            int partB = partialSumB[idxB];
            long cntA = 0;
            long cntB = 0;
            while (idxA < ps_A && partialSumA[idxA] == partA) {
                idxA++;
                cntA++;
            }
            while (idxB >= 0 && partialSumB[idxB] == partB) {
                idxB--;
                cntB++;
            }
            cnt += cntA * cntB;
        }
        else if (partialSumA[idxA] + partialSumB[idxB] < t) {
            idxA++;
        }
        else if (partialSumA[idxA] + partialSumB[idxB] > t) {
            idxB--;
        }
      }

      bw.write(cnt + "\n");
      bw.flush();
      bw.close();
      br.close();
    }
}