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
if lcs[n-1][m-1] != 0:
    i = n-1
    j = m-1
    result = ''
    while lcs[i][j] > 0 and i>=0 and j>=0:
        if lcs[i][j-1] == lcs[i][j]:
            j -= 1
        elif lcs[i-1][j] == lcs[i][j]:
            i -= 1
        else:
            result += str1[j]
            i -= 1
            j -= 1


    for i in range(len(result)-1, -1, -1):
        print(result[i], end='')

#       A, C, A, Y, K, P
#   [0, 0, 0, 0, 0, 0, 0]
# C [0, 0, 0, 0, 0, 0, 0]
# A [0, 0, 1, 1, 1, 1, 1]
# P [0, 1, 1, 2, 2, 2, 2]
# C [0, 1, 1, 2, 2, 2, 3]
# A [0, 1, 2, 2, 2, 2, 3]
# K [0, 1, 2, 3, 3, 4, 4]