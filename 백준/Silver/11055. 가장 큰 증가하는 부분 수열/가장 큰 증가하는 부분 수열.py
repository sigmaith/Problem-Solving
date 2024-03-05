import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
dp_table = [0] * n

def findmax(idx, num, big):
    result = 0
    if idx >= n:
        return 0
    else:
        #print('idx = ', idx , 'num = ', num, 'big = ', big)
        if arr[idx] > num and dp_table[idx] < arr[idx] + big:
            dp_table[idx] = arr[idx] + big
            findmax(idx+1, arr[idx], arr[idx] + big)
            findmax(idx+1, num, big)
        else:
            findmax(idx + 1, num, big)



findmax(0, 0, 0)
print(max(dp_table))

