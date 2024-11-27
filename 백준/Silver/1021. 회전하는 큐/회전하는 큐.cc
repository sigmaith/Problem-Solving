#pragma warning(disable:4996)
#include <stdio.h>
#include <stdlib.h>

typedef struct Node {// 노드: 데이터, 포인터 2개
	int data;
	struct Node* prev;
	struct Node* next;
}Node;

Node* new_Node(int data) {// 새로운 노드 생성 함수
	Node* node = (Node*)malloc(sizeof(Node));
	node->data = data;
	node->next = NULL;
	node->prev = NULL;
	return node;
}

typedef struct List {// 리스트 구조체
	int node_cnt; //원소 개수 
	Node* head, * tail; //헤드, 테일
}List;

List* new_List() {// 새로운 리스트 생성
	List* list = (List*)malloc(sizeof(List));
	list->head = list->tail = NULL;
	list->node_cnt = 0;
	return list;
}

void List_Stat(List* list) {//리스트 원소들 출력
	Node* p = list->head;//시작
	printf("list : [");
	while (p->next != list->head) {//원형이기 때문에
		printf("%d, ", p->data);
		p = p->next;
	}
	printf("%d]\n", p->data);
}

void Insert(List* list, int data, int kth) {//list에 data를 kth뒤로 삽입한다.
	if (list->node_cnt!=0 && kth > list->node_cnt + 1) {//삽입하려는 k번째가 list내 원소개수보다 많은 경우
		printf("can't insert kth\n");
		return;
	}
	Node* node = new_Node(data); 
	if (kth == 1) {//kth==1 이라면 list의 가장 앞에 삽입한다.
		if (list->node_cnt==0) {//list에 원소가 없는 경우
			list->head = list->tail = node;
			node->prev = node;
			node->next = node;
			list->node_cnt++;
			return;
		}
		else {//list에 원소가 있는 경우
			node->next = list->head;
			list->head = node;
			node->next->prev = list->head;
			node->prev = list->tail;
			list->node_cnt++;
		}
	}
	else {//kth가 1이 아닐때    kth 2
		kth -= 1;  //1 (첫번째 원소가 1로 시작하니깐..)
		Node* p = list->head;
		while (--kth)
			p = p->next;//kth위치로 이동
		if (p != list->tail ) {//p가 list의 마지막이 아닐경우
			node->prev = p;
			node->next = p->next;
			p->next->prev = node;
			p->next = node;
			list->node_cnt++;
		}
		else {//p가 list의 tail인 경우
			p->next = node;
			node->prev = p;
			list->tail = node;
			node->next = list->head;
			list->node_cnt++;
		}
	}

}

void Delete(List* list, int kth)//list에서 kth번째 원소를 제거한다.
{
	if (list->head == list->tail) { //원소가 1개인 경우
		list->head = list->tail = NULL;
	}
	else if (list->head->next == list->tail) { //원소가 2개인 경우
		if (kth == 1) {
			Node* p = list->head;
			p->next->prev = list->tail;
			p->next->next = list->tail;
			p->next = NULL;
			list->head = list->tail;
			free(p);
		}
		else {
			Node* p = list->tail;
			list->head->next = NULL;
			list->tail = list->head;
			list->head->next = list->head;
			list->head->prev = list->head;
			free(p);
		}
	}
	else { //원소가 3개 이상인 경우
		Node* p = list->head;
		if (kth==1) { //제거하려는 원소가 가장 앞의 원소
			p->next->prev = NULL;
			list->head = p->next;
			p->next = NULL;
			list->head->prev = list->tail;
			list->tail->next = list->head;
			free(p);
		}
		else {
			while (--kth)
				p = p->next;
			Node* x = p->next;
			if (p->next != list->tail) {//맨 뒤의 원소 아닐때
				p->next = p->next->next;
				p->next->next->prev = p;
			}
			else {//맨 뒤의 원소
				x->prev = NULL;
				x->next = NULL;
				list->tail = p;
				p->next = list->head;
			}
			free(x);
		}
	}
	list->node_cnt--;
}

int Search_Kth(List* list, int kth)//kth번째 원소의 data를 반환
{
	if (kth > list->node_cnt) {//kth가 list의 원소개수보다 많을 경우
		printf("kth element does not exist.\n");
		return -1;
	}
	Node* p = list->head;
	while (--kth) p = p->next;
	return p->data;
}

int Search_Num(List* list, int num) {//num값을 가진 원소의 위치 출력
	int kth = 1;
	Node* p = list->head;
	while (p->data != num && p->next != list->head)
		p = p->next, kth++;
	return kth;
}

void Changeheadtailright(List* list) {
	list->head = list->head->next;
	list->tail = list->tail->next;
}

void Changeheadtailleft(List* list) {
	list->head = list->head->prev;
	list->tail = list->tail->prev;
}

int main() {
	int N, M, x, jump=0, tmp;
	scanf("%d %d", &N, &M);
	List* list = new_List();

	for (int i = 0; i < N; i++)
		Insert(list, i + 1, i + 1);

	//List_Stat(list);//

	for (int i = 0; i < M; i++) {
		scanf("%d", &x);

		if (Search_Num(list, x)-1 < list->node_cnt - (Search_Num(list, x)-1)) {
			tmp = Search_Num(list, x) - 1;
			//List_Stat(list);//
			while (list->head->data != x)
				Changeheadtailright(list);
			Delete(list, 1);
			//printf("%d\n", tmp);//
		}

		else {
			tmp = list->node_cnt - (Search_Num(list, x) - 1);
			//List_Stat(list);//
			while (list->head->data != x)
				Changeheadtailright(list);
			Delete(list, 1);
			//printf("%d\n", tmp);//
		}
		jump += tmp;
	}


	printf("%d", jump);


	return 0;
}