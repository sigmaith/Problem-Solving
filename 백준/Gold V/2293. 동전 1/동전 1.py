import sys
input = sys.stdin.readline
n, k = map(int, input().split())

coins = [int(input()) for _ in range(n)]
coins.sort()

DP = [0] * (k + 1)
DP[0] = 1
for c in coins:
    for i in range(c, k+1):
        DP[i] += DP[i - c]
    #print(DP)

print(DP[k])