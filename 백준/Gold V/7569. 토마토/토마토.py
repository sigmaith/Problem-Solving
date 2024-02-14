# 20000000 , 1000*1000 = 1000000
# 위치 검사 1000000
# 토마토가 다 익었는지 검사 1000000
import sys
from collections import deque
input = sys.stdin.readline

m, n, h = map(int, input().split())
tomato = [[list(map(int, input().split())) for _ in range(n)] for _ in range(h)]
q = deque()
visited = [[[0] * m for _ in range(n)] for _ in range(h)]

flag = 0
for k in range(h):
    for i in range(n):
        for j in range(m):
            if tomato[k][i][j] == 1:
                q.append([i, j, k, 0])
                visited[k][i][j] = 1
            if tomato[k][i][j] == 0:
                flag = 1

if flag == 0:
    print("0")
else:
    max = 0
    while q:
        x, y, z, num = q.popleft()
        if num > max:
            max = num
        if x - 1 >= 0 and visited[z][x-1][y] == 0 and tomato[z][x-1][y] != -1:
            tomato[z][x-1][y] = 1
            visited[z][x-1][y] = 1
            q.append([x-1, y, z, num+1])
        if x + 1 < n and visited[z][x+1][y] == 0 and tomato[z][x+1][y] != -1:
            tomato[z][x+1][y] = 1
            visited[z][x+1][y] = 1
            q.append([x+1, y, z, num+1])
        if y - 1 >= 0 and visited[z][x][y-1] == 0 and tomato[z][x][y-1] != -1:
            tomato[z][x][y-1] = 1
            visited[z][x][y-1] = 1
            q.append([x, y-1, z, num+1])
        if y + 1 < m and visited[z][x][y+1] == 0 and tomato[z][x][y+1] != -1:
            tomato[z][x][y+1] = 1
            visited[z][x][y+1] = 1
            q.append([x, y+1, z, num+1])
        if z - 1 >= 0 and visited[z-1][x][y] == 0 and tomato[z-1][x][y] != -1:
            tomato[z-1][x][y] = 1
            visited[z-1][x][y] = 1
            q.append([x, y, z-1, num+1])
        if z + 1 < h and visited[z+1][x][y] == 0 and tomato[z+1][x][y] != -1:
            tomato[z+1][x][y] = 1
            visited[z+1][x][y] = 1
            q.append([x, y, z+1, num+1])

    flag = 0
    for k in range(h):
        for i in range(n):
            for j in range(m):
                if tomato[k][i][j] == 0:
                    flag = 1

    if flag:
        print("-1")
    else:
        print(max)

# 익지 않은 토마토
# 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고,
# 토마토가 모두 익지는 못하는 상황이면 -1
