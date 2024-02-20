import copy
import sys
input = sys.stdin.readline
from itertools import combinations
from collections import deque

def bfs():
    q = deque()
    for x, y in virus:
        q.appendleft([x, y])
    while q:
        x, y = q.pop()
        if x-1 >= 0 and visit[x-1][y] == 0 and lab_c[x-1][y] == 0:
            q.appendleft([x-1, y])
            visit[x-1][y] = 1
            lab_c[x-1][y] = 2
        if x+1 < n and visit[x+1][y] == 0 and lab_c[x+1][y] == 0: #elif로 했다 ㅠㅠ
            q.appendleft([x+1, y])
            visit[x+1][y] = 1
            lab_c[x+1][y] = 2
        if y-1 >= 0 and visit[x][y-1] == 0 and lab_c[x][y-1] == 0:
            q.appendleft([x, y-1])
            visit[x][y-1] = 1
            lab_c[x][y-1] = 2
        if y+1 < m and visit[x][y+1] == 0 and lab_c[x][y+1] == 0:
            q.appendleft([x, y+1])
            visit[x][y+1] = 1
            lab_c[x][y+1] = 2

def check_safe():
    count = 0
    for i in range(n):
        for j in range(m):
            if lab_c[i][j] == 0:
                count += 1
    return count


n, m = map(int, input().split())

virus = []
zeros = []
lab = []
for i in range(n):
    line = list(map(int, input().split()))
    for j in range(m):
        if line[j] == 0:
            zeros.append([i, j])
        elif line[j] == 2:
            virus.append([i, j])
    lab.append(line)

safe = 0 # 안전영역
c = 0
for comb in combinations(zeros, 3):
    visit = [[0] * m for _ in range(n)]
    lab_c = copy.deepcopy(lab)
    for i, j in comb:
        lab_c[i][j] = 1  # 벽을 세움
    bfs()
    tmp = check_safe()
    if safe < tmp:
        safe = tmp

print(safe)
