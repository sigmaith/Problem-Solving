import sys

input= sys.stdin.readline

s, t = map(int, input().split())

if 12 <= s <= 16 and t==0:
    print(320)
else:
    print(280)