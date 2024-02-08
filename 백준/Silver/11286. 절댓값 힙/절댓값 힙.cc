#pragma warning(disable:4996)
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//min algorithm

void setarrminusone(int inputnum); //initialize heap

int Heap1[210002]; //minus integer
int Heap2[210002]; //plus integer

int main(){
    int inputnum; scanf("%d", &inputnum);
    setarrminusone(inputnum);

    int last1 = 0; int last2 = 0;
    for(int i=0;i<inputnum;i++){
        int x; scanf("%d", &x); //input

        if(x == 0 && last1 == 0 && last2 == 0) printf("0\n");/////////////////////////////////empty
        
        if(x==0){
            if(((last1!= 0 && last2 == 0) || (last1!=0 && last2!=0  && Heap1[1] <= Heap2[1]) )) {//when heap1 has element ////////////////////////////////////
                printf("%d\n", -Heap1[1]);
                Heap1[1] = Heap1[last1--];
                Heap1[last1+1] = 2147483647;

                int idx=1;
                while(1){
                    if(Heap1[idx*2]==2147483647 && Heap1[idx*2+1]==2147483647) break;
                    else{
                    if(Heap1[idx*2] != 2147483647 && Heap1[idx*2] < Heap1[idx*2+1] && Heap1[idx*2] < Heap1[idx]) { 
                        //40 30 -1 -1- -1 처럼 아래 노드가 더 작을 수도 있어서 뒤의 조건을 붙여줘야 함
                        int tmp = Heap1[idx*2]; 
                        Heap1[idx*2]=Heap1[idx]; 
                        Heap1[idx]=tmp;
                        idx = idx*2;
                        }
                        else if(Heap1[idx*2] == Heap1[idx*2+1] && Heap1[idx*2] < Heap1[idx]) {
                            int tmp = Heap1[idx*2+1]; 
                            Heap1[idx*2+1]=Heap1[idx]; 
                            Heap1[idx]=tmp;
                            idx = idx*2 + 1;
                        }
                    else if(Heap1[idx*2+1] != 2147483647 &&Heap1[idx*2] > Heap1[idx*2+1] && Heap1[idx*2+1] < Heap1[idx] ) {
                        int tmp = Heap1[idx*2+1]; 
                        Heap1[idx*2+1]=Heap1[idx]; 
                        Heap1[idx]=tmp;
                        idx = idx*2 + 1;
                    }
                    else break;//다 클 경우
                }
                }
                if(last1==2&&Heap1[1]>Heap1[2]){
                    int tmp = Heap1[1];
                    Heap1[1]=Heap1[2];
                    Heap1[2]=tmp;
                }
            }
            else if(((last1==0 && last2!=0) || (last1!=0 && last2!=0  && Heap1[1]>Heap2[1]))) {//when heap2 has element ///////////////////////////////////////
                printf("%d\n", Heap2[1]);
                Heap2[1] = Heap2[last2--];
                Heap2[last2+1] = 2147483647;

                int idx=1;
                while(1){
                    if(Heap2[idx*2]==2147483647 && Heap2[idx*2+1]==2147483647) break;
                    else{
                        if(Heap2[idx*2] != 2147483647 && Heap2[idx*2] < Heap2[idx*2+1] && Heap2[idx*2] < Heap2[idx]) { 
                            //40 30 -1 -1- -1 처럼 아래 노드가 더 작을 수도 있어서 뒤의 조건을 붙여줘야 함
                            int tmp = Heap2[idx*2]; 
                            Heap2[idx*2]=Heap2[idx]; 
                            Heap2[idx]=tmp;
                            idx = idx*2;
                        }
                        else if(Heap2[idx*2] == Heap2[idx*2+1] && Heap2[idx*2] < Heap2[idx]) {
                            int tmp = Heap2[idx*2+1]; 
                            Heap2[idx*2+1]=Heap2[idx]; 
                            Heap2[idx]=tmp;
                            idx = idx*2 + 1;
                        }
                        else if(Heap2[idx*2+1] != 2147483647 &&Heap2[idx*2] > Heap2[idx*2+1] && Heap2[idx*2+1] < Heap2[idx] ) {
                            int tmp = Heap2[idx*2+1]; 
                            Heap2[idx*2+1]=Heap2[idx]; 
                            Heap2[idx]=tmp;
                            idx = idx*2 + 1;
                        }
                        else break;//다 클 경우
                    }
                }
                if(last2==2&&Heap2[1]>Heap2[2]){
                    int tmp = Heap2[1];
                    Heap2[1]=Heap2[2];
                    Heap2[2]=tmp;
                }
        
            }
        }

        else if(x != 0){
            if (x<0){
                Heap1[++last1] = -x; //원소 삽입 (1부터)
                //printf("heap1\n");

                int idx = last1;//인덱스
                while(idx != 1){
                    if(idx/2>0 && Heap1[idx/2]>Heap1[idx]){
                        int tmp = Heap1[idx/2]; Heap1[idx/2] = Heap1[idx]; Heap1[idx] = tmp;
                    }
                    else break;
                    idx /= 2;
                 }
            }
            else if (x>0){
                Heap2[++last2] = x; //원소 삽입 (1부터)
                //printf("heap2\n");

                int idx = last2;//인덱스
                while(idx != 1){
                    if(idx/2>0 && Heap2[idx/2]>Heap2[idx]){
                    int tmp = Heap2[idx/2]; Heap2[idx/2] = Heap2[idx]; Heap2[idx] = tmp;
                    }
                    else break;
                     idx /= 2;
                }
            }
        }
    }


    return 0;
}


void setarrminusone(int inputnum){
    for(int i=0; i<=inputnum*2 ;i++) Heap1[i] = 2147483647; //arr초기화
    for(int i=0; i<=inputnum*2 ;i++) Heap2[i] = 2147483647; //arr초기화
}