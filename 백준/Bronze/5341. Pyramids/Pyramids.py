import sys
input = sys.stdin.readline

while True:
    n = int(input())
    if n == 0: break
    else:
        result = 0
        for i in range(1, n+1):
            result += i
        print(result)