import sys
input = sys.stdin.readline

arr = []

a = int(input())
b = int(input())
c = int(input())

arr.append(a)
arr.append(b)
arr.append(c)

arr.sort()
print(arr[1])
