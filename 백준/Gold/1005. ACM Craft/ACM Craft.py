# 1005_topological_sorting 
import sys
input = sys.stdin.readline
from collections import deque

def topological_sort():
    result = [0] * (v + 1) # 각 노드에 도달하는 데 걸리는 최대 시간 저장
    q = deque()
    # 진입 차수가 0인 노드를 큐에 넣고, 해당 노드의 기본 건설 시간을 초기화
    for i in range(1, v+1):
        if in_degree[i] == 0:  # 진입 차수 0일때, 노드와 시간
            q.append(i)
            result[i] = con_time[i]
    # 위상 정렬 각 노드 처리
    while q:
        now = q.popleft()

        for next_node in graph[now]:
            # next_node로 들어오는 최대 시간 갱신
            result[next_node] = max(result[next_node], result[now] + con_time[next_node])
            in_degree[next_node] -= 1
            if in_degree[next_node] == 0 :
                q.append(next_node)
    # 목표 노드의 최대 건설 시간 출력
    print(result[goal])

t = int(input())
for _ in range(t):
    v, e = map(int, input().split())  # num of nodes, edges
    con_time = [0]
    con_time = con_time + list(map(int, input().split()))

    in_degree = [0] * (v+1)
    graph = [[] for _ in range(v+1)]

    for _ in range(e):
        x, y = map(int, input().split())
        in_degree[y] += 1
        graph[x].append(y)
    # print(graph)##

    goal = int(input())

    topological_sort()


