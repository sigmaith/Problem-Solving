import sys
from collections import deque
input = sys.stdin.readline

n = int(input())
array = list(map(int, input().split()))
result = [0] * n
s = deque()
for i in range (n):
    while s and array[s[-1]] < array[i]:
        result[s.pop()] = array[i]
    s.append(i)

while s:
    result[s.pop()] = -1

for i in range (n):
    print(result[i],end =' ')