import sys
input = sys.stdin.readline

n, m = map(int, input().split())
arr = list(map(int, input().split()))
summ = [0] * n
summ[0] = arr[0]

for i in range(1,n):
    summ[i] = summ[i-1] + arr[i]
#print(summ)
    
for i in range(m):
    x, y = map(int, input().split())
    if x == 1:
        print(summ[y-1])
    else:
        print(summ[y-1] - summ[x-2])