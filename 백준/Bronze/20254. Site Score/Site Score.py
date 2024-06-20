import sys
input = sys.stdin.readline

# 56UR + 24TR + 14UO + 6TO
q,w,e,r = map(int, input().split())
print(56*q + 24*w + 14*e + 6*r)