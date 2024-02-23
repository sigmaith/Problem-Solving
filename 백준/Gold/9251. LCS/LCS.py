import sys
input = sys.stdin.readline

str1 = '0' + input().strip()
str2 = '0' + input().strip()

m = len(str1)
n = len(str2)

lcs = [[0] * m for _ in range(n)]
for i in range(1, n):
    for j in range(1, m):
        if str1[j] == str2[i]:
            lcs[i][j] = 1 + lcs[i-1][j-1]
        else:
            lcs[i][j] = max(lcs[i-1][j], lcs[i][j-1])

print(lcs[n-1][m-1])