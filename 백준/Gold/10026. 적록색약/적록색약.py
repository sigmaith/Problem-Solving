import sys
from collections import deque

input = sys.stdin.readline

def bfs_n(i, j):
    nq.appendleft([i, j])
    visit_n[i][j] = 1
    while nq:
        x, y = nq.pop()
        if y - 1 >= 0 and visit_n[x][y - 1] == 0 and painting[x][y] == painting[x][y - 1]:
            visit_n[x][y - 1] = 1
            nq.appendleft([x, y - 1])
        if y + 1 < n and visit_n[x][y + 1] == 0 and painting[x][y] == painting[x][y + 1]:
            visit_n[x][y + 1] = 1
            nq.appendleft([x, y + 1])
        if x - 1 >= 0 and visit_n[x - 1][y] == 0 and painting[x][y] == painting[x - 1][y]:
            visit_n[x - 1][y] = 1
            nq.appendleft([x - 1, y])
        if x + 1 < n and visit_n[x + 1][y] == 0 and painting[x][y] == painting[x + 1][y]:
            visit_n[x + 1][y] = 1
            nq.appendleft([x + 1, y])


def bfs_w(i, j):
    wq.appendleft([i, j])
    visit_n[i][j] = 1
    while wq:
        x, y = wq.pop()
        if y - 1 >= 0 and visit_w[x][y - 1] == 0 and (painting[x][y] == painting[x][y - 1] or
            painting[x][y] == 'R' and painting[x][y - 1] == 'G' or painting[x][y] == 'G' and painting[x][y - 1] == 'R'):
            visit_w[x][y - 1] = 1
            wq.appendleft([x, y - 1])
        if y + 1 < n and visit_w[x][y + 1] == 0 and (painting[x][y] == painting[x][y + 1] or
            painting[x][y] == 'R' and painting[x][y + 1] == 'G' or painting[x][y] == 'G' and painting[x][y + 1] == 'R'):
            visit_w[x][y + 1] = 1
            wq.appendleft([x, y + 1])
        if x - 1 >= 0 and visit_w[x - 1][y] == 0 and (painting[x][y] == painting[x - 1][y] or
            painting[x][y] == 'R' and painting[x - 1][y] == 'G' or painting[x][y] == 'G' and painting[x - 1][y] == 'R'):
            visit_w[x - 1][y] = 1
            wq.appendleft([x - 1, y])
        if x + 1 < n and visit_w[x + 1][y] == 0 and (painting[x][y] == painting[x + 1][y] or
            painting[x][y] == 'R' and painting[x + 1][y] == 'G' or painting[x][y] == 'G' and painting[x + 1][y] == 'R'):
            visit_w[x + 1][y] = 1
            wq.appendleft([x + 1, y])


n = int(input())

painting = [input().strip() for _ in range(n)]
visit_n = [[0] * n for _ in range(n)]
visit_w = [[0] * n for _ in range(n)]

nq = deque()
wq = deque()

terr_n = 0
terr_w = 0

# 색맹 아닌 사람
for i in range(n):
    for j in range(n):
        if not visit_n[i][j]:
            terr_n += 1
            #print(terr_n)
            bfs_n(i, j)

# 색맹
for i in range(n):
    for j in range(n):
        if not visit_w[i][j]:
            terr_w += 1
            #print(terr_w)
            bfs_w(i, j)

print(terr_n, end=' ')
print(terr_w)
