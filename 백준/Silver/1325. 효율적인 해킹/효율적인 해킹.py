import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
A = [[] for _ in range(N+1)]
answer = [0] * (N+1)

def BFS(v):
    visited = [0] * (N + 1) # True, False가 시간을 많이 잡아먹나?????????ㅠ
    queue = deque([v])
    visited[v] = 1
    while queue:
        now_Node = queue.popleft()
        for i in A[now_Node]:
            if not visited[i]:
                visited[i] = 1
                answer[i] += 1
                queue.append(i)

for _ in range(M):
    S, E = map(int, input().split())
    A[S].append(E)

for i in range(1, N+1):
    #if len(A[i]) != 0:
    BFS(i)

maxVal = max(answer)
for i in range(1, N+1):
    if maxVal == answer[i]:
        print(i, end=' ')