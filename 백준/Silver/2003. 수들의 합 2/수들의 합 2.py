import sys
input = sys.stdin.readline

n, m = map(int, input().split())
a = list(map(int, input().split()))
cnt = 0
for i in range(n):
    f = a[i]
    if a[i] < m:
        for j in range(i+1, n):
            f += a[j]
            if f == m:
                cnt += 1
                break
            elif f > m:
                break
    elif a[i] == m:
        cnt += 1
    else:
        continue

print(cnt)

