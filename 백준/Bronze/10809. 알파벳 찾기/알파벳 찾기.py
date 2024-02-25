import sys
input = sys.stdin.readline

s = input().rstrip()
arr = [-1] * (ord('z')-ord('a')+1)

for i in range(len(s)):
    #print(ord(s[i])-ord('a'))
    if arr[ord(s[i])-ord('a')] == -1:
        arr[ord(s[i])-ord('a')] = i

for num in arr:
    print(num, end=' ')