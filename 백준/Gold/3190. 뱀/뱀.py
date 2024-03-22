import sys
input = sys.stdin.readline
from collections import deque

n = int(input())  # 보드 크기
k = int(input())  # 사과 개수
apples = []
for _ in range(k):
    x, y = map(int, input().split())
    apples.append([x-1, y-1])  # 사과 위치

l = int(input())  # 뱀의 방향 변환 횟수
turns = []
for _ in range(l):
    x, c = map(str, input().split())  # 초(sec)와 방향
    x = int(x)
    turns.append([x, c])

q = deque()
q.append([0, 0, 0])  # x좌표, y 좌표, second
dir = 'right'

dummy = [[0] * n for _ in range(n)]
dummy[0][0] = 1

while q:
    x, y, sec = q[-1][0], q[-1][1], q[-1][2]  # [-1]

    if turns and sec == turns[0][0]: # 방향바꾸기
        if dir == 'right' and turns[0][1] == 'L': dir = 'up'
        elif dir == 'right' and turns[0][1] == 'D': dir = 'down'
        elif dir == 'left' and turns[0][1] == 'L': dir = 'down'
        elif dir == 'left' and turns[0][1] == 'D': dir = 'up'
        elif dir == 'up' and turns[0][1] == 'L': dir = 'left'
        elif dir == 'up' and turns[0][1] == 'D': dir = 'right'
        elif dir == 'down' and turns[0][1] == 'L': dir = 'right'
        elif dir == 'down' and turns[0][1] == 'D': dir = 'left'
        turns.remove(turns[0])

    if dir == 'up' and x-1 < 0: # 벽에 부딪힘 검사
        print(sec+1)
        break
    elif dir == 'down' and x+1 >= n:
        print(sec+1)
        break
    elif dir == 'left' and y-1 < 0:
        print(sec+1)
        break
    elif dir == 'right' and y+1 >= n:
        print(sec+1)
        break

    if len(q) != 1:  # 뱀의 몸에 부딪힘 수정 필요 - 몸통에 부딪힐 수 있구나!! ㅠㅠ
        if dir == 'up' and x-1 >= 0 and dummy[x-1][y] == 1:
            print(sec+1)
            break
        if dir == 'down' and x+1 < n and dummy[x+1][y] == 1:
            print(sec+1)
            break
        if dir == 'left' and y-1 >= 0 and dummy[x][y-1] == 1:
            print(sec+1)
            break
        if dir == 'right' and y+1 < n and dummy[x][y+1] == 1:
            print(sec+1)
            break


    if dir == 'right': # 이동하기
        dummy[x][y + 1] = 1
        if [x, y+1] in apples:  # 사과
            apples.remove([x, y+1])
            q.append([x, y+1, sec+1])
        else:  # 사과 아님
            q.append([x, y+1, sec+1])
            dummy[q[0][0]][q[0][1]] = 0
            q.remove(q[0])
    elif dir == 'left':
        dummy[x][y - 1] = 1
        if [x, y-1] in apples: # 사과
            apples.remove([x, y-1])
            q.append([x, y-1, sec+1])
        else:
            q.append([x, y-1, sec+1])
            dummy[q[0][0]][q[0][1]] = 0
            q.remove(q[0])

    elif dir == 'down':
        dummy[x + 1][y] = 1
        if [x+1, y] in apples: # 사과
            apples.remove([x+1, y])
            q.append([x+1, y, sec+1])
        else:
            q.append([x+1, y, sec+1])
            dummy[q[0][0]][q[0][1]] = 0
            q.remove(q[0])

    elif dir == 'up':
        dummy[x - 1][y] = 1
        if [x-1, y] in apples: # 사과
            apples.remove([x-1, y])
            q.append([x-1, y, sec+1])
        else:
            q.append([x-1, y, sec+1])
            dummy[q[0][0]][q[0][1]] = 0
            q.remove(q[0])