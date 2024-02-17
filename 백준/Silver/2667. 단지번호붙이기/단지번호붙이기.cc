#pragma warning(disable:4996)
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int N, hcnt=0, cnt=0, i, j;
int arr[25][25];
int house[100];//house 단지 집수 배열 

void dfs(int x, int y) {//재귀 
    hcnt++;
	arr[x][y]= 0;
	for (int  m =-1; m < 2 ; m+=2) {
		if(x+m>-1 && x+m<N && arr[x+m][y]==1) dfs(x+m,y);
		if(y+m>-1 && y+m<N && arr[x][y+m]==1) dfs(x,y+m); //else if가 아니라 if 
	}
}


int main(){

	scanf("%d", &N);
	for (int n = 0; n < N; n++) {
		for (int m = 0; m < N; m++) {
			scanf("%1d", &arr[n][m]); ///%1d !!
		}
	}
	
	//printf("\n\n");
	
	while (i < N) {
		if (arr[i][j] != 1){
			j++;
			if(j==N) i++,j=0;
		}
		else{
			hcnt=0;
			dfs(i,j);
			house[cnt++]=hcnt;
			//printf("집수 = %d\n\n", hcnt);
			
			/////////////////////////////
			//for (int n = 0; n < N; n++) {
			//    for (int m = 0; m < N; m++) {
			//	     printf("%1d", arr[n][m]);
			//	}
			//	printf("\n");
	     	//}
	     	//printf("\n\n");
	     	/////////////////////////////
		}
	}


	printf("%d\n", cnt);//단지 수
	for (i = 0; i < cnt; i++) {//오름차순 정렬
		for (j = i + 1; j < cnt; j++) {
			int tmp;
			if (house[i] > house[j]) {
				tmp = house[i];
				house[i] = house[j];
				house[j] = tmp;
			}
		}
	}
	for (i = 0; i < cnt; i++) //집 개수 출력
		printf("%d\n", house[i]);

	return 0;
}