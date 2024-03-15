import sys
input = sys.stdin.readline
from collections import deque

n, k = map(int, input().split())
sec = 2000000
q = deque()
visit = [0] * 100001 # 방문처리!! ㅠㅠ
q.appendleft([n, 0])
visit[n] = 1
while q:
    data = q.pop()
    if data[0] == k:
        sec = data[1]
        break
    else:
        if data[0] + 1 <= 100000 and not visit[data[0] + 1]:
            q.appendleft([data[0] + 1, data[1] + 1])
            visit[data[0] + 1] = 1
        if data[0] - 1 >= 0 and not visit[data[0] - 1]:
            q.appendleft([data[0] - 1, data[1] + 1])
            visit[data[0] - 1] = 1
        if data[0] * 2 <= 100000 and not visit[data[0]*2]:
            q.appendleft([data[0] * 2, data[1] + 1])
            visit[data[0] * 2] = 1

print(sec)