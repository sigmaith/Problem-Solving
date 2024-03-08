import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**4)

n = int(input())
stairs = []
for i in range(n):
    x = int(input())
    stairs.append(x)

stairs = [0] + stairs
dp = [[0] * 2 for _ in range(n+1)]

#print(stairs)
#print(dp)

def findmaxsum(idx, check):
    if idx > n:
        return 0
    else:
        if dp[idx][check] == 0:
            result = stairs[idx]
            if check == 0:
                if idx+1 <= n and idx+2 <= n:
                    if idx+2 == n and idx != 0:
                        result += findmaxsum(idx+2, 0)
                    else:
                        result += max(findmaxsum(idx+1, 1), findmaxsum(idx+2, 0))
                elif idx+1 <= n:
                    result += findmaxsum(idx+1, 1)

            elif check == 1:
                if idx == 1 and n == 2:
                    result += findmaxsum(idx + 1, 1)
                elif idx == 1 and n == 3:
                    result += findmaxsum(idx+2, 0)
                elif idx == 1 and idx+1 <= n and idx+2 <= n:
                    result += max(findmaxsum(idx + 1, 1), findmaxsum(idx + 2, 0))
                else:
                    if idx+2 <= n:
                        result += findmaxsum(idx+2, 0)
            dp[idx][check] = result
        return dp[idx][check]

print(findmaxsum(0,0))
#print(dp)

# 첫번째 계단부터 시작해서 마지막계단이 포함되지 않은 최대값이 올수도 있다..