import sys
input = sys.stdin.readline

arr = input().rstrip()

l = 0
r = len(arr)-1

flag = 1
while l < r:
    if arr[l] != arr[r]:
        flag = 0
        break
    else:
        l += 1 
        r -= 1
        
print(flag)
        
