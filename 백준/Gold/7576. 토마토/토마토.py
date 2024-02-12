# 20000000 , 1000*1000 = 1000000
# 위치 검사 1000000
# 토마토가 다 익었는지 검사 1000000
import sys
from collections import deque
input = sys.stdin.readline

m, n = map(int, input().split())
tomato = [list(map(int, input().split())) for _ in range(n)]
q = deque()
visited = [[0] * m for _ in range(n)]

flag = 0
for i in range(n):
    for j in range(m):
        if tomato[i][j] == 1:
            q.append([i,j,0])
            visited[i][j] = 1
        if tomato[i][j] == 0:
            flag = 1

if flag == 0:
    print("0")
else:
    max = 0
    while q:
        x, y, num = q.popleft()
        if num > max:
            max = num
        if x - 1 >= 0 and visited[x-1][y] == 0 and tomato[x-1][y] != -1:
            tomato[x-1][y] = 1
            visited[x-1][y] = 1
            q.append([x-1, y, num+1])
        if x + 1 < n and visited[x+1][y] == 0 and tomato[x+1][y] != -1:
            tomato[x+1][y] = 1
            visited[x+1][y] = 1
            q.append([x+1, y, num+1])
        if y - 1 >= 0 and visited[x][y-1] == 0 and tomato[x][y-1] != -1:
            tomato[x][y-1] = 1
            visited[x][y-1] = 1
            q.append([x, y-1, num+1])
        if y + 1 < m and visited[x][y+1] == 0 and tomato[x][y+1] != -1:
            tomato[x][y+1] = 1
            visited[x][y+1] = 1
            q.append([x, y+1, num+1])

    flag = 0
    for i in range(n):
        for j in range(m):
            if tomato[i][j] == 0:
                flag = 1

    if flag:
        print("-1")
    else:
        print(max)

# 익지 않은 토마토
# 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고,
# 토마토가 모두 익지는 못하는 상황이면 -1
