import sys
input = sys.stdin.readline
#from itertools import combinations

n = int(input())
ropes = list(int(input()) for _ in range(n))
ropes = sorted(ropes, reverse = True)

wei = ropes[0]
l = 2
for i in range(1, n):
    if wei < ropes[i]*l:
        wei = ropes[i]*l
    l += 1
    
print(wei)