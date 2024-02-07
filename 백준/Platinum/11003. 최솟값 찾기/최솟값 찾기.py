import sys
from collections import deque

input = sys.stdin.readline

d = deque()

n, l = map(int, input().split())
array = list(map(int, input().split()))

for i in range(n):
    if d: # d가 있을때
        if d[0][0] <= i-l:
            d.popleft()
        while d and d[-1][1] > array[i]:
            d.pop()
    d.append([i, array[i]])
    print(d[0][1], end=' ')

