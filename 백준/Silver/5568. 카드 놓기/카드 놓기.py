import sys
input = sys.stdin.readline
from itertools import permutations

n = int(input().rstrip())
k = int(input().rstrip())

s = set()
card = list(int(input().rstrip()) for _ in range(n))
cnt = 0
for li in permutations(card, k):
    num = 0
    ten = 0
    for i in range(k-1, -1, -1):
        num += li[i] * (10 ** ten)
        if li[i] < 10:
            ten += 1
        else:
            ten += 2
    s.add(num)

#print(s)
print(len(s))


