import sys
input = sys.stdin.readline
from collections import deque


def bfs(x, y):
    if 0 <= x-1 < n and not visit[x-1][y] and area[x-1][y] > rain_h:
        visit[x-1][y] = 1
        q.append([x-1, y])
    if 0 <= x+1 < n and not visit[x+1][y] and area[x+1][y] > rain_h:
        visit[x+1][y] = 1
        q.append([x+1, y])
    if 0 <= y-1 < n and not visit[x][y-1] and area[x][y-1] > rain_h:
        visit[x][y-1] = 1
        q.append([x, y-1])
    if 0 <= y+1 < n and not visit[x][y+1] and area[x][y+1] > rain_h:
        visit[x][y+1] = 1
        q.append([x, y+1])

n = int(input())
area = [list(map(int, input().split())) for _ in range(n)]

max_h = 0
for array in area:
    for h in array:
        if max_h < h:
            max_h = h

max_safe_area = 0
for rain_h in range(max_h):
    safe_area = 0
    visit = [[0] * n for _ in range(n)]

    for i in range(n):
        for j in range(n):
            if area[i][j] > rain_h and not visit[i][j]:
                q = deque()
                visit[i][j] = 1
                safe_area += 1
                q.append([i, j])
                while q:
                    x, y = q.popleft()
                    bfs(x, y)

    if max_safe_area < safe_area:
        max_safe_area = safe_area

print(max_safe_area)