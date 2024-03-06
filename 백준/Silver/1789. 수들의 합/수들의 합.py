import sys
input = sys.stdin.readline

s = int(input())
n = 1
while True:
    if n*(n+1) > 2*s:
        n -= 1
        break
    n += 1
print(n)