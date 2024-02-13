import sys
input = sys.stdin.readline

num = int(input())
divisors = list(map(int,input().split()))
divisors.sort()

if num == 1:
    print(divisors[0]*divisors[0])
else:
    print(divisors[0]*divisors[-1])
