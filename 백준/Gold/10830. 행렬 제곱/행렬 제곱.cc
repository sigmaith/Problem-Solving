#pragma warning(disable : 4996)
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

long long N, B;
long long A[51][5][5];
long long tmp[5][5];
long long result[5][5];

void mulMatrix(long long c[][5], long long a[][5], long long b[][5]);
void Operate(long long B);

int main(){
    
    scanf("%lld %lld", &N, &B);// N 과 B입력
    for(int i=0;i<N;i++){// 행렬 A 입력받기
        for(int j=0;j<N;j++){
            scanf("%lld", &A[0][i][j]);
        }
    }

    for(int i=0;i<N;i++){//result 에 A를 copy
        for(int j=0;j<N;j++){
            result[i][j] = A[0][i][j];
        }
    }

    for(int i=0;i<N;i++){//result 에 A를 copy
        for(int j=0;j<N;j++){
            result[i][j] = A[0][i][j];
        }
    }

    for(int i=1; i<51 ; i++){//A의 제곱행렬들을 저장
        mulMatrix(A[i],A[i-1],A[i-1]);
    }

     for(int i=0;i<N;i++){
         for(int j=0;j<N;j++){
            //printf("%d ", A[4][i][j] );
        }
        //printf("\n");
     }

    Operate(B-1);/////제곱 계산 실행

    for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
            printf("%lld ", result[i][j] % 1000);
        }
        printf("\n");
    }

    return 0;
}

void mulMatrix(long long c[][5], long long a[][5], long long b[][5])
{
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++) {
            c[i][j] = 0;
            for (int k = 0; k < N; k++)
                c[i][j] += a[i][k] * b[k][j];
            c[i][j] = c[i][j]%1000;
        }
}

void Operate(long long B){
    if(B == 0){
        //printf("B=%lld\n", B);
        return;
    }
    else{
        //printf("B=%lld\n", B);
        
        for(int i=0;i<N;i++){
        for(int j=0;j<N;j++){
           // printf("%lld ", result[i][j] % 1000);
        }
        //printf("\n");
    }
        
        
        for(int i=0;i<N;i++){//copy
            for(int j=0;j<N;j++){
                tmp[i][j] = result[i][j];
            }
        }

        mulMatrix(result, tmp, A[(int)log2(B)]);
        Operate(B-pow(2,(int)log2(B)));
        
     
    }
}


















