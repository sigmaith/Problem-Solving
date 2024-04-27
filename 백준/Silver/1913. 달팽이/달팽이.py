import sys
input = sys.stdin.readline

n = int(input())  # odd number
goal = int(input()) # goal number

arr = [[0] * n for _ in range(n)]
x = y = n // 2  # 가운데
arr[x][y] = 1
num = 2  # 채워 넣을 숫자
flag_x = flag_y = 0

dx = [-1, 0, 1, 0]  # 상, 우, 하, 좌
dy = [0, 1, 0, -1]
d = 0

step = 1
while x != 0 or y != 0:
    #print("x = ", x, "y = ", y, "d = ", d)
    for _ in range(2):
        for _ in range(step):
            #print("x = ", x, "y = ", y, "d = ", d)
            if x == 0 and y == 0: break
            x = x + dx[d % 4]
            y = y + dy[d % 4]
            arr[x][y] = num
            if num == goal:
                flag_x, flag_y = x, y
            num += 1
            #print("x = ", x, "y = ", y, "d = ", d)
        d += 1
    step += 1
    #print("x = ", x, "y = ", y, "d = ", d)

    # print("x = ", x, "y = ", y)
    # for i in range(n):
    #     for j in range(n):
    #         print(arr[i][j], end=' ')
    #     print()
    # print()
    # print()

for i in range(n):
    for j in range(n):
        print(arr[i][j], end=' ')
    print()

if goal == 1:
    print( n // 2 + 1,  n // 2 + 1)
else:
    print(flag_x+1, flag_y+1)
