// #12100 2048(Easy)
import java.io.*;
import java.util.*;
class Main {
    static BufferedReader br; 
    static BufferedWriter bw;
    static StringTokenizer st;
    static int size;
    static int result;
    static void dfs(int iter, int[][] beforeBoard) throws Exception{
        // cheking greater block than result
        for (int l = 0; l < size; l++) {
            for (int m = 0; m < size; m++) {
                if (beforeBoard[l][m] > result)
                    result = beforeBoard[l][m];
            }
        }

        if (iter == 6) return;

        for(int dir=0; dir<4; dir++){ // east, west, south, north
            // copy
            int[][] afterBoard = new int[size][size];
            for(int l=0; l<size; l++){
                for(int m=0; m<size; m++){
                    afterBoard[l][m] = beforeBoard[l][m];
                }
            }
            // change of blocks
            if (dir == 0){ // 동쪽
                for(int l=0; l<size; l++){
                    int[] blocks = new int[size];
                    int idx = 0;
                    int prev = afterBoard[l][size-1];
                
                    for(int m=size-2; m>=0; ){
                        if(afterBoard[l][m] == 0) { m--; } // 현재가 0인거 거름
                        else if(prev == 0){prev = afterBoard[l][m]; m--;} // 이전이 0이고 현재가 0아닐때 갱신
                        else if(afterBoard[l][m] == prev){ 
                            blocks[idx++] = prev * 2; // blocks에 합쳐진 블록 추가
                            if(m>0) prev = afterBoard[l][m-1]; // prev갱신
                            else prev = -1;
                            m-=2; // 인덱스는 2칸 앞으로 갱신 (한번만 연산 가능)
                        }
                        else if(afterBoard[l][m] != prev){
                            blocks[idx++] = prev; // blocks에 prev블록 추가
                            prev = afterBoard[l][m]; // prev 갱신
                            m--; // 인덱스 1칸 앞으로 갱신
                        }
                        if (m < 0 && prev != -1) blocks[idx++] = prev; // 모든 상황에서 m이 벗어나면 prev 저장
                    }
                    for(int m=size-1; m>=0; m--){
                        afterBoard[l][m] = blocks[size-m-1];
                    }// changed blocks
                }
            }
            if (dir == 1){ // 서쪽
                for (int l = 0; l < size; l++) {
                    int[] blocks = new int[size];
                    int idx = 0;
                    int prev = afterBoard[l][0];
                    
                    for (int m = 1; m < size; ) {
                        if(afterBoard[l][m] == 0){ m++; }
                        else if(prev == 0){prev = afterBoard[l][m]; m++;}
                        else if(afterBoard[l][m] == prev){
                            blocks[idx++] = prev * 2;
                            if(m<size-1) prev = afterBoard[l][m+1];
                            else prev = -1;
                            m += 2;
                        }
                        else if(afterBoard[l][m] != prev){
                            blocks[idx++] = prev;
                            prev = afterBoard[l][m];
                            m++;
                        }
                        if(m>size-1 && prev!= -1) blocks[idx++] = prev;
                    }
                    for (int m = 0; m < size; m++) {
                        afterBoard[l][m] = blocks[m];
                    } // changed blocks
                }
            }
            if (dir == 2){ // south
                for (int l = 0; l < size; l++) {
                    int[] blocks = new int[size];
                    int idx = 0;
                    int prev = afterBoard[size-1][l];

                    for (int m=size-2; m>=0; ) {
                        if(afterBoard[m][l] == 0) { m--; }
                        else if(prev == 0){prev = afterBoard[m][l]; m--;}
                        else if(afterBoard[m][l] == prev){
                            blocks[idx++] = prev * 2;
                            if(m>0) prev = afterBoard[m-1][l];
                            else prev = -1;
                            m -= 2;
                        }
                        else if(afterBoard[m][l] != prev){
                            blocks[idx++] = prev;
                            prev = afterBoard[m][l];
                            m--;
                        }
                        if (m < 0 && prev != -1) blocks[idx++] = prev; 
                    }
                    for (int m = size - 1; m >= 0; m--) {
                        afterBoard[m][l] = blocks[size-m-1];
                    } // changed blocks
                }
            }
            if (dir == 3){ // north
                for (int l = 0; l < size; l++) {
                    int[] blocks = new int[size];
                    int idx = 0;
                    int prev = afterBoard[0][l];

                    for (int m = 1; m < size; ) {
                       if(afterBoard[m][l] == 0) m++;
                       else if(prev == 0){prev = afterBoard[m][l]; m++;}
                       else if(afterBoard[m][l] == prev){
                            blocks[idx++] = prev * 2;
                            if(m<size-1) prev = afterBoard[m+1][l]; 
                            else prev = -1;
                            m += 2;
                       }
                       else if(afterBoard[m][l] != prev){
                            blocks[idx++] = prev;
                            prev = afterBoard[m][l];
                            m++;
                       }
                       if(m>size-1 && prev!=-1) blocks[idx++] = prev;
                    }
                    for (int m = 0; m < size; m++) {
                        afterBoard[m][l] = blocks[m];
                    } // changed blocks
                }
            }


            // bw.write("dir=" + dir + "=========== afterboard ========== \n");
            // for (int i = 0; i < size; i++) {
            //     for (int j = 0; j < size; j++) {
            //         bw.write(afterBoard[i][j] + " ");
            //     }
            //     bw.write("\n");
            // }
            // bw.write("================================== \n");
            // bw.flush();

            //dfs start
            dfs(iter+1, afterBoard);
        }
    }

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());// int size... ㅠㅠ 바보!!!!
        result = 0;

        int[][] board = new int[size][size];
        for(int i=0; i<size; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<size; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }//board 입력

        dfs(1, board);
        bw.write(result + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}
