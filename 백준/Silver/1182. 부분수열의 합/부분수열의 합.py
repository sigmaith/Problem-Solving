import sys
input = sys.stdin.readline

n, s = map(int, input().split())  # 정수의 개수, 정수 s
arr = list(map(int, input().split()))

cnt = 0

for i in range(1 << len(arr)):
    num = 0
    for j in range(len(arr)):
        if i & (1 << j):
            num += arr[j]
    #print("i = ", i , ", num =" , num)
    if i != 0 and num == s:
        cnt += 1

print(cnt)