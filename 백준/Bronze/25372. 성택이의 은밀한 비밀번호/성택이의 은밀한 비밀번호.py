import sys
input = sys.stdin.readline

n = int(input())
for i in range(n):
    s = input().rstrip()
    # print("len = ", len(s))
    if 6 <= len(s) <= 9:
        print("yes")
    else:
        print("no")