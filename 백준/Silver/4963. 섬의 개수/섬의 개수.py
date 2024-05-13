import sys
input = sys.stdin.readline
from collections import deque

def conn(q, i1, j1):
    if 0 <= i1-1 < y and 0 <= j1 < x and not visited[i1-1][j1] and island_map[i1-1][j1]:
        q.append([i1-1, j1])
        visited[i1-1][j1] = 1  # 방문 체크
    if 0 <= i1 < y and 0 <= j1-1 < x and not visited[i1][j1-1] and island_map[i1][j1-1]:
        q.append([i1, j1-1])
        visited[i1][j1 - 1] = 1
    if 0 <= i1+1 < y and 0 <= j1 < x and not visited[i1+1][j1] and island_map[i1+1][j1]:
        q.append([i1+1, j1])
        visited[i1 + 1][j1] = 1
    if 0 <= i1 < y and 0 <= j1+1 < x and not visited[i1][j1+1] and island_map[i1][j1+1]:
        q.append([i1, j1+1])
        visited[i1][j1 + 1] = 1
    if 0 <= i1-1 < y and 0 <= j1-1 < x and not visited[i1-1][j1-1] and island_map[i1-1][j1-1]:
        q.append([i1-1, j1-1])
        visited[i1 - 1][j1 - 1] = 1
    if 0 <= i1-1 < y and 0 <= j1+1 < x and not visited[i1-1][j1+1] and island_map[i1-1][j1+1]:
        q.append([i1-1, j1+1])
        visited[i1 - 1][j1 + 1] = 1
    if 0 <= i1+1 < y and 0 <= j1-1 < x and not visited[i1+1][j1-1] and island_map[i1+1][j1-1]:
        q.append([i1+1, j1-1])
        visited[i1 + 1][j1 - 1] = 1
    if 0 <= i1+1 < y and 0 <= j1+1 < x and not visited[i1+1][j1+1] and island_map[i1+1][j1+1]:
        q.append([i1+1, j1+1])
        visited[i1 + 1][j1 + 1] = 1

while True:
    x, y = map(int, input().split())
    if x == 0 and y == 0:
        break
    island_map = [list(map(int, input().split())) for _ in range(y)]
    visited = [[0] * x for _ in range(y)]
    # print(island_map)
    q = deque()
    isl = 0  # 섬의 개수
    for i in range(y):
        for j in range(x):
            if not visited[i][j] and island_map[i][j]:  # 방문 안했고 섬이면
                visited[i][j] = 1  # 방문 체크
                isl += 1
                q.append([i, j])
                #  print(q)
                while q:
                    i1, j1 = q.popleft()
                    conn(q, i1, j1)

    print(isl)

